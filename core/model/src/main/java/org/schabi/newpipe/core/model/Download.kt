package org.schabi.newpipe.core.model

data class Download(
    val streamUrl: String,
    val title: String,
    val thumbnailUrl: String?,
    val localFilePath: String,
    val mediaType: String,
    val status: String,
    val progress: Int
)
