package com.glorykwon.kykdev.ui.verticalseekbartest

import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.constraintlayout.widget.ConstraintSet
import com.glorykwon.kykdev.databinding.ActivityVerticalSeekbarTestBinding
import com.glorykwon.kykdev.ui.BaseActivity

/**
 * vertical seekbar
 */
class VerticalSeekbarTestActivity : BaseActivity() {

    private val mBinding by lazy { ActivityVerticalSeekbarTestBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        initView()      //뷰 초기화
        initObserver()  //옵저버 초기화
    }

    override fun onResume() {
        super.onResume()
        initData()      //데이터 초기화
    }

    /**
     * 뷰 초기화
     */
    private fun initView() {

        initSeekbar()

    }

    /**
     * 옵저버 초기화
     */
    private fun initObserver() {
    }

    /**
     * 데이터 초기화
     */
    private fun initData() {
    }


    private fun initSeekbar() {

        mBinding.seekbar.setMax(100)
        mBinding.seekbar.setProgress(0)

        mBinding.seekbar.setOnSeekBarChangeListener(object: OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }

            override fun onProgressChanged(
                seekBar: SeekBar, progress: Int,
                fromUser: Boolean
            ) {
                val bias = 1-progress.toFloat()/100.toFloat()
                setBalloon("$progress", bias)
            }
        })

    }

    private fun setBalloon(balloonText: String, bias: Float) {
//        var bias = (useAmountPercent/100).toFloat()
        val constraintSet = ConstraintSet()
        constraintSet.clone(mBinding.layoutSeekbarParent)
        constraintSet.setVerticalBias(mBinding.layoutBalloon.id, bias)
        constraintSet.applyTo(mBinding.layoutSeekbarParent)

        mBinding.txtBalloon.text = balloonText
    }

}