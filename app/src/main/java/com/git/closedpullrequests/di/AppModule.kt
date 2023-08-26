package com.git.closedpullrequests.di

import android.app.Application
import com.git.closedpullrequests.model.GitHubRepository
import com.git.closedpullrequests.model.data.apiService.GitHubApiService
import com.git.closedpullrequests.ui.ClosedPullRequestsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGitHubApiService(): GitHubApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(GitHubApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGitHubRepository(apiService: GitHubApiService): GitHubRepository {
        return GitHubRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideClosedPullRequestsViewModel(repository: GitHubRepository): ClosedPullRequestsViewModel {
        return ClosedPullRequestsViewModel(repository)
    }
}