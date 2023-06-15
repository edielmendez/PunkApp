package mx.com.ediel.mv.punkapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import mx.com.ediel.mv.punkapp.core.ext.toVerifiedList
import mx.com.ediel.mv.punkapp.data.local.favorite.FavoriteLocalRepository
import mx.com.ediel.mv.punkapp.data.models.Beer
import mx.com.ediel.mv.punkapp.data.models.Favorite
import mx.com.ediel.mv.punkapp.data.remote.repository.PunkApiRepository
import mx.com.ediel.mv.punkapp.ui.common.UIState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val punkApiRepository: PunkApiRepository,
    private val favoriteLocalRepository: FavoriteLocalRepository
): ViewModel() {
    private val _state = MutableLiveData<UIState<MainScreenState>>()
    val state: LiveData<UIState<MainScreenState>> = _state

    private var job: Job? = null

    private val beersList: MutableList<Beer> = mutableListOf()

    private var page = 0

    init {
        if(beersList.isEmpty()){
            fetchBeers()
        }
    }

    fun fetchBeers() {
        _state.value = UIState.Loading(true)
        page += 1
        job?.cancel()
        job = viewModelScope.launch {
            val response = punkApiRepository.fetchBeers(page)
            _state.value = UIState.Loading(false)
            response.onSuccess {
                val favorites = favoriteLocalRepository.getFavorites().first()
                beersList.addAll(it.toVerifiedList(favorites))
                _state.value = UIState.Success(data = MainScreenState(beers = beersList))
            }
            response.onFailure {
                _state.value = UIState.Error(it.message ?: "")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    private fun saveFav(favorite: Favorite){
        job?.cancel()
        job = viewModelScope.launch {
           favoriteLocalRepository.saveFavorite(favorite)
        }
    }

    private fun deleteFav(favorite: Favorite){
        job?.cancel()
        job = viewModelScope.launch {
            favoriteLocalRepository.deleteFavorite(favorite)
        }
    }

    fun updateItem(position: Int, beer: Beer) {
        if (beer.isFavorite) {
            deleteFav(beer.toFavorite())
        } else {saveFav(beer.toFavorite())
            saveFav(beer.toFavorite())
        }
        beersList.removeAt(position)
        beersList.add(position , beer.copy(isFavorite = !beer.isFavorite))
        _state.value = UIState.Success(data = MainScreenState(beers = beersList, positionChanged = position))
    }


}