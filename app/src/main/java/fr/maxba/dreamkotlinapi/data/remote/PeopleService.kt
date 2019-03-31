package fr.maxba.dreamkotlinapi.data.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PeopleService {

    @GET("people/")
    fun getTopRatedPeoples(): Observable<PeoplesListResponse>

    @GET("people/{people}")
    fun getPeople(@Path("people") id: Int): Observable<PeopleResponse>

    companion object {

        fun create(): PeopleService {

            val client = OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request()
                val newUrl = request.url().newBuilder().build()
                chain.proceed(request.newBuilder().url(newUrl).build())
            }.build()


            val retrofit = Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(PeopleService::class.java)
        }
    }
}