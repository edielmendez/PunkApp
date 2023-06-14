package mx.com.ediel.mv.punkapp.data.local.user

interface AuthRepository {
    suspend fun login(email: String, password: String): Boolean
}