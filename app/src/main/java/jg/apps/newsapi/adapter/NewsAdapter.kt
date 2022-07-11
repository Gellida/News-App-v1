package jg.apps.newsapi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jg.apps.newsapi.R
import jg.apps.newsapi.api.Article
import jg.apps.newsapi.ui.fragments.NewsFragmentDirections
import jg.apps.newsapi.ui.fragments.SecondNewsFragmentDirections
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter(
    val newsList: () -> Unit

) : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    private var articleslist: List<Article> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Article>) {
        articleslist = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article_preview, parent, false)
        )
    }

    override fun getItemCount(): Int = articleslist.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val Article = articleslist[position]
        holder.itemView.apply {
            tvTitle.text = Article.title
            tvDescription.text = Article.description
            tvSource.text = Article.source.name.toString()
            tvPublishedAt.text = Article.publishedAt
            tvId.text = position.toString()
            Article.url
            Glide.with(this).load(Article.urlToImage).into(ivArticleImage)
            btnSeeMore.setOnClickListener {
                try {
                    val action =
                        NewsFragmentDirections.actionNewsFragmentToArticleFragment(Article.url)
                    findNavController().navigate(action)

                }catch(e: IllegalArgumentException)  {
                    val action =
                        SecondNewsFragmentDirections.actionSecondNewsFragmentToArticleFragment(
                            Article.url
                        )
                    findNavController().navigate(action)
                }

            }


        }
    }


    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}