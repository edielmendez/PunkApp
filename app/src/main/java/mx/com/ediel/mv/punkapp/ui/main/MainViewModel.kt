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
import kotlinx.coroutines.launch
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
    private val _state = MutableLiveData<UIState<List<Beer>>>()
    val state: LiveData<UIState<List<Beer>>> = _state

    private var job: Job? = null

    fun fetchBeers(){
        _state.value = UIState.Loading(true)
        job?.cancel()
        job = viewModelScope.launch {
            val response = punkApiRepository.fetchBeers(1)
            _state.value = UIState.Loading(false)
            response.onSuccess {
                _state.value = UIState.Success(it)
            }
            response.onFailure {
                _state.value = UIState.Error(it.message ?: "")
            }
        }
    }override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun saveFav(favorite: Favorite){
        job?.cancel()
        job = viewModelScope.launch {
           favoriteLocalRepository.saveFavorite(favorite)
        }
    }

    fun deleteFav(favorite: Favorite){
        job?.cancel()
        job = viewModelScope.launch {
            favoriteLocalRepository.deleteFavorite(favorite)
        }
    }


}