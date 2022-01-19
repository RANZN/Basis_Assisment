# Basis Assisment


<h3>DEMO</h3>

<img height='450' src="https://user-images.githubusercontent.com/40376163/150124463-3924bc54-3584-46aa-a647-a760f5a532b1.gif"/>


# Open Source Library
* [Retrofit](https://square.github.io/retrofit/)
* [Glide](https://github.com/bumptech/glide)
* [RxJava](https://github.com/ReactiveX/RxAndroid)

# Things we used while making this application
* ViewPager
* RecyclerView
* MVVM
* Data Binding

# Tech Stack ✨
* Kotlin
* Android Studio
* RxJava

# Dependencies
```
    plugins {
      id 'com.android.application'
      id 'kotlin-android'
      id 'kotlin-kapt'
    }

    buildFeatures {
        dataBinding true
    }
```
```
    //Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:3.12.3'
    implementation 'com.squareup.retrofit2:converter-scalars:2.3.0'

    //Rx Java
    implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'
    implementation 'io.reactivex.rxjava3:rxjava:3.1.3'
    implementation 'com.squareup.retrofit2:adapter-rxjava3:2.9.0'

    //Glide
    implementation 'com.github.bumptech.glide:glide:4.12.0'

```
