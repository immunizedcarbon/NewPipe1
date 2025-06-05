package org.schabi.newpipe.core.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntryEntity(
    @PrimaryKey val streamUrl: String,
    val title: String,
    val uploader: String,
    val thumbnailUrl: String?,
    val accessDate: Long
)
