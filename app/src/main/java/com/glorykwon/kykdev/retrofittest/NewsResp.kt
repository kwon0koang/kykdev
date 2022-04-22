package com.glorykwon.kykdev.retrofittest

import com.google.gson.annotations.SerializedName

data class NewsDocAttributes(
    @SerializedName("query_string")
    val query: String
)

data class NewsDoc(
    @SerializedName("uid_str")
    val uidStr: String,
    val category: String,   // 해당 문서의 카테고리 및 섹션 정보
    val section: String,    // 해당 문서의 카테고리 및 섹션 정보
    val publisher: String,  // 신문사나 증권사 명 등 해당 문서를 작성한 주체 및 작성자
    val title: String,
    val content: String,
    @SerializedName("content_url")
    val contentUrl: String,     // 원문 기사 주소
    @SerializedName("created_at")
    val createdAt: String,         // 기사 생성 시간
    @SerializedName("updated_at")
    val updatedAt: String,  // 기사 수정 시간
    val attributes: NewsDocAttributes
)

data class NewsData(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("last_page")
    val lastPage: Int,
    @SerializedName("docs")
    val docs: List<NewsDoc>
)

data class NewsKeywords(
    val keyword: String,
    val freq: Int
)

data class NewsEconomyData(
    @SerializedName("uid")
    val uid: String,
    @SerializedName("uid_str")
    val uidStr: String,
    @SerializedName("category")
    val category: String,   // 해당 문서의 카테고리 및 섹션 정보
    @SerializedName("section")
    val section: String,    // 해당 문서의 카테고리 및 섹션 정보
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("topic")
    val topic: String,
    @SerializedName("keywords")
    val keywords: List<NewsKeywords>
)

data class News(
    @SerializedName("economy")
    val economy: List<NewsEconomyData>
)

data class NewsTopics(
    @SerializedName("news")
    val news: News
)

data class NewsTopicData(
    val timestamp: String,
    @SerializedName("topics")
    val topics: NewsTopics
)

data class NewsTopicResp(
    val found: Boolean,
    @SerializedName("data")
    val data: NewsTopicData
)

data class NewsResp(
    @SerializedName("data")
    val data: NewsData
)