package com.saqcess.notification.network

import android.util.Log
import com.saqcess.notification.application.SaqcessApplication
import com.saqcess.notification.util.DeviceUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceGenerate {

    //    __________________________________________APP CONSTANT___________________________



    companion object {

        fun <S> createService(serviceClass: Class<S>, nothing: Nothing?): S {
            return createService(
                serviceClass,
                null
            )
        }
        var HEADER_KEY_TOKEN = "TOKEN"
        var HEADER_KEY_LANG = "LANG"
        var HEADER_KEY_DEVICE_ID = "DEVICE-ID"
        var HEADER_KEY_DEVICETYPE = "DEVICETYPE"
        var HEADER_KEY_DEVICEINFO = "DEVICEINFO"
        var HEADER_KEY_APPINFO = "APPINFO"

        var HEADER_LANG_VALUE = "1"
        var HEADER_DEVICETYPE_VALUE = "A"
        var API_BASE_URL = "https://saqcess.com/saqcess-notification-app/admin/apis/mobile/v1/"
        private val httpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
        private val apiService: AppServices? = null

        private val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        fun <S> createService(serviceClass: Class<S>?, authToken: String?): S {
            if (authToken.equals("")) {
                httpClient.interceptors()
                    .add(Interceptor { chain ->
                        val original = chain.request()
                        var requestBuilder = original.newBuilder()
                            .header(HEADER_KEY_LANG, HEADER_LANG_VALUE)
                            .header(
                                HEADER_KEY_DEVICE_ID,
                                DeviceUtil.getUniqueDeviceId(SaqcessApplication.getInstance().getApplicationContext())
                            )
                            .header(HEADER_KEY_DEVICETYPE, HEADER_DEVICETYPE_VALUE)
                            .header(
                                HEADER_KEY_DEVICEINFO,
                                DeviceUtil.getDeviceInfoModal().toString()
                            )
                            .header(HEADER_KEY_APPINFO, DeviceUtil.getDeviceInfoModal().appInfo)
                            .method(original.method(), original.body())
                        val request = requestBuilder.build()
                        chain.proceed(request)
                    })
            }else{
                httpClient.interceptors()
                    .add(Interceptor { chain ->
                        val original = chain.request()
                        var requestBuilder = original.newBuilder()
                            .header("token",authToken)
                            .header(HEADER_KEY_LANG, HEADER_LANG_VALUE)
                            .header(
                                HEADER_KEY_DEVICE_ID,
                                DeviceUtil.getUniqueDeviceId(SaqcessApplication.instance)
                            )
                            .header(HEADER_KEY_DEVICETYPE, HEADER_DEVICETYPE_VALUE)
                            .header(
                                HEADER_KEY_DEVICEINFO,
                                DeviceUtil.getDeviceInfoModal().toString()
                            )
                            .header(HEADER_KEY_APPINFO, DeviceUtil.getDeviceInfoModal().appInfo)
                            .method(original.method(), original.body())
                        val request = requestBuilder.build()
                        chain.proceed(request)
                    })
            }
            Log.d("toke", "Bearer $authToken")
            Log.d("device id",  DeviceUtil.getUniqueDeviceId(SaqcessApplication.instance));
            val client: OkHttpClient = httpClient.build()
            val retrofit: Retrofit = builder.client(client).build()
            return retrofit.create(serviceClass)
        }

    }
    private val HTTP_TIMEOUT = TimeUnit.SECONDS.toMillis(60)
}