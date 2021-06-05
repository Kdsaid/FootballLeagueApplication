package com.example.footballleagueapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )
        val appBarConfiguration = AppBarConfiguration(navController.graph)


        toolbar.setupWithNavController(navController, appBarConfiguration)
        // This will handle back actions initiated by the the back arrow
        // at the start of the toolbar.
        toolbar.setNavigationOnClickListener {
            // Handle the back button event and return to override
            // the default behavior the same way as the OnBackPressedCallback.
            // TODO(reason: handle custom back behavior here if desired.)

            // If no custom behavior was handled perform the default action.
            navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        }
    }
}



