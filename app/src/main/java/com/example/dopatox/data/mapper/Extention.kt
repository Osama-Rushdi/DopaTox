package com.example.dopatox.data.mapper

import com.example.dopatox.data.model.auth.LoginRequestDM
import com.example.dopatox.data.model.auth.RegisterRequestDM
import com.example.dopatox.data.model.auth.other.AuthResponseDM
import com.example.dopatox.data.model.auth.other.LogoutRequestDM
import com.example.dopatox.data.model.auth.other.RecentCodeRequestDM
import com.example.dopatox.data.model.auth.other.VerifyCodeRequestDM
import com.example.dopatox.data.model.challenge.ChallengeRequestDM
import com.example.dopatox.data.model.challenge.ChallengeResponseDM
import com.example.dopatox.data.model.usage.AppUsagesDM
import com.example.dopatox.data.model.usage.UserUsageRequestDM
import com.example.dopatox.domain.model.usage.AppUsages
import com.example.dopatox.domain.model.usage.UserUsageRequest
import com.example.dopatox.domain.model.challenge.ChallengeRequest
import com.example.dopatox.domain.model.challenge.ChallengeResponse
import com.example.dopatox.domain.model.auth.RegisterRequest
import com.example.dopatox.domain.model.auth.LoginRequest
import com.example.dopatox.domain.model.auth.other.AuthResponse
import com.example.dopatox.domain.model.auth.other.VerifyCodeRequest
import com.example.dopatox.domain.model.auth.other.RecentCodeRequest
import com.example.dopatox.domain.model.auth.other.LogoutRequest


// Extension functions for auth
fun RegisterRequestDM.toDomain(): RegisterRequest {
    return RegisterRequest(
        firstName = this.firstName ?: "",
        lastName = this.lastName ?: "",
        password = this.password ?: "",
        dateOfBirth = this.dateOfBirth ?: "",
        userName = this.userName ?: "",
        email = this.email ?: ""
    )
}

fun LoginRequestDM.toDomain(): LoginRequest {
    return LoginRequest(
        password = this.password ?: "",
        email = this.email ?: ""
    )
}

fun AuthResponseDM.toDomain(): AuthResponse {
    return AuthResponse(
        email = email ?: "", failedMessage = faildMessage?:"", isSuccess = isSuccess?:false
    )
}


// Extension functions for auth other
fun VerifyCodeRequestDM.toDomain(): VerifyCodeRequest {
    return VerifyCodeRequest(
        code = this.code ?: "",
        email = this.email ?: ""
    )
}

fun RecentCodeRequestDM.toDomain(): RecentCodeRequest {
    return RecentCodeRequest(
        email = this.email ?: ""
    )
}

fun LogoutRequestDM.toDomain(): LogoutRequest {
    return LogoutRequest(
        refToken = this.refToken ?: ""
    )
}

// Extension functions for RegisterRequest (from domain model)
fun RegisterRequest.toData(): RegisterRequestDM {
    return RegisterRequestDM(
        firstName = this.firstName,
        lastName = this.lastName,
        password = this.password,
        dateOfBirth = this.dateOfBirth,
        userName = this.userName,
        email = this.email
    )
}

// Extension functions for LoginRequest (from domain model)
fun LoginRequest.toData(): LoginRequestDM {
    return LoginRequestDM(
        password = this.password,
        email = this.email
    )
}

// Extension functions for VerifyCodeRequest (from domain model)
fun VerifyCodeRequest.toData(): VerifyCodeRequestDM {
    return VerifyCodeRequestDM(
        code = this.code,
        email = this.email
    )
}

// Extension functions for RecentCodeRequest (from domain model)
fun RecentCodeRequest.toData(): RecentCodeRequestDM {
    return RecentCodeRequestDM(
        email = this.email
    )
}

// Extension functions for LogoutRequest (from domain model)
fun LogoutRequest.toData(): LogoutRequestDM {
    return LogoutRequestDM(
        refToken = this.refToken
    )
}

// Extension functions for ChallengeRequest (from domain model)
fun ChallengeRequest.toData(): ChallengeRequestDM {
    return ChallengeRequestDM(
        duration = this.duration,
        description = this.description,
        title = this.title
    )
}

// Extension functions for ChallengeResponse (from domain model)
fun ChallengeResponse.toData(): ChallengeResponseDM {
    return ChallengeResponseDM(
        duration = this.duration,
        createdAt = this.createdAt,
        endDate = this.endDate,
        description = this.description,
        id = this.id,
        state = this.state,
        title = this.title,
        startDate = this.startDate
    )
}

// Extension functions for UserUsageRequest (from domain model)
fun UserUsageRequest.toData(): UserUsageRequestDM {
    return UserUsageRequestDM(
        logDate = this.logDate,
        appUsages = this.appUsages,
        userId = this.userId
    )
}

// Extension functions for AppUsages (from domain model)
fun AppUsages.toData(): AppUsagesDM {
    return AppUsagesDM(
        additionalProp1 = this.additionalProp1,
        additionalProp2 = this.additionalProp2,
        additionalProp3 = this.additionalProp3
    )
}

// Extension functions for challenge
fun ChallengeResponseDM.toDomain(): ChallengeResponse {
    return ChallengeResponse(
        duration = this.duration ?: "",
        createdAt = this.createdAt ?: "",
        endDate = this.endDate ?: "",
        description = this.description ?: "",
        id = this.id!!,
        state = this.state!!,
        title = this.title ?: "",
        startDate = this.startDate ?: ""
    )
}

fun ChallengeRequestDM.toDomain(): ChallengeRequest {
    return ChallengeRequest(
        duration = this.duration ?: "",
        description = this.description ?: "",
        title = this.title ?: ""
    )
}


// Extension functions for usage
fun UserUsageRequestDM.toDomain(): UserUsageRequest {
    return UserUsageRequest(
        logDate = this.logDate ?: "",
        appUsages = this.appUsages!!,
        userId = this.userId ?: ""
    )
}

fun AppUsagesDM.toDomain(): AppUsages {
    return AppUsages(
        additionalProp1 = this.additionalProp1 ?: "",
        additionalProp2 = this.additionalProp2 ?: "",
        additionalProp3 = this.additionalProp3 ?: ""
    )
}

