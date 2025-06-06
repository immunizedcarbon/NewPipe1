package org.schabi.newpipe.core.data.repository.impl

import org.schabi.newpipe.core.data.repository.StreamRepository
import org.schabi.newpipe.core.model.Stream

class DefaultStreamRepository : StreamRepository {
    override suspend fun getStream(url: String): Stream {
        return Stream(url = url, channelUrl = null, title = "", thumbnailUrl = null, duration = 0L, uploader = null)
    }

    override suspend fun search(query: String): List<Stream> {
        return emptyList()
    }

    override suspend fun getTrending(): List<Stream> {
        return emptyList()
    }

    override suspend fun getStreamsForChannel(channelUrl: String): List<Stream> {
        return emptyList()
    }
}
