package mx.com.ediel.mv.punkapp.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.com.ediel.mv.punkapp.data.local.user.AuthRepository
import mx.com.ediel.mv.punkapp.ui.common.UIState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    private val _state = MutableLiveData<UIState<Boolean>>()
    val state: LiveData<UIState<Boolean>> = _state

    private var job: Job? = null

    fun login(email: String, password: String){
        _state.value = UIState.Loading(true)
        job?.cancel()
        job = viewModelScope.launch {
            _state.value = UIState.Loading(false)
            _state.value = UIState.Success(repository.login(email, password))
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}