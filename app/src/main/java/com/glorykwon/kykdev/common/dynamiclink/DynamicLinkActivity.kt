package com.glorykwon.kykdev.common.dynamiclink

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.ui.MainActivity
import com.google.firebase.dynamiclinks.ktx.*
import com.google.firebase.ktx.Firebase
import timber.log.Timber

/**
 * https://firebase.google.com/products/dynamic-links
 * https://firebase.google.com/docs/dynamic-links/android/create?hl=ko
 *
 * 인텐트필터 : https://developer.android.com/guide/components/intents-filters
 *
 * 딥링크 & 앱링크 : https://developer.android.com/training/app-links?hl=ko
 */
class DynamicLinkActivity : Activity() {

    companion object {
        val TAG = this::class.simpleName

        const val KYK_DEV_DOMAIN_URI_PREFIX = "https://kykdev.page.link"
        const val KYK_DEV_SCHEME = "kykdev"
        const val KYK_HOST = "https://0koang.tistory.com"
        const val KYK_SEGMENT_EVENT = "kyksegmentevent"
        const val KYK_EVENT_CODE = "kykeventcode"

        fun shareDynamicLink() {
            Firebase.dynamicLinks.dynamicLink {
                val testEventCode = "testEventCode123"

                link = Uri.parse("$KYK_HOST/$KYK_SEGMENT_EVENT?$KYK_EVENT_CODE=$testEventCode")
                Timber.tag(TAG).d("link:${link}")

                domainUriPrefix = KYK_DEV_DOMAIN_URI_PREFIX

                androidParameters {
                    minimumVersion = 1
                }
                googleAnalyticsParameters {
                    source = "orkut"
                    medium = "social"
                    campaign = "example-promo"
                }
                socialMetaTagParameters {
                    title = "Example of a Dynamic Link"
                    description = "This link works whether the app is installed or not!"
                }
                buildShortDynamicLink()
                    .addOnCompleteListener { task ->
                        try {
                            val shortLink = task.getResult().shortLink

                            val sendIntent = Intent().apply {
                                setAction(Intent.ACTION_SEND)
                                putExtra(Intent.EXTRA_TEXT, shortLink.toString())
                                setType("text/plain")
                            }
                            MainApplication.getActivityContext()?.startActivity(Intent.createChooser(sendIntent, "Share"))
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag(TAG).d("intent : ${intent}")

        handleDeepLink()
    }

    private fun handleDeepLink() {
        val uri = intent.data
        val scheme = uri?.scheme
        when(scheme) {
            //커스텀 스키마
            KYK_DEV_SCHEME -> {
                //kykdev://action
                showDialog("uri : $uri")
            }
            else -> {
                Firebase.dynamicLinks.getDynamicLink(intent)
                    .addOnSuccessListener { pendingDynamicLinkData ->
                        //https://0koang.tistory.com/kyksegmentevent?kykeventcode=testEventCode123
                        val deepLink = pendingDynamicLinkData.link
                        val scheme = deepLink?.scheme
                        val host = deepLink?.host
                        val lastPathSegment = deepLink?.lastPathSegment

                        Timber.tag(TAG).d("deepLink : $deepLink / scheme : $scheme / host : $host / lastPathSegment : $lastPathSegment")

                        when(lastPathSegment) {
                            KYK_SEGMENT_EVENT -> {
                                val eventCode = deepLink.getQueryParameter(KYK_EVENT_CODE)
                                showDialog("event code : $eventCode")
                            }
                            else -> {
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                    .addOnFailureListener { e ->
                        e.printStackTrace()
                    }
            }
        }
    }

    private fun showDialog(msg: String) {
        AlertDialog.Builder(this)
            .setMessage(msg)
            .setPositiveButton("Confirm", null)
            .setOnDismissListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            .create()
            .show()
    }

}