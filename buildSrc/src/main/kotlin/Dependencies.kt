import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.viewModel}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}"

    const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val circleImage = "de.hdodenhof:circleimageview:${Versions.circleImage}"
    const val dotIndicator = "com.tbuonomo:dotsindicator:${Versions.dotIndicator}"

    const val retrofit= "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson= "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    const val viewpager= "androidx.viewpager2:viewpager2:${Versions.viewpager2}"

    const val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore}"

    const val camera = "androidx.camera:camera-camera2:${Versions.camerax}"
    const val cameraLifecycle = "androidx.camera:camera-lifecycle:${Versions.camerax}"
    const val cameraView = "androidx.camera:camera-view:${Versions.camerax}"

    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

    const val pagingRuntime = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    const val roomPaging = "androidx.room:room-paging:${Versions.roomPaging}"

    const val roomRuntime= "androidx.room:room-runtime:${Versions.room}"
    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"

    const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"

    const val junit = "junit:junit:${Versions.junit}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val expresoCore = "androidx.test.espresso:espresso-core:${Versions.expresoCore}"

    //Unit Test
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockkAndroid}"
    const val mockAgent = "io.mockk:mockk-agent:${Versions.mockkAndroid}"
    const val turbine = "app.cash.turbine:turbine:${Versions.turbine}"
}

fun DependencyHandler.room() {
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.room)
    kapt(Dependencies.roomCompiler)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)
}

fun DependencyHandler.paging() {
    implementation(Dependencies.pagingRuntime)
    implementation(Dependencies.roomPaging)
}

fun DependencyHandler.camera() {
    implementation(Dependencies.camera)
    implementation(Dependencies.cameraView)
    implementation(Dependencies.cameraLifecycle)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.loggingInterceptor)
    implementation(Dependencies.gson)
}

fun DependencyHandler.lifecycle() {
    implementation(Dependencies.livedata)
    implementation(Dependencies.viewModel)
}

fun DependencyHandler.navigation() {
    implementation(Dependencies.navUi)
    implementation(Dependencies.navFragment)
}

fun DependencyHandler.image() {
    implementation(Dependencies.glide)
    implementation(Dependencies.circleImage)
    implementation(Dependencies.dotIndicator)
}

fun DependencyHandler.dataStore() {
    implementation(Dependencies.datastore)
}

fun DependencyHandler.swipeRefresh() {
    implementation(Dependencies.swipeRefresh)
}

fun DependencyHandler.shimmer() {
    implementation(Dependencies.shimmer)
}

fun DependencyHandler.viewPager() {
    implementation(Dependencies.viewpager)
}

fun DependencyHandler.testing() {
    testImplementation(Dependencies.mockkAndroid)
    testImplementation(Dependencies.mockAgent)
    testImplementation(Dependencies.turbine)
}

fun DependencyHandler.unitTest() {
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.extJunit)
    androidTestImplementation(Dependencies.expresoCore)
}