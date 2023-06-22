package com.geeks.taskmanager.ui.home.adaptor

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.geeks.taskmanager.databinding.ItemTaskBinding
import com.geeks.taskmanager.model.Task
import android.content.Context


class TaskAdapter (private val context: Context): Adapter<TaskAdapter.TaskViewHolder>() {


    private var onItemClickListener: OnItemClickListener? = null


    interface OnItemClickListener {
        fun onItemDelete(task: Task)
    }


    private val list = arrayListOf<Task>()


    fun setTasks(tasks: List<Task>) {
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding).apply {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemDelete(list[position])
                    val task = list[position]
                    showConfirmationDialog(task)
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = list[position]
        holder.bind(currentTask)
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) = with(binding) {

            textViewTitle.text = task.title
            textViewDescription.text = task.description

        }
    }

    private fun showConfirmationDialog(task: Task) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setTitle("Подтверждение")
            .setMessage("Вы уверены, что хотите  удалить этот элемент?")
            .setPositiveButton("Да") { dialog, _ ->
                onItemClickListener?.onItemDelete(task)
                deleteTask(task)
                dialog.dismiss()
            }
            .setNegativeButton("Нет") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun deleteTask(task: Task) {
        val position = list.indexOf(task)
        if (position != -1) {
            list.removeAt(position)
            notifyItemRemoved(position)
        }
    }


}