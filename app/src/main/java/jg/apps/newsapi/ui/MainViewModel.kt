package jg.apps.newsapi.ui


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jg.apps.newsapi.api.Article
import jg.apps.newsapi.api.NewsApiResponse
import jg.apps.newsapi.api.NewsApiService
import jg.apps.newsapi.util.Constants.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainViewModel : ViewModel() {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: NewsApiService = retrofit.create(NewsApiService::class.java)

    val NewsInfoList = MutableLiveData<List<Article>>()
    val NewsSecondInfoList = MutableLiveData<List<Article>>()


    fun getNews() {
        val call = service.getNewsList()

        call.enqueue(object : Callback<NewsApiResponse> {

            override fun onResponse(
                call: Call<NewsApiResponse>,
                response: Response<NewsApiResponse>
            ) {
                response.body()?.articles?.let {
                    NewsInfoList.postValue(it)
                }
            }

            override fun onFailure(call: Call<NewsApiResponse>, t: Throwable) {
                call.cancel()
            }

        })

    }

    fun getSecondNews() {
        val call = service.getSecondNewsList()

        call.enqueue(object : Callback<NewsApiResponse> {

            override fun onResponse(
                call: Call<NewsApiResponse>,
                response: Response<NewsApiResponse>
            ) {
                response.body()?.articles?.let {
                    NewsSecondInfoList.postValue(it)
                }
            }

            override fun onFailure(call: Call<NewsApiResponse>, t: Throwable) {
                call.cancel()
            }

        })

    }


}