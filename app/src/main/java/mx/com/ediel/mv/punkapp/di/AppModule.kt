package mx.com.ediel.mv.punkapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.ediel.mv.punkapp.data.remote.repository.Constants.BASE_URL
import mx.com.ediel.mv.punkapp.data.remote.repository.PunkApiRepository
import mx.com.ediel.mv.punkapp.data.remote.repository.PunkApiRepositoryImpl
import mx.com.ediel.mv.punkapp.data.remote.repository.PunkApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Singleton
    @Provides
    fun providePunkApiService(retrofit: Retrofit): PunkApiService =  retrofit.create(PunkApiService::class.java)

    @Provides
    @Singleton
    fun providePunkApiRepository(punkApiRepositoryImpl: PunkApiRepositoryImpl): PunkApiRepository = punkApiRepositoryImpl
}