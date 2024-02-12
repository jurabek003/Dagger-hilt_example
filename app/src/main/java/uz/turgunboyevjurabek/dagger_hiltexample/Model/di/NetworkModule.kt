package uz.turgunboyevjurabek.dagger_hiltexample.Model.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.turgunboyevjurabek.dagger_hiltexample.Model.madels.ConstItem
import uz.turgunboyevjurabek.dagger_hiltexample.Model.network.ApiService
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideBaseUrl():String=ConstItem.BASE_URl


    @Provides
    @Singleton
    fun provideGetRetrofit(string: String):Retrofit{
        return Retrofit.Builder()
            .baseUrl(string)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getApiService(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }


}