package org.schabi.newpipe.core.data.repository

import org.schabi.newpipe.core.model.Stream

interface StreamRepository {
    suspend fun getStream(url: String): Stream
    suspend fun search(query: String): List<Stream>
    suspend fun getTrending(): List<Stream>
}
