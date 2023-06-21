package com.geeks.taskmanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.geeks.taskmanager.App
import com.geeks.taskmanager.R
import com.geeks.taskmanager.databinding.FragmentHomeBinding
import com.geeks.taskmanager.model.Task
import com.geeks.taskmanager.ui.home.adaptor.TaskAdapter
import com.geeks.taskmanager.ui.task.TaskFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val adapter = TaskAdapter()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = App.db.taskDao().getAll()
        adapter.setTasks(list)
        binding.bottomFab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)

        }

        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}