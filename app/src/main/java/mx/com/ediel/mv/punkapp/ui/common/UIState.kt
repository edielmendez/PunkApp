package mx.com.ediel.mv.punkapp.ui.common

sealed interface UIState<out T>{
    class Loading(val isLoading: Boolean): UIState<Nothing>
    class Success<T>(val data: T): UIState<T>
    class Error(val message: String): UIState<Nothing>
}