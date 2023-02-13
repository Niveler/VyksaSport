package com.andreyolenkov.vyksasport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupActionBarWithNavController
import com.andreyolenkov.vyksasport.databinding.ActivityMainBinding
import com.andreyolenkov.vyksasport.databinding.FragmentListComplexBinding
import com.andreyolenkov.vyksasport.ui.screens.complex.ListComplex

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP = this
        navController = Navigation.findNavController(this, R.id.nav_fragment)
        setupActionBarWithNavController(navController)
        navMenu()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun navMenu() {
        binding.bNavMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> {
                    //сюда титл экшнбара
                    APP.navController.navigate(R.id.listComplex)
                }
                R.id.item_events -> {
                    navController.navigate(R.id.listEventsFragment)
                }
                R.id.item_sections -> {}
                R.id.item_profile -> {}
            }
            true
        }
    }
}