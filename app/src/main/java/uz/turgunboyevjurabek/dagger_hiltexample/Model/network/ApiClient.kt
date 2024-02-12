package uz.turgunboyevjurabek.dagger_hiltexample.Model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.turgunboyevjurabek.dagger_hiltexample.Model.madels.ConstItem

object ApiClient {
    val api:ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(ConstItem.BASE_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}