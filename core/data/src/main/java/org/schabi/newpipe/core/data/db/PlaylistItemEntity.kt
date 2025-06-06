package org.schabi.newpipe.core.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "playlist_items",
    foreignKeys = [
        ForeignKey(
            entity = PlaylistEntity::class,
            parentColumns = ["id"],
            childColumns = ["playlistId"],
            onDelete = CASCADE
        )
    ],
    indices = [Index("playlistId")]
)
data class PlaylistItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val playlistId: Long,
    val streamUrl: String,
    val title: String,
    val thumbnailUrl: String?,
    val uploader: String?
)
