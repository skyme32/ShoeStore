package com.udacity.shoestore.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class LoginViewModel : ViewModel() {

    val username = MutableLiveData("")
    val password = MutableLiveData("")

    private val _flagEnabledButton = MutableLiveData(false)
    val flagEnabledButton: LiveData<Boolean>
        get() = _flagEnabledButton

    fun updateButton() {
        //Timber.i((username.value.isNullOrBlank() || password.value.isNullOrBlank()).toString())
        _flagEnabledButton.value =
            !(username.value.isNullOrBlank() || password.value.isNullOrBlank())
    }
}