package net.sarahah.quotes.ui.bottom_tabs.quotes


import android.os.Bundle
import android.util.Log
import android.view.View
import net.sarahah.quotes.R
import net.sarahah.quotes.base.BaseSupportFragment
import net.sarahah.quotes.ui.bottom_tabs.QuotesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class QuotesFragment : BaseSupportFragment(R.layout.fragment_quotes) {

    override val viewModel by viewModel<QuotesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getQuote()

        viewModel.getAllQuotes().observe(viewLifecycleOwner, {
            Log.d("DFGASGAF", it.size.toString())
        })
    }

}