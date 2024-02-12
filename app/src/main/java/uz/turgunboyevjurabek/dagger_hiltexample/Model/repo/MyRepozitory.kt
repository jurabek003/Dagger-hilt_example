package uz.turgunboyevjurabek.dagger_hiltexample.Model.repo

import uz.turgunboyevjurabek.dagger_hiltexample.Model.network.ApiService
import javax.inject.Inject

class MyRepozitory @Inject constructor(private val apiService: ApiService) {
    suspend fun getFacts()=apiService.getFacts()
}