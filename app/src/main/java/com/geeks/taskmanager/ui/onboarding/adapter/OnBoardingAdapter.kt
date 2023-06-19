package com.geeks.taskmanager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.geeks.taskmanager.databinding.ItemOnboardingBinding
import com.geeks.taskmanager.model.OnBoarding

class OnBoardingAdapter(private val onClick:() -> Unit) : Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    val list = listOf(
        OnBoarding("Новые идеи.","Дают новые возможности.", "https://i.pinimg.com/originals/30/69/85/306985996d5dafdd0e6652865caeb4f4.jpg"),
        OnBoarding("Новые возможности.","Надо сохранять.", "https://i.pinimg.com/originals/ec/36/13/ec36131f8d78d9239aff089a1f4bd1ab.jpg"),
        OnBoarding("Task Manager","Вам поможет!", "https://i.pinimg.com/564x/42/0a/83/420a8376da0165ebca9f55a453c05941.jpg"),

    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }


    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoarding: OnBoarding) {
            binding.textViewTitle.text = onBoarding.title
            binding.textViewDescription.text = onBoarding.description
            binding.buttonStart.isVisible = adapterPosition == list.lastIndex
            Glide.with(binding.imageViewOnBoarding).load(onBoarding.image).into(binding.imageViewOnBoarding)
            binding.buttonStart.setOnClickListener {
                onClick()

            }


        }

    }
}