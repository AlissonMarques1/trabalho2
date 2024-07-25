data class PopularArticlesResponse(
    val results: List<PopularArticle>
)

data class PopularArticle(
    val title: String,
    val abstract: String,
    val url: String,
    val multimedia: List<Multimedia>
)

data class Multimedia(
    val url: String,
    val type: String
)
