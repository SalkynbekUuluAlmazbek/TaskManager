package com.geeks.taskmanager.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.geeks.taskmanager.R
import com.geeks.taskmanager.data.local.Pref
import com.geeks.taskmanager.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    private val pref: Pref by lazy {
        Pref(this.requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveName()
        saveImage()
    }

    private fun saveName() {
        binding.editTextName.setText(pref.getName())
        binding.buttonSaveProfile.setOnClickListener {
            pref.saveName(binding.editTextName.text.toString())
        }
    }

    private fun saveImage() {
        Glide.with(binding.imageProfile).load(pref.getImage())
            .apply(RequestOptions.circleCropTransform()).into(binding.imageProfile)
        binding.imageProfile.setOnClickListener {
            chooseImageContact.launch("image/*")
        }
    }

    private val chooseImageContact =
        registerForActivityResult(ActivityResultContracts.GetContent()) { image ->
            if (image != null) {
                pref.saveImage(image.toString())
                Glide.with(requireContext()).load(image).apply(RequestOptions.circleCropTransform())
                    .into(binding.imageProfile)
            }
        }

}


