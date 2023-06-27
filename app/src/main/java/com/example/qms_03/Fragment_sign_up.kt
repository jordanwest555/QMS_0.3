package com.example.qms_03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.qms_03.databinding.FragmentSignUpBinding

class Fragment_sign_up : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Access the UI components and perform any necessary setup
        val fullNameEditText = binding.fullName
        val studentEmailEditText = binding.studentEmail
        val passwordEditText = binding.password
        val confirmPasswordEditText = binding.confirmPassword
        val signUpButton = binding.signupButton
        val loginLinkTextView = binding.loginLink

        // Set up listeners or actions for the UI components

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
