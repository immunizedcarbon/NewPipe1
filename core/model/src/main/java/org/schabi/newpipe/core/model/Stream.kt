package org.schabi.newpipe.core.model

data class Stream(
    val url: String,
    val title: String,
    val thumbnailUrl: String?,
    val duration: Long,
    val uploader: String?
)
