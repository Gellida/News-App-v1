package jg.apps.newsapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jg.apps.newsapi.MainViewModel
import jg.apps.newsapi.R

class NewsActivity: AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

    }
}