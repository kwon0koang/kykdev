package com.glorykwon.kykdev.ui.pagingtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.glorykwon.kykdev.repository.TestRepository
import com.glorykwon.kykdev.common.api.RetrofitTestDto
import kotlinx.coroutines.flow.Flow

class PagingTestViewModel : ViewModel() {

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