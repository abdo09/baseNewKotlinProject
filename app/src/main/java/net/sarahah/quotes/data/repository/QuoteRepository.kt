package net.sarahah.quotes.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import net.sarahah.quotes.data.client.network.Resource
import net.sarahah.quotes.data.dao.QuoteDao
import net.sarahah.quotes.data.services.QuoteServices
import net.sarahah.quotes.data.dto.QuoteResponse

interface QuoteRepository {

    suspend fun getQuotes(): Resource<QuoteResponse>

    fun getAllQuotesDB(): LiveData<List<QuoteResponse>>

}

class OnlineQuoteRepositoryImp(
    private val quoteServices: QuoteServices,
    private val quoteDao: QuoteDao
) : QuoteRepository {
    override suspend fun getQuotes(): Resource<QuoteResponse> {
        return try {
            val quote = quoteServices.getQuote()
            quote.let { quoteDao.insertAll(listOf(it))}
            Resource.OnSuccess(quote)
        } catch (ex: Exception) {
            Resource.OnError(ex)
        }
    }

    override fun getAllQuotesDB(): LiveData<List<QuoteResponse>> {
        return quoteDao.getAllQuotes()
    }

}