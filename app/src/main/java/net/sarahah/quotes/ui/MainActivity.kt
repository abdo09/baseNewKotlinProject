package net.sarahah.quotes.ui

import android.os.Bundle
import net.sarahah.quotes.R
import net.sarahah.quotes.base.BaseSupportActivity

class MainActivity : BaseSupportActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}