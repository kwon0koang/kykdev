package com.glorykwon.kykdev.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glorykwon.kykdev.MainApplication
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    private val TAG = "BaseActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.setActivityContext(this)

        savedInstanceState?.let {
            Timber.tag(TAG).d("onCreate / ${it.getString("test")}")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Timber.tag(TAG).d("onSaveInstanceState")
        outState.putString("test", System.currentTimeMillis().toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState?.let {
            Timber.tag(TAG).d("onRestoreInstanceState / ${it.getString("test")}")
        }
    }

}