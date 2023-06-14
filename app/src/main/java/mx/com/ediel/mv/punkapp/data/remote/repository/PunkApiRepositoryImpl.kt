package mx.com.ediel.mv.punkapp.data.remote.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import mx.com.ediel.mv.punkapp.data.models.Beer
import mx.com.ediel.mv.punkapp.data.remote.dto.PunkApiBeerDTO
import mx.com.ediel.mv.punkapp.di.DefaultDispatcher
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PunkApiRepositoryImpl @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val service: PunkApiService
): PunkApiRepository {
    override suspend fun fetchBeers(page: Int) = withContext(dispatcher){
        try {
            val response = service.fetchBeers(page)
            if(response.isSuccessful){
                Result.success(response.body()?.map { it.toBeer() } ?: emptyList())
            }else{
                Result.failure(Exception(response.errorBody()?.string()))
            }
        }catch (exception: Exception){
            Result.failure(exception)
        }
    }

    override suspend fun fetchBeer(beerId: Int): Flow<Beer> = flow {
        service.fetchBeer(beerId).map { it.toBeer() }.first()
    }

}