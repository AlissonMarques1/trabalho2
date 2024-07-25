package com.example.myapp.models

data class NYTResponse(
    val results: List<NYTArticle>
)

data class NYTArticle(
    val title: String,
    val abstract: String,
    val multimedia: List<NYTMultimedia>
)

data class NYTMultimedia(
    val url: String
)
