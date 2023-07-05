package com.example.qms_03.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _signUpButtonText = MutableLiveData<String>().apply {
        value = "Sign Up"
    }
    val signUpButtonText: LiveData<String> = _signUpButtonText

    private val _loginButtonText = MutableLiveData<String>().apply {
        value = "Login"
    }
    val loginButtonText: LiveData<String> = _loginButtonText
}
