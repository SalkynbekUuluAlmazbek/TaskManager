package com.geeks.taskmanager.ui.home.adaptor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.geeks.taskmanager.databinding.ItemTaskBinding
import com.geeks.taskmanager.model.Task


class TaskAdapter(
    private val onLongClickTask: (Task) -> Unit,
    private val onClickTask: (Bundle) -> Unit
) : Adapter<TaskAdapter.TaskViewHolder>() {

    private val list = arrayListOf<Task>()
    fun setTasks(tasks: List<Task>) {
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = list[position]
        holder.binds(currentTask)
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun binds(task: Task) = with(binding) {
            textViewTitle.text = task.title
            textViewDescription.text = task.description
            itemView.setOnLongClickListener {
                onLongClickTask(task)
                false
            }
            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("TASK",task)
                onClickTask(bundle)
            }
        }
    }
}