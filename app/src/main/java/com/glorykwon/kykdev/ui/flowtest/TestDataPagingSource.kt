package com.glorykwon.kykdev.ui.flowtest

import androidx.paging.PagingSource
import com.glorykwon.kykdev.api.RetrofitTestApiService
import com.glorykwon.kykdev.api.RetrofitTestDto
import com.glorykwon.kykdev.util.kt.toKykInt
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.system.measureTimeMillis

/**
 * 페이징 소스
 */
class TestDataPagingSource(): PagingSource<String, RetrofitTestDto>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, RetrofitTestDto> {
        try {

            val currentKey = params.key ?: ""

            val dataList = mutableListOf<RetrofitTestDto>()
            measureTimeMillis {
                coroutineScope {
                    listOf(async(Dispatchers.IO) {
                        dataList.addAll(RetrofitTestApiService.getInstance().searchByUserId(1))
                    }, async(Dispatchers.IO) {
                        dataList.addAll(RetrofitTestApiService.getInstance().searchByUserId(2))
                    }, async(Dispatchers.IO) {
                        dataList.addAll(RetrofitTestApiService.getInstance().searchByUserId(3))
                    }).awaitAll()
                }
            }.also {
                Timber.d("load / time:${it} / dataSize:${dataList.size}")
            }

            val nextKey = (currentKey.toKykInt()+1).toString()

            return LoadResult.Page(
                data = dataList,
                prevKey = null,
                nextKey = if (nextKey.isNullOrEmpty()) null else nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Page(
                data = ArrayList(),
                prevKey = null,
                nextKey = ""
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}