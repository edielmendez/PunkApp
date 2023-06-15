package mx.com.ediel.mv.punkapp.data.local.user

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.com.ediel.mv.punkapp.core.util.PAUtilities
import mx.com.ediel.mv.punkapp.data.local.dao.UserDAO
import mx.com.ediel.mv.punkapp.data.local.entities.UserEntity
import mx.com.ediel.mv.punkapp.di.DefaultDispatcher
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val dao: UserDAO
): AuthRepository {
    override suspend fun login(email: String, password: String) = withContext(dispatcher){
        val user = dao.getUserByEmail(email)
        if (user == null){
            false
        }
        password == PAUtilities.base64Decode(user?.password.toString())
    }
}