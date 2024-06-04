package loy.mobile.android_sdk_testing.utils

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import loy.mobile.android_sdk_testing.network.ApiService
import loy.mobile.android_sdk_testing.repository.AuthRepository
import loy.mobile.android_sdk_testing.repository.UserRepository
import loy.mobile.android_sdk_testing.repository.impl.AuthRepositoryImpl
import loy.mobile.android_sdk_testing.repository.impl.UserRepositoryImpl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object KoinModules {
    val retrofitModule = module {
        fun provideGson(): Gson {
            return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
        }

        fun provideHttpClient(): OkHttpClient {
            val okHttpClientBuilder = OkHttpClient.Builder()
            return okHttpClientBuilder.build()
        }

        fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit.Builder {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(factory))
                .client(client)
        }

        single { provideGson() }
        single { provideHttpClient() }
        single { provideRetrofit(get(), get()) }
    }

    val repositoryModule = module {
        fun provideUserRepository(apiBuilder: Retrofit.Builder): UserRepository {
            return UserRepositoryImpl(apiBuilder)
        }
        fun provideAuthRepository(apiBuilder: Retrofit.Builder): AuthRepository {
            return AuthRepositoryImpl(apiBuilder)
        }

        single { provideUserRepository(get()) }
        single { provideAuthRepository(get()) }
    }
}