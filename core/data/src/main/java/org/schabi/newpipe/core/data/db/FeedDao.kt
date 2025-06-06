package org.schabi.newpipe.core.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

import kotlinx.coroutines.flow.Flow

@Dao
interface FeedDao {
    @Query("SELECT * FROM feed_items ORDER BY uploadDate DESC")
    fun getFeed(): Flow<List<FeedItemEntity>>

    @Query("DELETE FROM feed_items")
    suspend fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<FeedItemEntity>)

    @Transaction
    suspend fun replaceAll(items: List<FeedItemEntity>) {
        clear()
        insertAll(items)
    }
}
