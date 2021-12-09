package com.vodus.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vodus.sdk.model.request.GetQuestionRequest
import module.QuestionModule
import android.widget.LinearLayout
import android.util.DisplayMetrics
import android.provider.Settings

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //var view = layoutInflater.inflate(R.layout.activity_main,null);
        var layout = findViewById<LinearLayout>(R.id.ads);

        val displayMetrics = DisplayMetrics();
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        var w = displayMetrics.widthPixels;

        val deviceID = Settings.Secure.getString(contentResolver,
            Settings.Secure.ANDROID_ID)

        var result = QuestionModule.getCC(applicationContext,GetQuestionRequest(
            containerWidth = w,
            deviceId = deviceID,
            containerBackgroundColor = "ddd",
            partnerCode = "")
        );

        if(result.isAvailable)
        {
            layout.addView(result.surveyWebView);
        }
    }
}