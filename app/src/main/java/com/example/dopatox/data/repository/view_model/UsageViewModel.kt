package com.example.dopatox.data.repository.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dopatox.domain.model.usage.UserUsageRequest
import com.example.dopatox.domain.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UsageViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val stateShow = MutableLiveData<UsageState>()

    fun addUserUsageLog(request: UserUsageRequest) {
        stateShow.postValue(UsageState.Loading)
        viewModelScope.launch {
            try {
                val response = repository.addUserUsageLog(request)
                stateShow.postValue(UsageState.Success(response))
            } catch (e: Exception) {
                stateShow.postValue(UsageState.Error(e.message ?: "Unknown error"))
            }
        }
    }

    fun getUserUsageDaily(userId: String, dayDate: String) {
        stateShow.postValue(UsageState.Loading)
        viewModelScope.launch {
            try {
                val response = repository.getUserUsageDaily(userId, dayDate)
                stateShow.postValue(UsageState.Success(response))
            } catch (e: Exception) {
                stateShow.postValue(UsageState.Error(e.message ?: "Unknown error"))
            }
        }
    }

    fun getUserUsageInRange(userId: String, startDate: String, endDate: String) {
        stateShow.postValue(UsageState.Loading)
        viewModelScope.launch {
            try {
                val response = repository.getUserUsageInRange(userId, startDate, endDate)
                stateShow.postValue(UsageState.Success(response))
            } catch (e: Exception) {
                stateShow.postValue(UsageState.Error(e.message ?: "Unknown error"))
            }
        }
    }

    sealed class UsageState {
        data object Loading : UsageState()
        data class Success(val response: Response<Unit>) : UsageState()
        data class Error(val message: String) : UsageState()
    }
}
