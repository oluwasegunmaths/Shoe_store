package com.udacity.shoestore

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding
    private var shouldShowMenu: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = this.findNavController(R.id.navHostFragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.label.toString() == "ShoeListFragment") {
                shouldShowMenu = true
                invalidateOptionsMenu()
            } else {
                if (shouldShowMenu) {
                    shouldShowMenu = false
                    invalidateOptionsMenu()
                }
            }
        }
        setSupportActionBar(binding.toolbar)
        val appBarConfiguration = AppBarConfiguration
                .Builder(
                        R.id.shoeListFragment,
                        R.id.loginFragment,
                        R.id.welcomeFragment,
                        R.id.instructionFragment
                )
                .build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        val sharedPreferences: SharedPreferences = getPreferences(MODE_PRIVATE)
        if (sharedPreferences.getBoolean("HAS SIGNED IN", false)) {
            navController.popBackStack(R.id.loginFragment, true)
            navController.navigate(R.id.shoeListFragment)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (shouldShowMenu && menu?.isEmpty() == true) {
            menuInflater.inflate(R.menu.menu_main, menu)
        }
        return super.onPrepareOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (navController.currentDestination?.label?.equals("ShoeListFragment") == true && item.itemId == R.id.action_signout) {
            val sharedPreferences: SharedPreferences = getPreferences(Context.MODE_PRIVATE)
            sharedPreferences.edit().putBoolean("HAS SIGNED IN", false).apply()
            navController.navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.popBackStack()
                || super.onSupportNavigateUp()
    }
}
