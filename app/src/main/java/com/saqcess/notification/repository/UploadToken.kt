package com.saqcess.notification.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.saqcess.notification.datamodel.CommonResponse
import com.saqcess.notification.network.AppServices
import com.saqcess.notification.network.ServiceGenerate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.HashMap

class UploadToken {
    val tag = "UploadToken";
    fun uploadToken(
        token: String?,
        context: Context?
    ): MutableLiveData<CommonResponse> {

        Log.d(tag, "-----row body--$token")
        var commonResponseLiveData = MutableLiveData<CommonResponse>()
        var commonResponse: CommonResponse;
        val apiService: AppServices = ServiceGenerate.createService(AppServices::class.java, "")
        val entityMap: HashMap<String, String> = HashMap<String,String>()
        if (token != null) {
            entityMap.put("device_token",token)
        }
        apiService.postToke(entityMap)
            .enqueue(object : Callback<CommonResponse?> {
                override fun onResponse(
                    call: Call<CommonResponse?>,
                    response: Response<CommonResponse?>
                ) {
                    if (response.body() == null) {
                        return
                    }
                    if (response.isSuccessful()) {
                        commonResponse = response.body()!!
                        commonResponseLiveData.setValue(commonResponse)
                        Log.d(tag, "-token update 200---" + Gson().toJson(response.body()))
                    } else {
                        Log.d(tag, "-token fail 200---" + Gson().toJson(response.body()))
                        commonResponse = response.body()!!
                        commonResponseLiveData.setValue(commonResponse)
                    }
                }

                override fun onFailure(
                    call: Call<CommonResponse?>,
                    t: Throwable
                ) {
                    Log.d(tag, "-----failure---------" + t.message)
                }
            })
        return commonResponseLiveData
    }
}