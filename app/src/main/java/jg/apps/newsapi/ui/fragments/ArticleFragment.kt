package jg.apps.newsapi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import jg.apps.newsapi.ui.MainViewModel
import jg.apps.newsapi.R
import jg.apps.newsapi.databinding.FragmentArticleBinding


class ArticleFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentArticleBinding

    val args: ArticleFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = FragmentArticleBinding.inflate(inflater, container, false)


        viewModel.getNews()


        activity.let {
            viewModel.NewsInfoList.observe(viewLifecycleOwner) { response ->

                binding.Webvieww.loadUrl(args.url)
                binding.Webvieww.setWebViewClient(WebViewClient())

            }
        }

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_articleFragment_to_newsFragment)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)


        return binding.root
    }
}