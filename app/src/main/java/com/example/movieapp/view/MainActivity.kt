package com.example.movieapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.homeFragment, R.id.newsFragment,
                R.id.trashFragment, R.id.settingsFragment, R.id.logoutFragment, R.id.shareFragment,
            ), binding.drawerLayout,
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
        binding.bottomNavView.setupWithNavController(navController)
        binding.bottomNavView.setOnItemSelectedListener { NavBottom ->
            when (NavBottom.itemId) {
                R.id.homeFragment -> navController.navigate(R.id.homeFragment)
                R.id.dashboardFragment -> navController.navigate(R.id.dashboardFragment)
                R.id.registrationFragment -> navController.navigate(R.id.registrationFragment)
            }
            return@setOnItemSelectedListener false
        }
        navController.addOnDestinationChangedListener { _, des, _ ->
            when (des.id) {
                R.id.homeFragment -> {

                }
                R.id.dashboardFragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setHomeButtonEnabled(false)
                }
                R.id.registrationFragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setHomeButtonEnabled(false)
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || this.onSupportNavigateUp()
    }

}