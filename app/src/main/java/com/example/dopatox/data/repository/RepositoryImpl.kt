package com.example.dopatox.data.repository

import com.example.dopatox.data.model.auth.RegisterRequest
import com.example.dopatox.data.model.auth.RegisterResponse
import com.example.dopatox.data.model.auth.other.LogoutRequest
import com.example.dopatox.data.model.auth.other.RecentCodeRequest
import com.example.dopatox.data.model.auth.other.VerifyCodeRequest
import com.example.dopatox.data.model.auth.other.VerifyCodeResponse
import com.example.dopatox.data.model.challenge.ChallengeRequest
import com.example.dopatox.data.model.challenge.ChallengeResponse
import com.example.dopatox.data.model.usage.UserUsageRequest
import com.example.dopatox.data.repository.data_sources.remote_data_source.RemoteDataSource
import com.example.dopatox.domain.repo.Repository
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val connectivity: Connectivity,
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override suspend fun register(request: RegisterRequest): RegisterResponse {
        return if (connectivity.isOnline()) {
            remoteDataSource.register(request)
        } else {
            throw Exception("No internet connection")
        }
    }

    override suspend fun verifyCode(request: VerifyCodeRequest): VerifyCodeResponse {
        return if (connectivity.isOnline()) {
            remoteDataSource.verifyCode(request)
        } else {
            throw Exception("No internet connection")
        }
    }

    override suspend fun resendCode(request: RecentCodeRequest): RegisterResponse {
        return if (connectivity.isOnline()) {
            remoteDataSource.resendCode(request)
        } else {
            throw Exception("No internet connection")
        }
    }

    override suspend fun logout(request: LogoutRequest): Response<Unit> {
        return if (connectivity.isOnline()) {
            remoteDataSource.logout(request)
        } else {
            throw Exception("No internet connection")
        }
    }

    override suspend fun getChallenges(): Response<List<ChallengeResponse>> {
        return if (connectivity.isOnline()) {
            remoteDataSource.getChallenges()
        } else {
            throw Exception("No internet connection")
        }
    }

    override suspend fun addChallenge(request: ChallengeRequest): Response<Unit> {
        return if (connectivity.isOnline()) {
            remoteDataSource.addChallenge(request)
        } else {
            throw Exception("No internet connection")
        }
    }

    override suspend fun updateChallenge(id: Int, request: ChallengeRequest): Response<Unit> {
        return if (connectivity.isOnline()) {
            remoteDataSource.updateChallenge(id, request)
        } else {
            throw Exception("No internet connection")
        }
    }

    override suspend fun deleteChallenge(id: Int): Response<Unit> {
        return if (connectivity.isOnline()) {
            remoteDataSource.deleteChallenge(id)
        } else {
            throw Exception("No internet connection")
        }
    }

    override suspend fun addChallengeBulk(list: List<ChallengeRequest>): Response<Unit> {
        return if (connectivity.isOnline()) {
            remoteDataSource.addChallengeBulk(list)
        } else {
            throw Exception("No internet connection")
        }
    }

    override suspend fun startChallenge(id: Int): Response<Unit> {
        return if (connectivity.isOnline()) {
            remoteDataSource.startChallenge(id)
        } else {
            throw Exception("No internet connection")
        }
    }

    override suspend fun makeDoneChallenge(id: Int): Response<Unit> {
        return if (connectivity.isOnline()) {
            remoteDataSource.makeDoneChallenge(id)
        } else {
            throw Exception("No internet connection")
        }
    }

    override suspend fun cancelChallenge(id: Int): Response<Unit> {
        return if (connectivity.isOnline()) {
            remoteDataSource.cancelChallenge(id)
        } else {
            throw Exception("No internet connection")
        }
    }

    override suspend fun addUserUsageLog(request: UserUsageRequest): Response<Unit> {
        return if (connectivity.isOnline()) {
            remoteDataSource.addUserUsageLog(request)
        } else {
            throw Exception("No internet connection")
        }
    }

    override suspend fun getUserUsageDaily(userId: String, dayDate: String): Response<Unit> {
        return if (connectivity.isOnline()) {
            remoteDataSource.getUserUsageDaily(userId, dayDate)
        } else {
            throw Exception("No internet connection")
        }
    }

    override suspend fun getUserUsageInRange(
        userId: String,
        startDate: String,
        endDate: String
    ): Response<Unit> {
        return if (connectivity.isOnline()) {
            remoteDataSource.getUserUsageInRange(userId, startDate, endDate)
        } else {
            throw Exception("No internet connection")
        }
    }
}
