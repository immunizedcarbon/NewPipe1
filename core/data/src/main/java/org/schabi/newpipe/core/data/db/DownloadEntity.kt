package org.schabi.newpipe.core.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloads")
data class DownloadEntity(
    @PrimaryKey val streamUrl: String,
    val title: String,
    val thumbnailUrl: String?,
    val localFilePath: String,
    val mediaType: String,
    val status: String,
    val progress: Int
)
