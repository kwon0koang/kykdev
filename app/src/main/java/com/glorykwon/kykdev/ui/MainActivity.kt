package com.glorykwon.kykdev.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.glorykwon.kykdev.R
import com.glorykwon.kykdev.databinding.MainActivityBinding
import kotlinx.coroutines.*
import timber.log.Timber

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

        runBlocking {
//            val a = lifecycleScope.launch {
//                for(i in 1..10){
//                    Timber.d("kyk / a / $i"
//                    delay(100)
//                }
//            }

//            Timber.d("kyk / aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")

//            val b = lifecycleScope.async {
//                for(i in 1..10){
//                    Timber.d("kyk / b / $i")
//                    delay(100)
//                }
//            }.await()
//            val c = lifecycleScope.async {
//                for(i in 1..10){
//                    Timber.d("kyk / c / $i")
//                    delay(100)
//                }
//            }.await()
//            val d = lifecycleScope.async {
//                for(i in 1..10){
//                    Timber.d("kyk / d / $i")
//                    delay(100)
//                }
//            }.await()

            listOf(async {
                for(i in 1..10){
                    Timber.d("kyk / b / $i")
                    delay(100)
                }
            },async {
                for(i in 1..10){
                    Timber.d("kyk / c / $i")
                    delay(100)
                }
            },async {
                for(i in 1..10){
                    Timber.d("kyk / d / $i")
                    delay(100)
                }
            }).awaitAll()

            for(i in 1..10){
                Timber.d("kyk / e / $i")
                delay(100)
            }

//            val b1 = b.await()
//            val c1 = c.await()
//            val d1 = d.await()

            Timber.d("kyk / 555555555555555555555555555")


        }


    }




}