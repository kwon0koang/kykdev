package com.glorykwon.kykdev.common.analytics

/**
 * analytics data
 */
data class AnalyticsData(
    val eventName: String,
    val param: Map<String, String>? = null
)