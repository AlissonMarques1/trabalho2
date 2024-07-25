data class RealTimeArticlesResponse(
    val results: List<RealTimeArticle>
)

data class RealTimeArticle(
    val title: String,
    val abstract: String,
    val url: String
)
