package com.example.footballleagueapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.*
import com.example.footballleagueapplication.databinding.ActivityMainBinding

//
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )
        val appBarConfiguration = AppBarConfiguration(navController.graph)


        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        // This will handle back actions initiated by the the back arrow
        // at the start of the toolbar.
       binding.toolbar.setNavigationOnClickListener {
            // Handle the back button event and return to override
            // the default behavior the same way as the OnBackPressedCallback.
            // TODO(reason: handle custom back behavior here if desired.)

            // If no custom behavior was handled perform the default action.
            navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        }
    }
    override fun onDestroy() {
        _binding = null

        super.onDestroy()

    }



}



