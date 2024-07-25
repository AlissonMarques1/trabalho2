data class BooksResponse(
    val results: BooksResults
)

data class BooksResults(
    val books: List<Book>
)

data class Book(
    val title: String,
    val author: String,
    val description: String,
    val book_image: String
)
