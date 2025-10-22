package com.ar.asteroidradar.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainerView
import com.ar.asteroidradar.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragContainerView = findViewById<FragmentContainerView>(R.id.nav_host_fragment)
        ViewCompat.setOnApplyWindowInsetsListener(fragContainerView) { view, insets ->
            val innerPadding = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or
                        WindowInsetsCompat.Type.displayCutout()
            )
            fragContainerView.setPadding(
                innerPadding.left,
                innerPadding.top,
                innerPadding.right,
                innerPadding.bottom
            )
            insets
        }
    }
}
