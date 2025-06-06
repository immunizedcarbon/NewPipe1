package org.schabi.newpipe.core.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feed_items")
data class FeedItemEntity(
    @PrimaryKey val streamUrl: String,
    val channelName: String,
    val channelAvatarUrl: String?,
    val uploadDate: Long?,
    val title: String,
    val thumbnailUrl: String?
)
