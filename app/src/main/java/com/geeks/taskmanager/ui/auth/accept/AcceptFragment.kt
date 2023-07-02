package com.geeks.taskmanager.ui.auth.accept

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.geeks.taskmanager.R
import com.geeks.taskmanager.databinding.FragmentAcceptBinding
import com.geeks.taskmanager.databinding.FragmentPhoneBinding
import com.geeks.taskmanager.ui.auth.phone.PhoneFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class AcceptFragment : Fragment() {

    private lateinit var binding: FragmentAcceptBinding
    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAcceptBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val verId: String? = arguments?.getString(PhoneFragment.VER_KEY)
        binding.bottomAccept.setOnClickListener {
            val credential =
                PhoneAuthProvider.getCredential(verId.toString(), binding.verifyBlock.textEntered.toString())
            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnSuccessListener {
            findNavController().navigate(R.id.action_to_onBoard)
        }.addOnFailureListener {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        }
    }
}