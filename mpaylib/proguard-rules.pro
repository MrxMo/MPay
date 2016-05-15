# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/moguangjian/android/software/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# 支付宝支付
#-libraryjars libs/alipaySdk-20160427.jar
-dontwarn com.alipay.**
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}

# 微信支付
#-libraryjars libs/libammsdk.jar
-dontwarn com.tencent.**

# Mpay
#-keep class MPayConfig
#-keep public class * class com.mrmo.mpaylib.model.** {*;}                                  #保持某包下类的所有子类
#-keep public interface com.mrmo.mpaylib.** {*;}                                   #保持某包下接口的所有子类
#
#-keep public class extends com.mrmo.mpaylib.MWeChatPayResultActivity # 保持哪些类不被混淆
