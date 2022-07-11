package jg.apps.newsapi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import jg.apps.newsapi.ui.MainViewModel
import jg.apps.newsapi.R
import jg.apps.newsapi.adapter.NewsAdapter
import jg.apps.newsapi.databinding.FragmentNewBinding
import kotlinx.android.synthetic.main.fragment_new.*
import kotlinx.android.synthetic.main.item_article_preview.view.*

class SecondNewsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentNewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = FragmentNewBinding.inflate(inflater, container, false)

        initUI()

        return binding.root
    }

    private fun initUI() {

        viewModel.getSecondNews()

        binding.recyclerNews.layoutManager = LinearLayoutManager(activity)
        binding.recyclerNews.adapter = NewsAdapter {
        }

        activity.let {
            viewModel.NewsInfoList.observe(viewLifecycleOwner) {
                binding.root.btnSeeMore.setOnClickListener {
                    findNavController().navigate(R.id.action_secondNewsFragment_to_articleFragment)
                }
            }
        }

        activity?.let {
            viewModel.NewsSecondInfoList.observe(it) { Articles ->
                (recyclerNews.adapter as NewsAdapter).setData(Articles)
            }
        }



        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_secondNewsFragment_to_newsFragment)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
}