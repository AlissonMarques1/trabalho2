package com.example.myapp.models

data class PopularArticle(
    val title: String,
    val byline: String,
    val abstract: String,
    val media: List<Media>
)

data class Media(
    val media_metadata: List<MediaMetadata>
)

data class MediaMetadata(
    val url: String
)