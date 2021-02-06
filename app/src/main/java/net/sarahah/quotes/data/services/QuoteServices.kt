package net.sarahah.quotes.data.services

import net.sarahah.quotes.data.dto.QuoteResponse
import retrofit2.http.*

interface QuoteServices {

    @GET("random.json")
    suspend fun getQuote(): QuoteResponse

}