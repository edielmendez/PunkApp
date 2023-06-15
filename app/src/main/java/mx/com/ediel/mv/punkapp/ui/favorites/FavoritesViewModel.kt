package mx.com.ediel.mv.punkapp.ui.favorites

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
import mx.com.ediel.mv.punkapp.ui.common.UIState
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoriteLocalRepository: FavoriteLocalRepository
): ViewModel() {
    private val _state = MutableLiveData<UIState<List<Favorite>>>()
    val state: LiveData<UIState<List<Favorite>>> = _state

    private var job: Job? = null

    fun fetchFavorites(){
        //_state.value = UIState.Loading(true)
        job?.cancel()
        job = viewModelScope.launch {
            favoriteLocalRepository.getFavorites()
                .catch {
                    _state.value = UIState.Error(it.message ?: "")
                }
                .collect {
                    _state.value = UIState.Success(it)
                }
        }
    }

    fun updateRate(favId: Int, rate:Int){
        job?.cancel()
        job = viewModelScope.launch {
            favoriteLocalRepository.updateRate(favId, rate)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}