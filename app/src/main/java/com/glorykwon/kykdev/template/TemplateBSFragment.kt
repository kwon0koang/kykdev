package com.glorykwon.kykdev.template

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.glorykwon.kykdev.R
import com.glorykwon.kykdev.databinding.FragmentBottomSheetCommonBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber

class TemplateBSFragment(
    val mTextTitle: String? = null,             // 제목
    val mTextContent: String? = null,           // 내용
    val mTextBtnCancel: String? = null,         // 취소 버튼 텍스트
    val mTextBtnConfirm: String? = null,        // 확인 버튼 텍스트
    val mCancelListener: ((result: Boolean) -> Unit)? = null,
    val mConfirmListener: ((result: Boolean) -> Unit)? = null
): BottomSheetDialogFragment() {

    private val mBinding by lazy { FragmentBottomSheetCommonBinding.inflate(layoutInflater) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            val bottomSheet = dialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.transparent)) // 백그라운드 투명

            val behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED // Expanded 상태로 오픈
            behavior.isHideable = true      // Drag를 통한 숨김 처리
            behavior.skipCollapsed = true   // 중간 Collapsed 단계 생략
            behavior.isDraggable = true
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return with(mBinding) {

            // 뷰 초기화
            initView()

            root
        }
    }

    /**
     * 뷰 초기화
     */
    private fun initView(){

        mTextTitle?.let {
            mBinding.txtTitle.text = it
            mBinding.txtTitle.visibility = View.VISIBLE
        } ?: run {
            mBinding.txtTitle.visibility = View.GONE
        }
        mTextContent?.let {
            mBinding.txtContent.text = it
        }
        mTextBtnCancel?.let {
            mBinding.btnCancel.text = it
            mBinding.btnCancel.visibility = View.VISIBLE
        } ?: run {

            mBinding.btnCancel.visibility = View.GONE
        }
        mTextBtnConfirm?.let {
            mBinding.btnConfirm.text = it
        }

        // 클릭 리스너 셋팅
        mBinding.btnCancel.clicks().subscribeBy {
            mCancelListener?.invoke(true)
            dismiss()
        }
        mBinding.btnConfirm.clicks().subscribeBy {
            mConfirmListener?.invoke(true)
            dismiss()
        }

        mBinding.layoutContent.setOnTouchListener(preventDragTouchListener)
        mBinding.txtContent.setOnTouchListener(preventDragTouchListener)
        mBinding.btnCancel.setOnTouchListener(preventDragTouchListener)
        mBinding.btnConfirm.setOnTouchListener(preventDragTouchListener)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setOnBackPress()
    }

    /**
     * 백버튼 클릭시 처리
     */
    private lateinit var callback: OnBackPressedCallback
    private fun setOnBackPress() {
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                dismiss()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

    /**
     * 드래그 막기 위함
     */
    private var dragCnt = 0             // ACTION_MOVE 에서 증가
    fun isClicked() = dragCnt <= 5      // 5 이하이면 클릭으로 판단
    private val preventDragTouchListener = View.OnTouchListener { v, event ->
        Timber.d("preventDragTouchListener / ${event.action}")
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                dragCnt = 0
                dialog?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)?.let { bottomSheet ->
                    bottomSheet.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.transparent)) // 백그라운드 투명
                    val behavior = BottomSheetBehavior.from(bottomSheet)
                    behavior.isDraggable = false
                }
            }
            MotionEvent.ACTION_UP -> {
                Timber.d("preventDragTouchListener / isClicked:${isClicked()} / dragCnt:$dragCnt")
                if (isClicked()) {
                    v.performClick()
                }

                dragCnt = 0
                dialog?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)?.let { bottomSheet ->
                    bottomSheet.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.transparent)) // 백그라운드 투명
                    val behavior = BottomSheetBehavior.from(bottomSheet)
                    behavior.isDraggable = true
                }
            }
            MotionEvent.ACTION_MOVE -> {
                dragCnt++
            }
        }
        true
    }

}