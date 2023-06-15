package mx.com.ediel.mv.punkapp.data.remote.repository

import kotlinx.coroutines.flow.Flow
import mx.com.ediel.mv.punkapp.data.remote.dto.PunkAPIResponse
import mx.com.ediel.mv.punkapp.data.remote.dto.PunkApiBeerDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PunkApiService {
    @GET("beers")
    suspend fun fetchBeers(@Query("page") page: Int, @Query("per_page") perPage: Int = 10): Response<List<PunkApiBeerDTO>>

    @GET("beers/{beerId}")
    suspend fun fetchBeer(
        @Path("beerId") beerId: Int
    ): Response<List<PunkApiBeerDTO>>
}