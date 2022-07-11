package jg.apps.newsapi.api
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsApiResponse(
    @Expose @SerializedName("articles") val articles: List<Article>,
    @Expose @SerializedName("status") val status: String,
    @Expose @SerializedName("total_results") val totalResults: Int,
)

data class Article(
    @Expose @SerializedName("author") val author: String,
    @Expose @SerializedName("content") val content: String,
    @Expose @SerializedName("description") val description: String,
    @Expose @SerializedName("publishedAt") val publishedAt: String,
    @Expose @SerializedName("source") val source: Source,
    @Expose @SerializedName("title") val title: String,
    @Expose @SerializedName("url") val url: String,
    @Expose @SerializedName("urlToImage") val urlToImage: String,
)

data class Source(
    @Expose @SerializedName("id") val id: Any,
    @Expose @SerializedName("name") val name: Any
)





