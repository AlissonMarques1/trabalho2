package com.example.myapp.models

data class BooksResponse(
    val results: BooksResults
)

data class BooksResults(
    val books: List<Book>
)