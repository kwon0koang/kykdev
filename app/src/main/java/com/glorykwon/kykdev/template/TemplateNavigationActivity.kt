package com.glorykwon.kykdev.template

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.glorykwon.kykdev.R
import com.glorykwon.kykdev.databinding.ActivityMainBinding
import com.glorykwon.kykdev.ui.BaseActivity

/**
 * todo
 */
class TemplateNavigationActivity : BaseActivity() {

    private val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
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