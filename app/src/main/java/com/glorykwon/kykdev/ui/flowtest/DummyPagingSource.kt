package com.glorykwon.kykdev.ui.flowtest

import androidx.paging.PagingSource
import com.glorykwon.kykdev.dto.TestDto
import com.glorykwon.kykdev.util.kt.toKykInt
import kotlinx.coroutines.delay
import timber.log.Timber

/**
 * 더미 페이징 소스
 */
class DummyPagingSource(): PagingSource<String, TestDto>() {

    val mDummyChickenList = arrayListOf(
        "범프리카인생치킨",
        "더꼬치다",
        "치킨대가",
        "처갓집",
        "오순살",
        "교촌치킨",
        "네네치킨",
        "후라이드참잘하는집",
        "박옥녀",
        "치킨더홈",
        "꾸브라꼬숯불두마리치킨",
        "갓튀긴후라이드",
        "쌀통닭",
        "21세기굽는치킨",
        "림스치킨",
        "치킨신드롬",
        "철인7호",
        "동키치킨",
        "굽네치킨",
        "땅땅치킨",
        "썬더치킨",
        "BBQ",
        "BHC",
        "장수통닭",
        "동근이숯불두마리치킨",
        "갓튀긴후라이드"
    )

    override suspend fun load(params: LoadParams<String>): LoadResult<String, TestDto> {
        try {
            val currentKey = params.key ?: ""

            //치킨 로드
            val list = arrayListOf<TestDto>()
            mDummyChickenList.forEach { chicken ->
                list.add(TestDto().apply {
                    value01 = "$chicken $currentKey"
                })
            }
            delay(1000)

            val nextKey = (currentKey.toKykInt()+1).toString()

            val msg = "currentKey:$currentKey / nextKey:$nextKey"
            Timber.d(msg)

            return LoadResult.Page(
                data = list.toList(),
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