package com.geeks.taskmanager.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geeks.taskmanager.R
import com.geeks.taskmanager.data.local.Pref
import com.geeks.taskmanager.databinding.FragmentOnBoardingBinding
import com.geeks.taskmanager.ui.onboarding.adapter.OnBoardingAdapter

class OnBoardingFragment : Fragment() {

    private lateinit var binding:FragmentOnBoardingBinding

    private val adapter = OnBoardingAdapter(this::onClick)

    private val pref : Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = adapter
        binding.indicator.setViewPager(binding.viewPager)
    }

        private fun onClick() {
            pref.saveSeen()
            findNavController().navigate(R.id.action_to_mobile_navigation)
        }


}