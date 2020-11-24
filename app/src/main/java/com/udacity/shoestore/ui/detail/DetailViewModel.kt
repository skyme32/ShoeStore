package com.udacity.shoestore.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe


class DetailViewModel : ViewModel() {

    val title = MutableLiveData("")
    val sizer = MutableLiveData("")
    val companiest = MutableLiveData("")
    val descriptions = MutableLiveData("")

    private val _flagButtonSave = MutableLiveData(false)
    val flagButtonSave: LiveData<Boolean>
        get() = _flagButtonSave

    var shoe = Shoe("", 0.0, "", "")
        get() = field.apply {
            name = title.value!!
            size = sizer.value!!.toDouble()
            company = companiest.value!!
            description = descriptions.value!!
        }

    fun updateflagButtonSave() {
        _flagButtonSave.value = !title.value.isNullOrBlank()
                && !sizer.value.isNullOrBlank()
                && !companiest.value.isNullOrBlank()
                && !descriptions.value.isNullOrBlank()
    }

}

