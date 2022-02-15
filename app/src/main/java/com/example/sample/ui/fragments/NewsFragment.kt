package com.example.sample.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sample.adapters.news.NewsAdapter
import com.example.sample.databinding.FragmentNewsBinding
import com.example.sample.utils.ExtensionFunctions.hide
import com.example.sample.utils.ExtensionFunctions.show
import com.example.sample.utils.Resource
import com.example.sample.viewmodels.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding get() = _binding!!

    private val viewModel by viewModels<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsAdapter = NewsAdapter(
            onReadMore = { article ->
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(article.url)
                startActivity(intent)
            },
            onShare = { article ->
                val share = Intent(Intent.ACTION_SEND)
                share.type = "text/plain"
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                share.putExtra(Intent.EXTRA_TEXT, article.url)
                startActivity(Intent.createChooser(share, "Share link!"))
            }
        )

        binding.newsRecyclerView.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }

        viewModel.newsList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.show()
                }
                is Resource.Error -> {
                    binding.progressBar.hide()
                    Snackbar.make(
                        requireContext(),
                        binding.root,
                        "ERROR : ${it.message.toString()}",
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction("RETRY") {
                        viewModel.getNewsList()
                    }.show()
                }
                is Resource.Success -> {
                    binding.progressBar.hide()
                    newsAdapter.submitList(it.data)
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}