package com.glorykwon.kykdev.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.glorykwon.kykdev.api.RetrofitTestDto
import com.glorykwon.kykdev.ui.flowtest.TestDataPagingSource
import kotlinx.coroutines.flow.Flow

object TestRepository {

    /**
     * 더미 데이터 조회 (페이징)
     */
    fun getPagingData(): Flow<PagingData<RetrofitTestDto>> {
        return Pager(
            config = PagingConfig(pageSize = 100, enablePlaceholders = false)
            , pagingSourceFactory = { TestDataPagingSource() }
        ).flow
    }

}
