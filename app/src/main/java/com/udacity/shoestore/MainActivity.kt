package com.udacity.shoestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Create the navigation and configure the toolbar
        setSupportActionBar(binding.toolbar)

        /**
         * Thank you for the suggestion, you are right it's deprecated :O
         * navController = this.findNavController(R.id.myNavHostFragment)
         */
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        // UI Navigation
        NavigationUI.setupActionBarWithNavController(this, navController, AppBarConfiguration(setOf(R.id.shoeListFragment)))
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
