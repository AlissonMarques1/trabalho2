package com.example.myapp.models

data class RealTimeArticle(
    val title: String,
    val byline: String,
    val abstract: String,
    val multimedia: List<Multimedia>
)

data class Multimedia(
    val url: String
)