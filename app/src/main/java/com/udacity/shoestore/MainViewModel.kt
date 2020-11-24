package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel : ViewModel() {

    private val list = mutableListOf(
        Shoe("Vertical slayer horizontal", 41.0, "Nike", "The best shoes for running ans cimlb."),
        Shoe("Samsonite slayer", 36.0, "Google", "The best shoes for driving."),
        Shoe("Motion slayer horizontal", 41.0, "Nike", "The best shoes for running ans cimlb."),
        Shoe("Recycler slayer", 23.0, "Puma", "The best shoes for driving."),
        Shoe("Vertical slayer horizontal", 41.0, "Nature", "The best shoes for running ans cimlb."),
        Shoe("horizontal slayer", 45.0, "Adidas", "The best shoes for driving."),
        Shoe("Vertical slayer horizontal", 41.0, "Nike", "The best shoes for running ans cimlb."),
        Shoe("Samsonite slayer", 36.0, "Google", "The best shoes for driving."),
        Shoe("Motion slayer horizontal", 41.0, "Nike", "The best shoes for running ans cimlb."),
        Shoe("Vertical slayer horizontal", 41.0, "Nature", "The best shoes for running ans cimlb.")
    )

    private val _shoeList = MutableLiveData<List<Shoe>>(list)
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList


    fun addShoe(shoe: Shoe) {
        list.add(shoe)
        _shoeList.value = list
    }
}