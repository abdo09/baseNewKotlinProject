package net.sarahah.quotes.ui.bottom_tabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.sarahah.quotes.base.BaseViewModel
import net.sarahah.quotes.data.client.network.Resource
import net.sarahah.quotes.data.client.network.SingleLiveEvent
import net.sarahah.quotes.data.dao.LikedQuoteDao
import net.sarahah.quotes.data.dto.QuoteResponse
import net.sarahah.quotes.data.model.Quote
import net.sarahah.quotes.data.repository.QuoteRepository

class QuotesViewModel(
    private val onlineQuoteRepository: QuoteRepository, private val quoteDao: LikedQuoteDao
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

    fun insertLikedQuote(quote: Quote) {
         launch {
             quote.let { quoteDao.insertAll(listOf(it)) }
         }
    }

    fun deleteLikedQuote(id: Int?) {
        launch {
            withContext(Dispatchers.IO) { id.let { quoteDao.deleteLikedQuotes(it)} }
        }
    }

     fun getAllQuotes() = onlineQuoteRepository.getAllQuotesDB()
     fun getAllLikedQuotes() = onlineQuoteRepository.getAllLikedQuotesDB()
}