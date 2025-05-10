package com.example.dopatox.domain.repo
import com.example.dopatox.domain.model.challenge.ChallengeRequest
import com.example.dopatox.domain.model.challenge.ChallengeResponse
import com.example.dopatox.domain.model.auth.other.LogoutRequest
import com.example.dopatox.domain.model.auth.other.RecentCodeRequest
import com.example.dopatox.domain.model.auth.RegisterRequest
import com.example.dopatox.domain.model.auth.other.AuthResponse
import com.example.dopatox.domain.model.auth.other.VerifyCodeRequest
import com.example.dopatox.domain.model.usage.UserUsageRequest
import retrofit2.Response

interface Repository {

    suspend fun register(request: RegisterRequest): AuthResponse

    suspend fun verifyCode(request: VerifyCodeRequest): AuthResponse

    suspend fun resendCode(request: RecentCodeRequest): AuthResponse

    suspend fun logout(request: LogoutRequest): Response<Unit>

    suspend fun getChallenges(): Response<List<ChallengeResponse>>

    suspend fun addChallenge(request: ChallengeRequest): Response<Unit>

    suspend fun updateChallenge(id: Int, request: ChallengeRequest): Response<Unit>

    suspend fun deleteChallenge(id: Int): Response<Unit>

    suspend fun addChallengeBulk(list: List<ChallengeRequest>): Response<Unit>

    suspend fun startChallenge(id: Int): Response<Unit>

    suspend fun makeDoneChallenge(id: Int): Response<Unit>

    suspend fun cancelChallenge(id: Int): Response<Unit>

    suspend fun addUserUsageLog(request: UserUsageRequest): Response<Unit>

    suspend fun getUserUsageDaily(userId: String, dayDate: String): Response<Unit>

    suspend fun getUserUsageInRange(userId: String, startDate: String, endDate: String): Response<Unit>

}
