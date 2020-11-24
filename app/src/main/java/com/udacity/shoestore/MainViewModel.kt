package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel : ViewModel() {

    private val list = mutableListOf(
        Shoe("Vertical slayer horizontal", 41.0, "Noke", "The best shoes for running ans cimlb."),
        Shoe("Samsote slayer", 36.0, "Goglle", "The best shoes for driving."),
        Shoe("Motion slayer horizontal", 41.0, "Nike", "The best shoes for running ans cimlb."),
        Shoe("Recycler slayer", 23.0, "Pema", "The best shoes for driving."),
        Shoe("horizontal slayer", 45.0, "Momo", "The best shoes for driving."),
        Shoe("Samsonite slayer", 36.0, "Pepe", "The best shoes for driving."),
        Shoe("Motion slayer horizontal", 41.0, "Nuke", "The best shoes for running ans cimlb."),
    )

    private val _shoeList = MutableLiveData<List<Shoe>>(list)
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList


    fun addShoe(shoe: Shoe) {
        list.add(shoe)
        _shoeList.value = list
    }

    fun updateShoe(shoe: Shoe) {
        list[list.indexOf(shoe)] = shoe
    }

    fun deleteShoe(shoe: Shoe) {
        list.remove(shoe)
        _shoeList.value = list
    }
}