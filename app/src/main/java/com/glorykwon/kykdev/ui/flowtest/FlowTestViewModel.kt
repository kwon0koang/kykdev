package com.glorykwon.kykdev.ui.flowtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.glorykwon.kykdev.dto.TestDto
import com.glorykwon.kykdev.repository.TestRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

class FlowTestViewModel : ViewModel() {

    /**
     * 치킨 검색
     */
//    fun searchChicken(searchText: String): Flow<List<String>> {
//        val flow: Flow<List<String>> = flow {
//            val result = TestRepository.searchChicken(searchText)
//            emit(result)
//        }
//        return flow
//    }

    /**
     * 더미 데이터 조회
     */
    fun fetchDummyData(): Flow<PagingData<TestDto>>? {
        try {
            val result = TestRepository.fetchDummyData().cachedIn(viewModelScope)
            return result
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    private val _fetchTestStr = MutableStateFlow("")
    val fetchTestStr = _fetchTestStr




}