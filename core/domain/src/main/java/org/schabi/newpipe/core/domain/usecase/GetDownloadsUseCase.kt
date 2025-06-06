package org.schabi.newpipe.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.schabi.newpipe.core.data.repository.DownloadRepository
import org.schabi.newpipe.core.model.Download
import javax.inject.Inject

class GetDownloadsUseCase @Inject constructor(
    private val repository: DownloadRepository
) {
    operator fun invoke(): Flow<List<Download>> = repository.getDownloads()
}
