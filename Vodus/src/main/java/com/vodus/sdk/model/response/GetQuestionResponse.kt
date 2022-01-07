package com.vodus.sdk.model.response

import android.webkit.WebView
import androidx.annotation.Nullable
import kotlinx.serialization.Serializable

@Serializable
data class GetQuestionResponse(
    val surveyWebView: WebView?,
    val isAvailable: Boolean,
    val message: String
)