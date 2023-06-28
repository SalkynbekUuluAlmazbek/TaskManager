package com.geeks.taskmanager.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.geeks.taskmanager.App
import com.geeks.taskmanager.R
import com.geeks.taskmanager.databinding.FragmentHomeBinding
import com.geeks.taskmanager.model.Task
import com.geeks.taskmanager.ui.home.adaptor.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val adapter  = TaskAdapter(this::onLongClickTask,this::onClickTask)
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
        updateTasksList()

        binding.bottomFab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }

        binding.recyclerView.adapter = adapter
    }

    private fun onClickTask(bundle: Bundle){
        findNavController().navigate(R.id.taskFragment,bundle)
    }

    private fun onLongClickTask(task: Task) {
        val dialogBuilder = AlertDialog.Builder(requireActivity())

        dialogBuilder.setTitle(getString(R.string.you_wont_delete))
            .setMessage(getString(R.string.recovery_is_not_possible))
            .setPositiveButton(getString(R.string.ok)) { dialog: DialogInterface, _: Int ->
                // Обработка нажатия кнопки "OK"
                App.db.taskDao().delete(task)
                updateTasksList()
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog: DialogInterface, _: Int ->
                // Обработка нажатия кнопки "Отмена"
                dialog.dismiss()
            }
        dialogBuilder.show()
    }

    private fun updateTasksList() {
        val list = App.db.taskDao().getAll()
        adapter.setTasks(list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}