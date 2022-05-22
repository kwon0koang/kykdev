package com.glorykwon.kykdev.ui.flowtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.glorykwon.kykdev.repository.TestRepository
import com.glorykwon.kykdev.api.RetrofitTestDto
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow

class FlowTestViewModel : ViewModel() {

    /**
     * 데이터 조회
     */
    fun getTestData(): Flow<PagingData<RetrofitTestDto>>? {
        try {
            val result = TestRepository.getPagingData().cachedIn(viewModelScope)
            return result
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

}