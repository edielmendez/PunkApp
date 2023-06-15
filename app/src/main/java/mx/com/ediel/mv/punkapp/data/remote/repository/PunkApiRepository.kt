package mx.com.ediel.mv.punkapp.data.remote.repository

import kotlinx.coroutines.flow.Flow
import mx.com.ediel.mv.punkapp.data.models.Beer

interface PunkApiRepository {
    suspend fun fetchBeers(page: Int): Result<List<Beer>>
    suspend fun fetchBeer(beerId: Int): Result<Beer?>
}