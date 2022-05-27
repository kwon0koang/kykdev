package com.glorykwon.kykdev.ui.designpatterntest

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.glorykwon.kykdev.R
import com.glorykwon.kykdev.databinding.DesignPatternTestActivityBinding
import com.glorykwon.kykdev.ui.BaseActivity

class DesignPatternTestActivity : BaseActivity() {

    private val mBinding by lazy { DesignPatternTestActivityBinding.inflate(layoutInflater) }
    private var mNavHostFragment: NavHostFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mNavHostFragment = NavHostFragment.create(R.navigation.navigation_design_pattern, Bundle())
        mNavHostFragment?.let {
            supportFragmentManager.beginTransaction()
                .setPrimaryNavigationFragment(it)
                .replace(R.id.navigation_design_pattern, it)
                .commitNow()
        }
    }




}