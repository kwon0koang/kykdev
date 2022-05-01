package com.glorykwon.kykdev.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.glorykwon.kykdev.R
import com.glorykwon.kykdev.databinding.MainActivityBinding

class MainActivity : BaseActivity() {

    private val mBinding by lazy { MainActivityBinding.inflate(layoutInflater) }
    private var mNavHostFragment: NavHostFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
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




}