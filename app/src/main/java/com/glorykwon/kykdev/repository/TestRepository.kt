package com.glorykwon.kykdev.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.glorykwon.kykdev.dto.TestDto
import com.glorykwon.kykdev.ui.flowtest.DummyPagingSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

object TestRepository {

    /**
     * 더미 데이터 조회
     */
    fun fetchDummyData(): Flow<PagingData<TestDto>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false)
            , pagingSourceFactory = { DummyPagingSource() }
        ).flow
    }

}
