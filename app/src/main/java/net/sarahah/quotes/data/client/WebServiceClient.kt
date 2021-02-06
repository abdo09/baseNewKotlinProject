package net.sarahah.quotes.data.client

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import net.sarahah.quotes.utils.Constants
import okhttp3.Cache
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

fun createHttpClient(): OkHttpClient {

    val tlsSpecs: List<ConnectionSpec> = listOf(ConnectionSpec.MODERN_TLS)

    val logInterceptor = HttpLoggingInterceptor()
    logInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val clientBuilder = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .connectionSpecs(tlsSpecs)
        .addInterceptor {
            val original = it.request()
            val requestBuilder = original.newBuilder()
            requestBuilder.header("Content-Type", "application/json")

            val request = requestBuilder.build()
            return@addInterceptor it.proceed(request)
        }
        .callTimeout(2, TimeUnit.MINUTES)
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .retryOnConnectionFailure(true)
        .cache(Cache(createTempDir(), 200))

    clientBuilder.addInterceptor(logInterceptor)

    return clientBuilder.build()
}

fun createRetrofit(httpClient: OkHttpClient): Retrofit{

    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    return Retrofit.Builder()
        .baseUrl(Constants.URLS.BASE_URL)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

}

inline fun <reified T> createWebService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}