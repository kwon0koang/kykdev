package com.glorykwon.kykdev.ui.main

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.subjects.BehaviorSubject

class RxJavaTestViewModel() : ViewModel() {

    val mTextSubject = BehaviorSubject.createDefault("")
    val mNetworkSubject01 = BehaviorSubject.createDefault(false)
    val mNetworkSubject02 = BehaviorSubject.createDefault(false)
    val mNetworkSubject03 = BehaviorSubject.createDefault(false)

}