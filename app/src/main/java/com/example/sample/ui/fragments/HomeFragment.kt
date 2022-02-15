package com.example.sample.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sample.R
import com.example.sample.adapters.book.BooksAdapter
import com.example.sample.databinding.FragmentHomeBinding
import com.example.sample.model.BookEntity
import com.example.sample.utils.Constants.STATIC_BOOK_URL1
import com.example.sample.utils.Constants.STATIC_BOOK_URL2
import com.google.firebase.auth.FirebaseAuth


class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding get() = _binding!!
    private var _binding: FragmentHomeBinding? = null

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        // for drawer layout
        setupNavigationDrawer()

        val bookAdapter = BooksAdapter()
        binding.booksRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = bookAdapter
        }

        val list = listOf(
            BookEntity(
                title = resources.getString(R.string.the_hobbit),
                image = STATIC_BOOK_URL1
            ),
            BookEntity(
                title = resources.getString(R.string.the_famous_five),
                image = STATIC_BOOK_URL2
            ),
            BookEntity(
                title = resources.getString(R.string.the_hobbit),
                image = STATIC_BOOK_URL1
            ),
            BookEntity(
                title = resources.getString(R.string.the_famous_five),
                image = STATIC_BOOK_URL2
            )
        )

        bookAdapter.submitList(list)

    }

    private fun setupNavigationDrawer() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)?.let {
            it.setSupportActionBar(binding.toolbar)
            it.supportActionBar?.title = resources.getString(R.string.online_book_store)
        }

        val toggle = ActionBarDrawerToggle(
            activity,
            binding.drawerLayout,
            binding.toolbar,
            R.string.open,
            R.string.close
        )
        toggle.syncState()

        binding.drawerLayout.addDrawerListener(toggle)

        binding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_news -> {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewsFragment())
                    true
                }
                R.id.nav_weather -> {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToWeatherFragment())
                    true
                }
                R.id.nav_todo -> {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTaskFragment())
                    true
                }
                R.id.nav_language -> {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLanguageFragment())
                    true
                }
                else -> {
                    auth.signOut()
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
                    true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}