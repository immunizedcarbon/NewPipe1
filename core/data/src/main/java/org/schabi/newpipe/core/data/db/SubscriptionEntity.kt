package org.schabi.newpipe.core.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriptions")
data class SubscriptionEntity(
    @PrimaryKey val channelUrl: String,
    val channelName: String,
    val channelAvatarUrl: String?,
    val subscriptionDate: Long
)
