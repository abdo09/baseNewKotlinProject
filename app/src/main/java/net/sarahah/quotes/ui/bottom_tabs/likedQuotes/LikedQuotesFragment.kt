package net.sarahah.quotes.ui.bottom_tabs.likedQuotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.sarahah.quotes.R
import net.sarahah.quotes.base.BaseSupportFragment
import net.sarahah.quotes.ui.bottom_tabs.QuotesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LikedQuotesFragment : BaseSupportFragment(R.layout.fragment_liked_quotes) {
    override val viewModel by viewModel<QuotesViewModel>()


}