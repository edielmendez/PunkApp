package mx.com.ediel.mv.punkapp.data.remote.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import mx.com.ediel.mv.punkapp.data.models.Beer
import mx.com.ediel.mv.punkapp.di.DefaultDispatcher
import javax.inject.Inject

class PunkApiRepositoryImpl @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val service: PunkApiService
): PunkApiRepository {
    override suspend fun fetchBeers(page: Int): Flow<List<Beer>> = flow {
        service.fetchBeers(page).map { it.toBeer() }
    }

    override suspend fun fetchBeer(beerId: Int): Flow<Beer> = flow {
        service.fetchBeer(beerId).map { it.toBeer() }.first()
    }

}