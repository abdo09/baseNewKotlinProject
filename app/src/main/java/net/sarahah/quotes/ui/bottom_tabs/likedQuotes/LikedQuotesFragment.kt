package net.sarahah.quotes.ui.bottom_tabs.likedQuotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_quotes.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.sarahah.quotes.R
import net.sarahah.quotes.base.BaseSupportFragment
import net.sarahah.quotes.data.model.Quote
import net.sarahah.quotes.ui.bottom_tabs.QuotesViewModel
import net.sarahah.quotes.ui.bottom_tabs.quotes.QuoteAdapter
import net.sarahah.quotes.utils.Constants.URLS.Companion.SEARCH_NEWS_TIME_DELAY
import org.koin.android.viewmodel.ext.android.viewModel

class LikedQuotesFragment : BaseSupportFragment(R.layout.fragment_liked_quotes) {
    override val viewModel by viewModel<QuotesViewModel>()

    lateinit var likedQuoteAdapter: LikedQuoteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationVisibility = View.VISIBLE
        setupRecyclerView()

        observer(view)
    }

    private fun observer(view: View) {
        viewModel.getAllLikedQuotes().observe(viewLifecycleOwner, {
            likedQuoteAdapter.differ.submitList(it.toMutableList())
        })

        likedQuoteAdapter.setOnItemClickListener {
            var job: Job? = null
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                viewModel.deleteLikedQuote(it)
                viewModel.getAllLikedQuotes().observe(viewLifecycleOwner, {list ->
                    likedQuoteAdapter.differ.submitList(list.toMutableList())
                })
            }
        }
    }

    private fun setupRecyclerView() {
        likedQuoteAdapter = LikedQuoteAdapter()
        rv_quotes.apply {
            adapter = likedQuoteAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}