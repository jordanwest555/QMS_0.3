package com.example.qms_03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.qms_03.databinding.HomeFragmentBinding
import com.example.qms_03.ui.home.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val signUpButton: Button = binding.signUpButton
        homeViewModel.signUpButtonText.observe(viewLifecycleOwner) {
            signUpButton.text = it
        }

        signUpButton.setOnClickListener {
            // Navigate to SignUpFragment
            val navController = findNavController()
            navController.navigate(R.id.action_homeFragment_to_signUpFragment)
        }

        val loginButton: Button = binding.loginButton
        homeViewModel.loginButtonText.observe(viewLifecycleOwner) {
            loginButton.text = it
        }

        loginButton.setOnClickListener {
            // Navigate to LoginFragment
            val navController = findNavController()
            navController.navigate(R.id.action_homeFragment_to_loginFragment)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
