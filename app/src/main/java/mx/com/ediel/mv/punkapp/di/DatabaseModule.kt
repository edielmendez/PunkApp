package mx.com.ediel.mv.punkapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import mx.com.ediel.mv.punkapp.data.local.dao.FavoriteDAO
import mx.com.ediel.mv.punkapp.data.local.dao.UserDAO
import mx.com.ediel.mv.punkapp.data.local.db.AppDatabase
import mx.com.ediel.mv.punkapp.data.local.favorite.FavoriteLocalRepository
import mx.com.ediel.mv.punkapp.data.local.favorite.FavoriteLocalRepositoryImpl
import mx.com.ediel.mv.punkapp.data.local.user.AuthRepository
import mx.com.ediel.mv.punkapp.data.local.user.AuthRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideMWTAAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideUserDAO(appDatabase: AppDatabase): UserDAO {
        return appDatabase.userDAO()
    }

    @Provides
    fun provideFavoriteDAO(appDatabase: AppDatabase): FavoriteDAO {
        return appDatabase.favoriteDAO()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository = authRepositoryImpl

    @Provides
    @Singleton
    fun provideFavoriteLocalRepository(favoriteLocalRepositoryImpl: FavoriteLocalRepositoryImpl): FavoriteLocalRepository = favoriteLocalRepositoryImpl

    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.IO
}

annotation class DefaultDispatcher