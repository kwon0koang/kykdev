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
    fun fetchTestData(): Flow<PagingData<RetrofitTestDto>>? {
        try {
            val result = TestRepository.fetchPagingData().cachedIn(viewModelScope)
            return result
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

}