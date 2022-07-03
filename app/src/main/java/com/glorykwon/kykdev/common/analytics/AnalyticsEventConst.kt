package com.glorykwon.kykdev.common.analytics

/**
 * analytics data
 */
object AnalyticsEventConst {

//    val ENTER           = "enter__"          //화면진입
//    val CLICK           = "click__"          //클릭
//    val SUCCESS         = "success__"        //작업성공

    val enterMain = AnalyticsData(
        eventName = "enter__main"
    )

    val enterDesignPatternMain = AnalyticsData(
        eventName = "enter__design_pattern_main"
    )

    val enterDesignPatternCommand = AnalyticsData(
        eventName = "enter__design_pattern_command"
    )

}
