package org.schabi.newpipe.feature.downloads

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.io.File
import java.net.URL
import org.schabi.newpipe.core.data.repository.DownloadRepository
import org.schabi.newpipe.core.model.Download
import org.schabi.newpipe.extractor.NewPipe
import org.schabi.newpipe.extractor.stream.StreamInfo

@HiltWorker
class DownloadWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repository: DownloadRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val url = inputData.getString(KEY_STREAM_URL) ?: return Result.failure()
        val type = inputData.getString(KEY_MEDIA_TYPE) ?: "video"
        return try {
            val service = NewPipe.getServiceByUrl(url)
            val info = StreamInfo.getInfo(service, url)
            val stream = if (type == "audio") info.audioStreams.first() else info.videoStreams.first()
            val file = File(applicationContext.filesDir, System.currentTimeMillis().toString())
            val connection = URL(stream.url).openConnection()
            val total = connection.contentLength
            val input = connection.getInputStream()
            file.outputStream().use { out ->
                var downloaded = 0
                val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                var read: Int
                while (input.read(buffer).also { read = it } != -1) {
                    out.write(buffer, 0, read)
                    downloaded += read
                    val progress = if (total > 0) (downloaded * 100 / total) else 0
                    repository.upsert(
                        Download(
                            streamUrl = url,
                            title = info.name,
                            thumbnailUrl = info.thumbnailUrl,
                            localFilePath = file.absolutePath,
                            mediaType = type,
                            status = STATUS_DOWNLOADING,
                            progress = progress
                        )
                    )
                    setForeground(createForegroundInfo(progress, info.name))
                }
            }
            repository.upsert(
                Download(
                    streamUrl = url,
                    title = info.name,
                    thumbnailUrl = info.thumbnailUrl,
                    localFilePath = file.absolutePath,
                    mediaType = type,
                    status = STATUS_COMPLETED,
                    progress = 100
                )
            )
            Result.success()
        } catch (e: Exception) {
            repository.upsert(
                Download(
                    streamUrl = url,
                    title = "",
                    thumbnailUrl = null,
                    localFilePath = "",
                    mediaType = type,
                    status = STATUS_FAILED,
                    progress = 0
                )
            )
            Result.failure()
        }
    }

    private fun createForegroundInfo(progress: Int, title: String): ForegroundInfo {
        createChannel()
        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.stat_sys_download)
            .setContentTitle(title)
            .setProgress(100, progress, false)
            .build()
        return ForegroundInfo(NOTIFICATION_ID, notification)
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "Downloads", NotificationManager.IMPORTANCE_LOW)
            NotificationManagerCompat.from(applicationContext).createNotificationChannel(channel)
        }
    }

    companion object {
        private const val CHANNEL_ID = "download"
        private const val NOTIFICATION_ID = 1001
        const val STATUS_DOWNLOADING = "downloading"
        const val STATUS_COMPLETED = "completed"
        const val STATUS_FAILED = "failed"
        const val KEY_STREAM_URL = "streamUrl"
        const val KEY_MEDIA_TYPE = "mediaType"

        fun buildData(url: String, mediaType: String) = workDataOf(
            KEY_STREAM_URL to url,
            KEY_MEDIA_TYPE to mediaType
        )
    }
}
