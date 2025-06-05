package org.schabi.newpipe.core.data.repository.impl

import org.schabi.newpipe.core.data.repository.StreamRepository
import org.schabi.newpipe.core.model.Stream
import org.schabi.newpipe.extractor.NewPipe
import org.schabi.newpipe.extractor.stream.StreamInfo

class DefaultStreamRepository : StreamRepository {
    override suspend fun getStream(url: String): Stream {
        val service = NewPipe.getServiceByUrl(url)
        val info = StreamInfo.getInfo(service, url)
        return Stream(
            url = info.url,
            title = info.name,
            thumbnailUrl = info.thumbnailUrl,
            duration = info.duration,
            uploader = info.uploaderName
        )
    }
}
