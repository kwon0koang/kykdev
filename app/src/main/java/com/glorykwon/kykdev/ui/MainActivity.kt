package com.glorykwon.kykdev.ui

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.glorykwon.kykdev.R
import com.glorykwon.kykdev.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var mNavHostFragment: NavHostFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        showSplashScreen()      //android 12 부터 생긴 splash api

        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mNavHostFragment = NavHostFragment.create(R.navigation.navigation_main, Bundle())
        mNavHostFragment?.let {
            supportFragmentManager.beginTransaction()
                .setPrimaryNavigationFragment(it)
                .replace(R.id.navigation_main, it)
                .commitNow()
        }
    }

    private fun showSplashScreen() {
        // Handle the splash screen transition.
        installSplashScreen()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // custom exit on splashScreen
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                // custom animation.
                ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.height.toFloat()
                ).apply {
                    duration = 500
                    // Call SplashScreenView.remove at the end of your custom animation.
                    doOnEnd {
                        splashScreenView.remove()
                    }
                }.run {
                    // Run your animation.
                    start()
                }
            }
        }
    }


}