package com.glorykwon.kykdev.ui.rxjavatest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.glorykwon.kykdev.databinding.RxjavaTestFragmentBinding
import com.glorykwon.kykdev.ui.BaseFragment
import com.glorykwon.kykdev.ui.main.RxJavaTestViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

class RxJavaTestFragment : BaseFragment() {

    private val mBinding by lazy { RxjavaTestFragmentBinding.inflate(layoutInflater) }
    private val mViewModel by viewModels<RxJavaTestViewModel>()

    private var mTestResultText = ""

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
            mViewModel.mTextSubject.onNext(str)
        }

        mBinding.cbOnNetworkSubject01.setOnCheckedChangeListener { _, isChecked ->
            mViewModel.mNetworkSubject01.onNext(isChecked)
        }

        mBinding.cbOnNetworkSubject02.setOnCheckedChangeListener { _, isChecked ->
            mViewModel.mNetworkSubject02.onNext(isChecked)
        }

        mBinding.cbOnNetworkSubject03.setOnCheckedChangeListener { _, isChecked ->
            mViewModel.mNetworkSubject03.onNext(isChecked)
        }

        //subscribeOn은 여러번 호출되더라도 맨 처음의 호출만 영향을 주며 어디에 위치하든 상관없음.
        //observeOn은 여러번 호출될 수 있으며 이후에 실행되는 연산에 영향을 주므로 위치가 중요.
        mViewModel.mTextSubject
            .doOnNext { mTestResultText = "$it\n${Thread.currentThread().name} #1" }
            .subscribeOn(Schedulers.io())
            .doOnNext { mTestResultText += "\n${Thread.currentThread().name} #2" }
            .observeOn(Schedulers.io())
            .doOnNext { mTestResultText += "\n${Thread.currentThread().name} #3" }
            .observeOn(Schedulers.computation())
            .doOnNext { mTestResultText += "\n${Thread.currentThread().name} #4" }
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                mTestResultText += "\n${Thread.currentThread().name} #5"
                mBinding.txtRxjavaTest.text = mTestResultText
            }

        Observable.combineLatest(
            mViewModel.mNetworkSubject01.doOnNext { Timber.d("combineLatest test / mNetworkSubject01:${it}") }
            , mViewModel.mNetworkSubject02.doOnNext { Timber.d("combineLatest test / mNetworkSubject02:${it}") }
            , mViewModel.mNetworkSubject03.doOnNext { Timber.d("combineLatest test / mNetworkSubject03:${it}") }
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