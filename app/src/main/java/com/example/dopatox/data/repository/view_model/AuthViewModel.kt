package com.example.dopatox.data.repository.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dopatox.domain.model.auth.RegisterRequest
import com.example.dopatox.domain.model.auth.other.LogoutRequest
import com.example.dopatox.domain.model.auth.other.VerifyCodeRequest
import com.example.dopatox.domain.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val stateShow = MutableLiveData<AuthState>()

    fun register(request: RegisterRequest) {
        stateShow.postValue(AuthState.Loading)
        viewModelScope.launch {
            try {
                val response = repository.register(request)
                stateShow.postValue(AuthState.SuccessRegister(response))
            } catch (e: Exception) {
                stateShow.postValue(AuthState.Error(e.message ?: "Unknown error"))
            }
        }
    }

    fun verifyCode(request: VerifyCodeRequest) {
        stateShow.postValue(AuthState.Loading)
        viewModelScope.launch {
            try {
                val response = repository.verifyCode(request)
                stateShow.postValue(AuthState.SuccessVerifyCode(response))
            } catch (e: Exception) {
                stateShow.postValue(AuthState.Error(e.message ?: "Unknown error"))
            }
        }
    }

    fun logout(request: LogoutRequest) {
        stateShow.postValue(AuthState.Loading)
        viewModelScope.launch {
            try {
                val response = repository.logout(request)
                stateShow.postValue(AuthState.SuccessLogout(response))
            } catch (e: Exception) {
                stateShow.postValue(AuthState.Error(e.message ?: "Unknown error"))
            }
        }
    }

    sealed class AuthState {
        data object Loading : AuthState()
        data class SuccessRegister(val response: RegisterResponseDM) : AuthState()
        data class SuccessVerifyCode(val response: VerifyCodeResponse) : AuthState()
        data class SuccessLogout(val response: Response<Unit>) : AuthState()
        data class Error(val message: String) : AuthState()
    }
}
