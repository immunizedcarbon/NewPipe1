package org.schabi.newpipe.core.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {
    @Query("SELECT * FROM playlists")
    fun getPlaylists(): Flow<List<PlaylistEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(entity: PlaylistEntity): Long

    @Delete
    suspend fun deletePlaylist(entity: PlaylistEntity)

    @Query("SELECT * FROM playlist_items WHERE playlistId = :playlistId")
    fun getItems(playlistId: Long): Flow<List<PlaylistItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(entity: PlaylistItemEntity)

    @Delete
    suspend fun deleteItem(entity: PlaylistItemEntity)
}
