package org.schabi.newpipe.core.data.repository.impl

import org.schabi.newpipe.core.data.repository.StreamRepository
import org.schabi.newpipe.core.model.Stream
import org.schabi.newpipe.extractor.NewPipe
import org.schabi.newpipe.extractor.kiosk.KioskInfo
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

    override suspend fun getTrending(): List<Stream> {
        val service = NewPipe.getService(0)
        val kioskList = service.kioskList
        val kioskId = kioskList.defaultKioskId
        val url = kioskList.getListLinkHandlerFactoryByType(kioskId).fromId(kioskId).url
        val info = KioskInfo.getInfo(service, url)
        return info.relatedItems.map { item ->
            Stream(
                url = item.url,
                title = item.name,
                thumbnailUrl = item.thumbnailUrl,
                duration = item.duration,
                uploader = item.uploaderName
            )
        }
    }
}
