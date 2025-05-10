package com.example.dopatox.data.repository.data_sources.remote_data_source

import com.example.dopatox.data.api.WebServices
import com.example.dopatox.data.mapper.toData
import com.example.dopatox.data.mapper.toDomain
import com.example.dopatox.domain.model.auth.RegisterRequest
import com.example.dopatox.domain.model.auth.other.AuthResponse
import com.example.dopatox.domain.model.auth.other.LogoutRequest
import com.example.dopatox.domain.model.auth.other.RecentCodeRequest
import com.example.dopatox.domain.model.auth.other.VerifyCodeRequest
import com.example.dopatox.domain.model.challenge.ChallengeRequest
import com.example.dopatox.domain.model.challenge.ChallengeResponse
import com.example.dopatox.domain.model.usage.UserUsageRequest
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: WebServices) : RemoteDataSource {
    override suspend fun register(request: RegisterRequest): AuthResponse =
        api.register(request.toData()).toDomain()

    override suspend fun verifyCode(request: VerifyCodeRequest): AuthResponse =
        api.verifyCode(request.toData()).toDomain()

    override suspend fun resendCode(request: RecentCodeRequest): AuthResponse =
        api.resendCode(request.toData()).toDomain()

    override suspend fun logout(request: LogoutRequest): Response<Unit> {
        val response = api.logout(request.toData())
        if (response.isSuccessful) {
            return response
        } else {
            throw Exception("Error in logout: ${response.code()}")
        }
    }

    override suspend fun getChallenges(): Response<List<ChallengeResponse>> {
        val response = api.getChallenges()
        return if (response.isSuccessful) {
            Response.success(response.body()!!.map { it.toDomain() })
        } else {
            throw Exception("Error in getChallenges: ${response.code()}")
        }
    }

    override suspend fun addChallenge(request: ChallengeRequest): Response<Unit> {
        val response = api.addChallenges(request.toData())
        return if (response.isSuccessful) {
            Response.success(Unit)
        } else {
            throw Exception("Error in addChallenge: ${response.code()}")
        }
    }

    override suspend fun updateChallenge(id: Int, request: ChallengeRequest): Response<Unit> {
        val response = api.updateChallenges(id, request.toData())
        return if (response.isSuccessful) {
            Response.success(Unit)
        } else {
            throw Exception("Error in updateChallenge: ${response.code()}")
        }
    }

    override suspend fun deleteChallenge(id: Int): Response<Unit> {
        val response = api.deleteChallenge(id)
        return if (response.isSuccessful) {
            Response.success(Unit)
        } else {
            throw Exception("Error in deleteChallenge: ${response.code()}")
        }
    }

    override suspend fun addChallengeBulk(list: List<ChallengeRequest>): Response<Unit> {
        val response = api.addChallengeBulk(list.map { it.toData() })
        return if (response.isSuccessful) {
            Response.success(Unit)
        } else {
            throw Exception("Error in addChallengeBulk: ${response.code()}")
        }
    }

    override suspend fun startChallenge(id: Int): Response<Unit> {
        val response = api.updateChallengeStart(id)
        return if (response.isSuccessful) {
            Response.success(Unit)
        } else {
            throw Exception("Error in startChallenge: ${response.code()}")
        }
    }

    override suspend fun makeDoneChallenge(id: Int): Response<Unit> {
        val response = api.makeDoneChallenge(id)
        return if (response.isSuccessful) {
            Response.success(Unit)
        } else {
            throw Exception("Error in makeDoneChallenge: ${response.code()}")
        }
    }

    override suspend fun cancelChallenge(id: Int): Response<Unit> {
        val response = api.cancelChallenge(id)
        return if (response.isSuccessful) {
            Response.success(Unit)
        } else {
            throw Exception("Error in cancelChallenge: ${response.code()}")
        }
    }

    override suspend fun addUserUsageLog(request: UserUsageRequest): Response<Unit> {
        val response = api.addUserUsageLog(request)
        return if (response.isSuccessful) {
            Response.success(Unit)
        } else {
            throw Exception("Error in addUserUsageLog: ${response.code()}")
        }
    }

    override suspend fun getUserUsageDaily(userId: String, dayDate: String): Response<Unit> {
        val response = api.getUserUsageDaily(userId, dayDate)
        return if (response.isSuccessful) {
            Response.success(Unit)
        } else {
            throw Exception("Error in getUserUsageDaily: ${response.code()}")
        }
    }

    override suspend fun getUserUsageInRange(
        userId: String,
        startDate: String,
        endDate: String
    ): Response<Unit> {
        val response = api.getUserUsageInRange(userId, startDate, endDate)
        return if (response.isSuccessful) {
            Response.success(Unit)
        } else {
            throw Exception("Error in getUserUsageInRange: ${response.code()}")
        }
    }
}

