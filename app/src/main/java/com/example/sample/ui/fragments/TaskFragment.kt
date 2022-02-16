package com.example.sample.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sample.adapters.task.TaskAdapter
import com.example.sample.databinding.FragmentTaskBinding
import com.example.sample.utils.ExtensionFunctions.hide
import com.example.sample.utils.ExtensionFunctions.show
import com.example.sample.viewmodels.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.text.DateFormat
import java.util.*

@AndroidEntryPoint
class TaskFragment : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding: FragmentTaskBinding get() = _binding!!

    private val viewModel by viewModels<TaskViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val date = DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().time)
        binding.toolbar.title = "Today, $date"

        val taskAdapter = TaskAdapter(
            onUpdate = { task ->
                findNavController().navigate(
                    TaskFragmentDirections.actionTaskFragmentToAddUpdateTaskFragment(
                        task
                    )
                )
            },
            onDelete = { task ->
                viewModel.deleteArticle(task)
            }
        )

        binding.taskRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = taskAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getTasksList().collectLatest {
                binding.progressBar.show()
                if (it.isNullOrEmpty()) {
                    binding.textEmptyText.show()
                    binding.progressBar.hide()
                    taskAdapter.submitList(it)
                } else {
                    binding.progressBar.hide()
                    binding.textEmptyText.hide()
                    taskAdapter.submitList(it)
                }
            }
        }

        binding.floatingButtonAdd.setOnClickListener {
            findNavController().navigate(
                TaskFragmentDirections.actionTaskFragmentToAddUpdateTaskFragment(
                    null
                )
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}