package com.glorykwon.kykdev.ui.rxjavatest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.glorykwon.kykdev.databinding.RxjavaTestFragmentBinding
import com.glorykwon.kykdev.ui.BaseFragment
import com.glorykwon.kykdev.ui.main.RxJavaTestViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class RxJavaTestFragment : BaseFragment() {

    private val mBinding by lazy { RxjavaTestFragmentBinding.inflate(layoutInflater) }
    private val mViewModel by viewModels<RxJavaTestViewModel>()

    private val mTextSubject = BehaviorSubject.createDefault("")
    private val mNetworkSubject01 = BehaviorSubject.createDefault(false)
    private val mNetworkSubject02 = BehaviorSubject.createDefault(false)
    private val mNetworkSubject03 = BehaviorSubject.createDefault(false)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        initView()      //뷰 초기화

        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        initData()      //데이터 초기화
    }

    /**
     * 뷰 초기화
     */
    private fun initView() {

        mBinding.etRxjavaTest.addTextChangedListener {
            val str = it.toString()
            mTextSubject.onNext(str)
        }

        mBinding.cbOnNetworkSubject01.setOnCheckedChangeListener { _, isChecked ->
            mNetworkSubject01.onNext(isChecked)
        }

        mBinding.cbOnNetworkSubject02.setOnCheckedChangeListener { _, isChecked ->
            mNetworkSubject02.onNext(isChecked)
        }

        mBinding.cbOnNetworkSubject03.setOnCheckedChangeListener { _, isChecked ->
            mNetworkSubject03.onNext(isChecked)
        }

        mTextSubject
            .doOnNext { mBinding.txtRxjavaTest.text = "$it #1" }
            .debounce(200, TimeUnit.MILLISECONDS)
            .doOnNext { mBinding.txtRxjavaTest.text = "$it #2" }
            .debounce(200, TimeUnit.MILLISECONDS)
            .doOnError { Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show() }
            .subscribeBy {
                mBinding.txtRxjavaTest.text = "$it #3"
            }

        Observable.combineLatest(
            mNetworkSubject01.doOnNext { Timber.d("combineLatest test / mNetworkSubject01:${mNetworkSubject01.value}") }
            , mNetworkSubject02.doOnNext { Timber.d("combineLatest test / mNetworkSubject02:${mNetworkSubject02.value}") }
            , mNetworkSubject03.doOnNext { Timber.d("combineLatest test / mNetworkSubject03:${mNetworkSubject03.value}") }
        ){ result1, result2, result3 ->
            result1 && result2 && result3
        }.subscribeBy { isAllTrue ->
            mBinding.txtCombineLatestTest.text = "is all check ? ${isAllTrue}"
        }

    }

    /**
     * 데이터 초기화
     */
    private fun initData() {
    }

}