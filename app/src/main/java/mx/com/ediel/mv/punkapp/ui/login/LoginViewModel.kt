package mx.com.ediel.mv.punkapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.com.ediel.mv.punkapp.ui.common.UIState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

): ViewModel() {

    private val _state = MutableLiveData<UIState<Boolean>>()
    val state: LiveData<UIState<Boolean>> = _state

    private var job: Job? = null

    fun login(email: String, password: String){
        _state.value = UIState.Loading(true)
        job?.cancel()
        job = viewModelScope.launch {
            delay(3000)
            _state.value = UIState.Loading(false)
            _state.value = UIState.Success(true)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}