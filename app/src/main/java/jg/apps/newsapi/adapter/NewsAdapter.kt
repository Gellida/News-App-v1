package jg.apps.newsapi

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_new.view.*
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter(
    val newsList: () -> Unit

): RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

        private lateinit var viewModel: MainViewModel

        private var articleslist: List<Article> = emptyList<Article>()

        @SuppressLint("NotifyDataSetChanged")
        fun setData(list: List<Article>) {
            articleslist = list
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
            return ArticleViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview, parent, false)
            )
        }

        override fun getItemCount(): Int = articleslist.size

        override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
            val Article = articleslist[position]
            holder.itemView.apply {
                tvTitle.text = Article.title
                tvDescription.text = Article.description
                tvSource.text = Article.source.toString()
                tvPublishedAt.text = Article.publishedAt

                tvTopic1.text = Article.title
                tvTopic3.text = Article.title
            }


        }

        class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}