package com.glorykwon.kykdev.ui.webviewtest

import android.os.Build
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.glorykwon.kykdev.databinding.ActivityWebviewTestBinding
import com.glorykwon.kykdev.ui.BaseActivity

class WebViewTestActivity : BaseActivity() {

    private val mBinding by lazy { ActivityWebviewTestBinding.inflate(layoutInflater) }

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

        with(mBinding.webview) {
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true // 자바스크립트에서 새 창 실행 허용
            settings.allowFileAccess = true // webview에서 파일 접근 지원
            settings.useWideViewPort = true // 화면 사이즈 맞추기 허용
            settings.setSupportZoom(true) // 화면 줌 허용
            settings.domStorageEnabled = true // 로컬 저장소 허용
            settings.setSupportMultipleWindows(true) // 새 창 띄우기 허용
            settings.loadWithOverviewMode = true // 메타 태그 허용
            settings.builtInZoomControls = true // 화면 확대 축소 허용

            // Enable and setup web view cache
            settings.cacheMode = WebSettings.LOAD_DEFAULT // 브라우저 캐시 허용
            settings.displayZoomControls = true

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                settings.safeBrowsingEnabled = true  // api 26
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                settings.mediaPlaybackRequiresUserGesture = false
            }

            settings.allowContentAccess = true
            settings.setGeolocationEnabled(true)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                settings.allowUniversalAccessFromFileURLs = true
            }

            webViewClient = BaseWebViewClient().apply {
                setOnWebVieClientListener(
                    onPageStartedListener = null,
                    onPageFinishedListener = null,
                    shouldOverrideUrlLoadingListener = null,
                )
            }

            loadUrl("https://www.naver.com")

        }
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

}