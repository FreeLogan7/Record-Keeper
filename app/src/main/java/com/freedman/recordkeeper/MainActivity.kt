package com.freedman.recordkeeper

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.freedman.recordkeeper.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnItemSelectedListener {

    //VARIABLES
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuClickHandled = when (item.itemId) {
            R.id.reset_running -> showConfirmationDialog(RUNNING_DISPLAY_VALUE) //or  //RunPreference.edit { clear() }
            R.id.reset_cycling -> showConfirmationDialog(CYCLING_DISPLAY_VALUE)
            R.id.reset_all -> showConfirmationDialog(ALL_DISPLAY_VALUE)
            else -> super.onOptionsItemSelected(item)
        }

        return menuClickHandled
    }

    private fun showConfirmationDialog(selection: String): Boolean {
        AlertDialog.Builder(this)
            .setTitle("Reset $selection records")
            .setMessage("you sure? budddy?")
            .setPositiveButton("yes") { _, _ ->
                when (selection) {
                    ALL_DISPLAY_VALUE -> {
                        getSharedPreferences(RunningFragment.FILENAME, Context.MODE_PRIVATE).edit { clear() }
                        getSharedPreferences(CyclingFragment.FILENAME, Context.MODE_PRIVATE).edit { clear() }
                    }
                    RUNNING_DISPLAY_VALUE -> getSharedPreferences(RunningFragment.FILENAME, Context.MODE_PRIVATE).edit { clear() }
                    CYCLING_DISPLAY_VALUE -> getSharedPreferences(CyclingFragment.FILENAME, Context.MODE_PRIVATE).edit { clear() }
                    else -> {}
                }
                refreshCurrentFragment()
                showConfirmation()
            }
            .setNegativeButton("No", null)
            .show()

        return true
    }

    private fun showConfirmation() {
        val snackbar =
            Snackbar.make(binding.root, "Records Cleared Succesfully", Snackbar.LENGTH_SHORT)
        snackbar.anchorView = binding.bottomNav
        //if wanted to add a reverse button could - >
        /*snackbar.setAction("undo"){ // add listener here// what to do

        }*/
        snackbar.show()
    }

    private fun refreshCurrentFragment() {
        when (binding.bottomNav.selectedItemId) {
            R.id.nav_running -> runningClicked()
            R.id.nav_cycling -> cyclingClicked()
            else -> {}
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean { //IMPLIED :Boolean to = when
        return when (item.itemId) {
            R.id.nav_running -> runningClicked()
            R.id.nav_cycling -> cyclingClicked()
            else -> false
        }
    }

    private fun runningClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, RunningFragment())
        }
        return true
    }

    private fun cyclingClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, CyclingFragment())
        }
        return true
    }

    companion object{
        const val RUNNING_DISPLAY_VALUE = "running"
        const val CYCLING_DISPLAY_VALUE = "cycling"
        const val ALL_DISPLAY_VALUE = "all"
    }
}


/*override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
    R.id.runnin
}*/


/*     the depenendecy for gradle -> build features binding = true allows for attaching to id's
               binding allows us to avoid breaking the system because the layout file we have
               access to is only related to what we are inflating
               binding.frameContent
               binding.bottomNav
        */