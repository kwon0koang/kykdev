package com.glorykwon.kykdev.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.util.kt.getCurrentMethodName
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    private val mActivityName = this::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("${getCurrentMethodName()} / $mActivityName")
        super.onCreate(savedInstanceState)
        MainApplication.setActivityContext(this)

        savedInstanceState?.let {
            Timber.d("onCreate / ${it.getString("test")}")
        }
    }

    override fun onStart() {
        Timber.d("${getCurrentMethodName()} / $mActivityName")
        super.onStart()
    }

    override fun onResume() {
        Timber.d("${getCurrentMethodName()} / $mActivityName")
        super.onResume()
    }

    override fun onPause() {
        Timber.d("${getCurrentMethodName()} / $mActivityName")
        super.onPause()
    }

    override fun onStop() {
        Timber.d("${getCurrentMethodName()} / $mActivityName")
        super.onStop()
    }

    override fun onDestroy() {
        Timber.d("${getCurrentMethodName()} / $mActivityName")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Timber.d("${getCurrentMethodName()} / $mActivityName")
        outState.putString("test", System.currentTimeMillis().toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState?.let {
            Timber.d("onRestoreInstanceState / ${it.getString("test")}")
        }
    }

}