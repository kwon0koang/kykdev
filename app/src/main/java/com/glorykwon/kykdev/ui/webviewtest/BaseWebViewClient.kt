package com.glorykwon.kykdev.ui.webviewtest

import android.graphics.Bitmap
import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import timber.log.Timber

class BaseWebViewClient : WebViewClient() {

    private var mOnPageStartedListener: ((String?) -> Unit)? = null
    private var mOnPageFinishedListener: ((String?) -> Unit)? = null
    private var mShouldOverrideUrlLoadingListener: ((String?) -> Unit)? = null

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        Timber.d("BaseWebViewClient / shouldOverrideUrlLoading / url : ${request?.url}")
        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
        Timber.d("BaseWebViewClient / doUpdateVisitedHistory / url : $url")
        super.doUpdateVisitedHistory(view, url, isReload)
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        Timber.d("BaseWebViewClient / onPageStarated / url : $url")
        super.onPageStarted(view, url, favicon)
        mOnPageStartedListener?.invoke(url)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        Timber.d("BaseWebViewClient / onPageFinished / url : $url")
        super.onPageFinished(view, url)
        mOnPageFinishedListener?.invoke(url)
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        Timber.d("BaseWebViewClient / onReceivedSslError")
    }

    /**
     * WebViewclient Callback Listner
     */
    fun setOnWebVieClientListener(
        onPageStartedListener: ((String?) -> Unit)? = null,
        onPageFinishedListener: ((String?) -> Unit)? = null,
        shouldOverrideUrlLoadingListener: ((String?) -> Unit)? = null
    ) {
        mOnPageStartedListener = onPageStartedListener
        mOnPageFinishedListener = onPageFinishedListener
        mShouldOverrideUrlLoadingListener = shouldOverrideUrlLoadingListener
    }

}