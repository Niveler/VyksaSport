package com.andreyolenkov.vyksasport

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.app.Person
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupActionBarWithNavController
import com.andreyolenkov.vyksasport.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.profile_header.*

/**
 * Main activity
 *
 * @constructor Create empty Main activity
 */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var tvLogin :TextView
    lateinit var navigationView: NavigationView
    lateinit var header: View

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP = this
        navController = Navigation.findNavController(this, R.id.nav_fragment)
        navigationView=findViewById(R.id.profileNav)
        header= navigationView.getHeaderView(0)
        tvLogin=header.findViewById(R.id.tvLogin)
        setupActionBarWithNavController(navController)
        navMenu()
        clickOnLogin()
        profileMenuClick()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    /**
     * Click on login
     *
     */
    fun clickOnLogin() {
        tvLogin.setOnClickListener {
            APP.navController.navigate(R.id.signin)
            drawer.closeDrawer(GravityCompat.START) //для закрытия NavigationView
        }
    }

    /**
     * Click edit profile
     *
     */
    fun clickEditProfile() {
    }

    /**
     * Profile menu click
     *
     */
    @SuppressLint("ResourceType")
    fun profileMenuClick() {
        //navigationView.menu.add(R.menu.admin_menu) ЧТОБЫ УСТАНОВИТЬ ДРУГОЕ МЕНЮ ДЛЯ ПОЛЬЗОВАТЕЛЯ
        navigationView.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.itm_register -> {
                    APP.navController.navigate(R.id.register)
                }
                R.id.itm_report -> {
                    APP.navController.navigate(R.id.timeTable)
                }
                R.id.add_complex -> {
                    APP.navController.navigate(R.id.addComplex)
                }
                R.id.add_event -> {
                    APP.navController.navigate(R.id.addEventFragment)
                }
                R.id.add_section -> {
                    APP.navController.navigate(R.id.addSection)
                }
                R.id.mySections -> {
                    APP.navController.navigate(R.id.mySections)
                }
                R.id.declaration -> {
                    APP.navController.navigate(R.id.mySections)
                }
                R.id.mycalendar -> {
                    APP.navController.navigate(R.id.sectionCalendar)
                }
                R.id.mychat -> {
                    APP.navController.navigate(R.id.chat)
                }
            }
            drawer.closeDrawer(GravityCompat.START)
            true
        }
    }

    /**
     * Nav menu
     *
     */
    fun navMenu() {
        binding.bNavMenu.setOnItemSelectedListener {
            // Такая же обработка в NavigationView Menu
            when (it.itemId) {
                R.id.item_home -> { APP.navController.navigate(R.id.listComplex) }
                R.id.item_events -> { navController.navigate(R.id.listEventsFragment) }
                R.id.item_sections -> { navController.navigate(R.id.listSectionFragment) }
                R.id.item_profile -> { binding.drawer.openDrawer(GravityCompat.START) }
            }
            true
        }
    }
}