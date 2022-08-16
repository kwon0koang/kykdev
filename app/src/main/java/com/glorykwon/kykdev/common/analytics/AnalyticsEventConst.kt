package com.glorykwon.kykdev.common.analytics

/**
 * analytics data
 *  rule
 *      {행동/타이밍}
 *      {행동/타이밍}__{화면}
 *      {행동/타이밍}_{대상}__{화면}
 */
object AnalyticsEventConst {

//    val ENTER           = "enter__"          //화면진입
//    val CLICK           = "click__"          //클릭
//    val SUCCESS         = "success__"        //작업성공

    val enterMain = AnalyticsData(
        eventName = "enter__main"
    )

    val clickRetrofitTest = AnalyticsData(
        eventName = "click__retrofit_test"
    )

    val successRetrofitTest = AnalyticsData(
        eventName = "success__retrofit_test"
    )

    val clickRxPermissionTest = AnalyticsData(
        eventName = "click__rxpermission_test"
    )

    val successRxPermissionTest = AnalyticsData(
        eventName = "success__rxpermission_test"
    )

    val enterRealmTest = AnalyticsData(
        eventName = "enter__realm_test"
    )

    val enterRoomTest = AnalyticsData(
        eventName = "enter__room_test"
    )

}
