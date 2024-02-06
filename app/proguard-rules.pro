# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Signature holds information about generic types. Generic types are used to provide additional type
# information at compile-time and are erased (removed) during the compilation process.
# The Signature attribute helps retain this generic type information for runtime reflection.

-keep class com.aditya.weather.model.** { *; }
-keepclassmembers class com.aditya.weather.model.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**
-keep class okhttp3.**{ *;}
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**
-keep class net.sqlcipher.** { *; }
-dontwarn net.sqlcipher.**
