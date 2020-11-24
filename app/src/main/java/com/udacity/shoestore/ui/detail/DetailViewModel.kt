package com.udacity.shoestore.ui.detail

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

    private val _flagisEdit = MutableLiveData(false)
    val flagisEdit: LiveData<Boolean>
        get() = _flagisEdit


    var shoe = Shoe("", 0.0, "", "")
        get() = field.apply {
            name = title.value!!
            size = sizer.value!!.toDouble()
            company = companiest.value!!
            description = descriptions.value!!
        }
        set(value) {
            title.value = value.name
            sizer.value = value.size.toString()
            companiest.value = value.company
            descriptions.value = value.description
            _flagisEdit.value = true
            field = value
        }

    fun updateflagButtonSave() {
        _flagButtonSave.value = !title.value.isNullOrBlank()
                && !sizer.value.isNullOrBlank()
                && !companiest.value.isNullOrBlank()
                && !descriptions.value.isNullOrBlank()
    }

}

