# VODUS SDK for Android

[![](https://jitpack.io/v/vodus-research/vodus-android-sdk.svg)](https://jitpack.io/#vodus-research/vodus-android-sdk)
Add it to your build.gradle with:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
and:

```gradle
dependencies {
    compile 'com.github.vodus-research:vodus-android-sdk:{latest version}'
}
```


### Get Survey Example
```java
try {
	    var result = QuestionModule.getCC(applicationContext,GetQuestionRequest(
            containerWidth = <<CONTAINER_WIDTH>>,
            deviceId = <<DEVICE_ID>>,
            containerBackgroundColor = <<BACKGROUND_COLOR>>,
            partnerCode = <<PARTNER_CODE>>,
            env = <<ENV>>)
        );

        if(result.isAvailable)
        {
            layout.addView(result.surveyWebView);
        }
        else{
            //  No survey available or in correct setup
            //  Get more details with: ${result.message}
        }
} catch(Exception e) {
	e.printStackTrace();
}

### Environment Parameter
<<CONTAINER_WIDTH>>
`This is the width of the container or survey in int (pixel)`

<<DEVICE_ID>>
`This is the Device ID for the user`

<<BACKGROUND_COLOR>>
`Survey container background color. Hex color code without hash(#) eg: 000000`

<<PARTNER_CODE>>
`Partner provided by Vodus`

<<env>>
`This is the environment to connect to`
- SANDBOX
- LIVE