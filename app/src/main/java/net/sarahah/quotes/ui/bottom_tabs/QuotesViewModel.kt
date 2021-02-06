package net.sarahah.quotes.ui.bottom_tabs

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.sarahah.quotes.base.BaseViewModel
import net.sarahah.quotes.data.client.network.Resource
import net.sarahah.quotes.data.client.network.SingleLiveEvent
import net.sarahah.quotes.data.dto.QuoteResponse
import net.sarahah.quotes.data.repository.QuoteRepository

class QuotesViewModel(
    private val onlineQuoteRepository: QuoteRepository
) : BaseViewModel() {

    var quote = SingleLiveEvent<QuoteResponse>()

    fun getQuote() {
        showLoading.value = true

        launch {
            val result = withContext(Dispatchers.IO) { onlineQuoteRepository.getQuotes() }

            showLoading.value = false

            when (result) {
                is Resource.OnSuccess -> { getAllQuotes() }
                is Resource.OnError -> showError.value = result.exception.message


            }
        }

    }

    fun getAllQuotes() = onlineQuoteRepository.getAllQuotesDB()
}