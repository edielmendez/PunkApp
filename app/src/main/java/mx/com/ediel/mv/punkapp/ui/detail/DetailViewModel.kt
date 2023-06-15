package mx.com.ediel.mv.punkapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import mx.com.ediel.mv.punkapp.data.models.Beer
import mx.com.ediel.mv.punkapp.data.remote.repository.PunkApiRepository
import mx.com.ediel.mv.punkapp.ui.common.UIState
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PunkApiRepository
):ViewModel() {
    private val _state = MutableLiveData<UIState<Beer>>()
    val state: LiveData<UIState<Beer>> = _state

    private var job: Job? = null

    fun fetchBeer(id: Int){
        _state.value = UIState.Loading(true)
        job?.cancel()
        job = viewModelScope.launch {
            val response = repository.fetchBeer(id)
            _state.value = UIState.Loading(false)
            response.onSuccess {
                it?.let {
                    _state.value = UIState.Success(it)
                }
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
}