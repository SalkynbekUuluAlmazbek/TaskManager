package com.geeks.taskmanager.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.geeks.taskmanager.App
import com.geeks.taskmanager.R
import com.geeks.taskmanager.databinding.FragmentTaskBinding
import com.geeks.taskmanager.model.Task

class TaskFragment : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        task = arguments?.getSerializable("TASK") as Task?
        if (task == null) {
            binding.buttonSave.setOnClickListener {
                onSave()
            }
        } else {
            binding.editTextTitle.setText(task?.title)
            binding.editTextDescription.setText(task?.description)
            binding.buttonSave.text = (getString(R.string.update))
            binding.buttonSave.setOnClickListener {
                onUpdate()
            }
        }
    }

    private fun onSave() {
        val data = Task(
            title = binding.editTextTitle.text.toString(),
            description = binding.editTextDescription.text.toString()
        )

        App.db.taskDao().insert(data)
        findNavController().navigateUp()
    }

    private fun onUpdate() {
        val data = task!!.copy(
            title = binding.editTextTitle.text.toString(),
            description = binding.editTextDescription.text.toString()
        )
        App.db.taskDao().update(data)
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}