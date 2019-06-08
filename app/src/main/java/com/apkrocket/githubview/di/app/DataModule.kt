package com.apkrocket.githubview.di.app

import com.apkrocket.BASE_URL
import com.apkrocket.githubview.store.data.GithubStore
import com.apkrocket.githubview.store.rest.GithubService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class DataModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(createOkHttp())
            .build()
    }

    @Provides
    fun providesGithubStore(retrofit: Retrofit): GithubStore {
        return GithubStore(retrofit.create(GithubService::class.java))
    }

    fun createOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()

                val request = original.newBuilder()
                    .header("User-Agent", "Your-App-Name")
                    .header("Accept", "application/vnd.github.v3+json")
                    .method(original.method(), original.body())
                    .build()

                chain.proceed(request)
            }.build()
    }
}