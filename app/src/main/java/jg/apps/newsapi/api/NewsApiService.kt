package jg.apps.newsapi.api

import jg.apps.newsapi.util.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines")
    fun getNewsList(
        @Query("country")
        countryCode: String = "mx",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY

    ): Call<NewsApiResponse>

    @GET("v2/top-headlines")
    fun getSecondNewsList(
        @Query("q")
        q: String = "apple",
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Call<NewsApiResponse>


}