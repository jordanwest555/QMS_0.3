package com.example.qms_03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class Fragment_login : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)

        val emailField: EditText = root.findViewById(R.id.vossie_email)
        val passwordField: EditText = root.findViewById(R.id.password)
        val loginButton: Button = root.findViewById(R.id.login_button)
        val signupLink: TextView = root.findViewById(R.id.signup_link)

        loginButton.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.text.toString()

            if (email.isNotBlank() && password.isNotBlank()) {
                Toast.makeText(requireContext(), "Attempting to log in...", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Please fill in both fields", Toast.LENGTH_SHORT).show()
            }
        }

        signupLink.setOnClickListener {
            findNavController().navigate(R.id.action_Fragment_login_to_Fragment_sign_up)
        }



        return root
    }
}
