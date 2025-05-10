package com.example.dopatox.data.repository.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dopatox.domain.model.challenge.ChallengeRequest
import com.example.dopatox.domain.model.challenge.ChallengeResponse
import com.example.dopatox.domain.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ChallengeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val stateShow = MutableLiveData<ChallengeState>()

    fun getChallenges() {
        stateShow.postValue(ChallengeState.Loading)
        viewModelScope.launch {
            try {
                val response = repository.getChallenges()
                stateShow.postValue(ChallengeState.SuccessGet(response.body()!!))
            } catch (e: Exception) {
                stateShow.postValue(ChallengeState.Error(e.message ?: "Unknown error"))
            }
        }
    }

    fun addChallenge(request: ChallengeRequest) {
        stateShow.postValue(ChallengeState.Loading)
        viewModelScope.launch {
            try {
                val response = repository.addChallenge(request)
                stateShow.postValue(ChallengeState.Success(response))
            } catch (e: Exception) {
                stateShow.postValue(ChallengeState.Error(e.message ?: "Unknown error"))
            }
        }
    }

    fun updateChallenge(id: Int, request: ChallengeRequest) {
        stateShow.postValue(ChallengeState.Loading)
        viewModelScope.launch {
            try {
                val response = repository.updateChallenge(id, request)
                stateShow.postValue(ChallengeState.Success(response))
            } catch (e: Exception) {
                stateShow.postValue(ChallengeState.Error(e.message ?: "Unknown error"))
            }
        }
    }

    fun deleteChallenge(id: Int) {
        stateShow.postValue(ChallengeState.Loading)
        viewModelScope.launch {
            try {
                val response = repository.deleteChallenge(id)
                stateShow.postValue(ChallengeState.Success(response))
            } catch (e: Exception) {
                stateShow.postValue(ChallengeState.Error(e.message ?: "Unknown error"))
            }
        }
    }

    sealed class ChallengeState {
        data object Loading : ChallengeState()
        data class Success(val response: Response<Unit>) : ChallengeState()
        data class SuccessGet(val response: List<ChallengeResponse>) : ChallengeState()
        data class Error(val message: String) : ChallengeState()
    }
}
