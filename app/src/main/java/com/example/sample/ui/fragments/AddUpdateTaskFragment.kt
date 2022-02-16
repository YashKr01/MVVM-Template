package com.example.sample.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sample.R
import com.example.sample.adapters.category.CategoryAdapter
import com.example.sample.databinding.FragmentAddUpdateTaskBinding
import com.example.sample.model.CategoryEntity
import com.example.sample.model.TaskEntity
import com.example.sample.utils.ExtensionFunctions.hide
import com.example.sample.viewmodels.TaskAddUpdateViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddUpdateTaskFragment : Fragment() {

    private var _binding: FragmentAddUpdateTaskBinding? = null
    private val binding: FragmentAddUpdateTaskBinding get() = _binding!!

    private val taskData by navArgs<AddUpdateTaskFragmentArgs>()
    private val viewModel by viewModels<TaskAddUpdateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddUpdateTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // categories adapter & recyclerview
        val categoryAdapter = CategoryAdapter(onItemClick = {
            binding.txtSelectedCategory.text = it.title
        })
        binding.chipRecyclerView.apply {
            setHasFixedSize(false)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }

        // priorities dropdown
        binding.selectedPriority.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.item_priority_dropdown,
                resources.getStringArray(R.array.priorities)
            )
        )

        val list = listOf(
            CategoryEntity(id = null, title = "work"),
            CategoryEntity(id = null, title = "personal"),
            CategoryEntity(id = null, title = "sports"),
            CategoryEntity(id = null, title = "office")
        )
        categoryAdapter.submitList(list)

        // update ui if task is not null
        val receivedTask = taskData.task
        receivedTask?.let {
            updateUI(it)
        }

        var currentDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        val hourTime24 = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val hourTime12 = Calendar.getInstance().get(Calendar.HOUR)
        val minTime = Calendar.getInstance().get(Calendar.MINUTE)

        setTime(hourTime24, hourTime12, minTime)

        binding.btnAddUpdateTask.setOnClickListener {

            val currentTask: TaskEntity?

            // if task is to be updated
            if (receivedTask != null) {
                currentTask = receivedTask.copy(
                    title = binding.txtTaskTitle.text.toString(),
                    priority = binding.selectedPriority.text.toString(),
                    category = binding.txtSelectedCategory.text.toString(),
                    date = currentDate
                )
            }

            // if new task is to be added
            else {
                currentTask = TaskEntity(
                    id = null,
                    title = binding.txtTaskTitle.text.toString(),
                    priority = binding.selectedPriority.text.toString(),
                    category = binding.txtSelectedCategory.text.toString(),
                    date = currentDate
                )
            }

            // update/insert task
            val job = lifecycleScope.launch {
                currentTask.let { task -> viewModel.insertTask(task) }
            }

            // navigate back
            lifecycleScope.launch(Dispatchers.Main) {
                job.join()
                findNavController().popBackStack()
            }

        }

        binding.datePicker.setOnDateChangeListener { _, year, month, day ->
            currentDate = "$day-$month-$year"
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setTime(hourTime24: Int, hourTime12: Int, minTime: Int) {
        val hourString = if (hourTime12 < 10) "0$hourTime12" else hourTime12.toString()
        val minString = if (minTime < 10) "0$minTime" else minTime.toString()

        if(hourTime24 < 12 ) binding.txtCurrentTime.text = "$hourString:$minString AM"
        else binding.txtCurrentTime.text = "$hourString:$minString PM"

    }


    private fun updateUI(taskEntity: TaskEntity) {
        binding.apply {
            toolbar.title = resources.getString(R.string.update_task)
            btnAddUpdateTask.text = resources.getString(R.string.update_task)
            txtTaskTitle.setText(taskEntity.title)
            txtSelectedCategory.text = taskEntity.category
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}