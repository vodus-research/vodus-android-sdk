package module

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.LinearLayout
import com.vodus.sdk.model.request.GetQuestionRequest
import com.vodus.sdk.model.response.GetQuestionResponse
import android.content.ActivityNotFoundException
import android.util.Log
import android.webkit.*
import module.QuestionModule.Companion.Tag

class QuestionModule {

    companion object {
        /**
         *
         * This is used to get Survey from Vodus
         *
         */
        const val Tag = "Vodus"

        fun getCC(context: Context, request: GetQuestionRequest):GetQuestionResponse  {
            val webView = WebView(context)
            val height = (request.containerWidth * 1.2).toInt()

            Log.d(Tag,"getCC() width: ${request.containerWidth}")
            Log.d(Tag, "getCC() height: $height")

            if(request.containerWidth <=0)
            {
                val error = "Invalid container width: ${request.containerWidth}. Container width must be more than 0"
                Log.d(Tag, error)
                return GetQuestionResponse(isAvailable = false, surveyWebView = webView, message = error)
            }

            val params = "&containerWidth=${request.containerWidth}&containerHeight=$height&deviceId=${request.deviceId}&deviceOS=1&partnerCode=${request.partnerCode}&containerBackgroundColor=${request.containerBackgroundColor.replace("#","")}"

            when (request.env) {
                "LIVE" -> {
                    webView.loadUrl("https://api.vodus.com/sdk/v1/index.html?$params")
                    Log.d(Tag, "Request url [LIVE]: https://api.vodus.com/sdk/v1/index.html?$params")
                }
                "SANDBOX" -> {
                    webView.loadUrl("https://vodus-api-uat.azurewebsites.net/sdk/v1/index.html?$params")
                    Log.d(Tag, "Request url [SANDBOX]: https://vodus-api-uat.azurewebsites.net/sdk/v1/index.html?$params")
                }
                else -> {
                    val error = "Invalid env: [${request.env}]"
                    Log.d(Tag, error)
                    return GetQuestionResponse(isAvailable = false, surveyWebView = webView, message = error)
                }
            }

            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
            webView.settings.domStorageEnabled = true
            webView.settings.javaScriptEnabled = true
            webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
            webView.settings.loadsImagesAutomatically = true
            webView.layoutParams = LinearLayout.LayoutParams(request.containerWidth, height)
            webView.addJavascriptInterface(JSInterface(context), "vodusAndroidSdk")

            return GetQuestionResponse(isAvailable = true, surveyWebView = webView, message = "")
        }
    }
}

class JSInterface(var context: Context) {
    @JavascriptInterface
    fun openUrl(url: String) {
        Log.d(Tag, "OpenUrl: $url")
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setPackage("com.android.chrome")
        try {
            context.startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            // Chrome browser presumably not installed so allow user to choose instead
            intent.setPackage(null)
            context.startActivity(intent)
        }

    }
}
