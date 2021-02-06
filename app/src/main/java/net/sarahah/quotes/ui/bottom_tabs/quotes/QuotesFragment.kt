package net.sarahah.quotes.ui.bottom_tabs.quotes


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_quotes.*
import net.sarahah.quotes.R
import net.sarahah.quotes.base.BaseSupportFragment
import net.sarahah.quotes.data.dto.QuoteResponse
import net.sarahah.quotes.data.model.Quote
import net.sarahah.quotes.ui.bottom_tabs.QuotesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class QuotesFragment : BaseSupportFragment(R.layout.fragment_quotes) {

    override val viewModel by viewModel<QuotesViewModel>()

    lateinit var quoteAdapter: QuoteAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationVisibility = View.VISIBLE
        viewModel.getQuote()
        setupRecyclerView()
        observer()
    }

    override fun refreshUI() {
        super.refreshUI()
        viewModel.getQuote()
    }

    private fun observer() {
        viewModel.getAllQuotes().observe(viewLifecycleOwner, {
            quoteAdapter.differ.submitList(it.toMutableList())
        })

        quoteAdapter.setOnItemClickListener {
            val quote = Quote()
            quote.apply {
                this.id = it.id
                this.author = it.author
                this.permalink = it.permalink
                this.quote = it.quote
            }
            viewModel.insertLikedQuote(quote = quote )
        }
    }

    private fun setupRecyclerView() {
        quoteAdapter = QuoteAdapter()
        rv_quotes.apply {
            adapter = quoteAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}