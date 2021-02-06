package net.sarahah.quotes.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import net.sarahah.quotes.R
import net.sarahah.quotes.base.BaseSupportActivity
import net.sarahah.quotes.utils.fadeIn
import net.sarahah.quotes.utils.fadeOut

class MainActivity : BaseSupportActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBottomNavigationVisibility()

        setUpNavigation()

    }

    @SuppressLint("RestrictedApi")
    fun setBottomNavigationVisibility() {
        // get the reference of the bottomNavigationView and set the visibility.
        when (NavHostFragment.findNavController(quotesNavHostFragment).currentDestination?.id) {
            R.id.quotesFragment -> {
                bottom_app_bar.fadeOut(400, View.VISIBLE)
            }

            R.id.likedQuotesFragment -> {
                bottom_app_bar.fadeOut(400, View.VISIBLE)
            }
        }
    }

    @SuppressLint("RestrictedApi")
    fun setBottomNavigationVisibility(visibility: Int) {

        // get the reference of the bottomNavigationView and set the visibility.
        if (visibility == View.VISIBLE){
            bottom_app_bar.fadeIn(250, View.VISIBLE)

        } else if (visibility == View.GONE){
            bottom_app_bar.fadeOut(250, View.GONE)

        }

    }

    private fun setUpNavigation() {
        bottomNavigationView.background = null

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.quoteNav -> {

                    findNavController(R.id.quotesNavHostFragment).navigate(R.id.quotesFragment)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.likedQuoteNav -> {
                    findNavController(R.id.quotesNavHostFragment).navigate(R.id.likedQuotesFragment)
                    return@OnNavigationItemSelectedListener true
                }

            }
            return@OnNavigationItemSelectedListener false

        })
    }

}