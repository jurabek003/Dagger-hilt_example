package uz.turgunboyevjurabek.dagger_hiltexample.Model.network

import retrofit2.http.GET
import retrofit2.http.Query
import uz.turgunboyevjurabek.dagger_hiltexample.Model.madels.CatsFacts
import uz.turgunboyevjurabek.dagger_hiltexample.Model.madels.Data
import java.util.ArrayList
import java.util.Date

interface ApiService {

    @GET("facts")
   suspend fun getFacts(@Query("limit") limit:Int=20):CatsFacts

}
