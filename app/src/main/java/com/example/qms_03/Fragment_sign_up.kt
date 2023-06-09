package com.example.qms_03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.qms_03.databinding.FragmentSignUpBinding
import android.util.Log
import org.mindrot.jbcrypt.BCrypt

class Fragment_sign_up : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private fun printAllUsers(dbHelper: DatabaseHelper) {
        val allUsers = dbHelper.getAllUsers()
        for (user in allUsers) {
            Log.d("DatabaseHelper", "User: $user")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val signUpButton = binding.signupButton

        signUpButton.setOnClickListener {
            val name = binding.fullName.text.toString()
            val email = binding.studentEmail.text.toString()
            val password = binding.password.text.toString()
            val confirmPassword = binding.confirmPassword.text.toString()
            val isAdmin = binding.adminCheckbox.isChecked

            val passwordPattern = "^(?=.*[A-Z])(?=.*\\d).{6,}$".toRegex()

            if (name.isNotBlank() && email.isNotBlank() && password.isNotBlank() && password == confirmPassword) {
                if (!passwordPattern.matches(password)) {
                    Toast.makeText(requireContext(), "Password must be at least 6 characters long and contain at least 1 uppercase letter and 1 number", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val dbHelper = DatabaseHelper(requireContext())
                if (dbHelper.emailExists(email)) {
                    Toast.makeText(requireContext(), "This email is already registered", Toast.LENGTH_SHORT).show()
                } else {
                    // temporary removal of password hashing as i couldnt get it to work
                    //val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
                    //dbHelper.insertUser(User(null, email, hashedPassword, "", name, "", isAdmin))
                    dbHelper.insertUser(User(null, email, password, "", name, "", isAdmin))
                    Toast.makeText(requireContext(), "User registered successfully", Toast.LENGTH_SHORT).show()
                    printAllUsers(dbHelper)   //test code to verify database working, can be viewed in logcat.
                    findNavController().navigate(R.id.action_Fragment_sign_up_to_Fragment_login)
                }
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields or make sure your passwords match", Toast.LENGTH_SHORT).show()
            }
        }



        val loginLink = binding.loginLink
        loginLink.setOnClickListener {
            findNavController().navigate(R.id.action_Fragment_sign_up_to_Fragment_login)
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
