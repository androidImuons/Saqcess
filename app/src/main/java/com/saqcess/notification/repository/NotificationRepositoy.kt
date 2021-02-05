package com.saqcess.notification.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.saqcess.notification.datamodel.NotificationsResponseModel
import com.saqcess.notification.network.AppServices
import com.saqcess.notification.network.ServiceGenerate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationRepositoy {
    private val tag: String?="NotificationRepositoy"

    fun getNotification(
        token: String?,
        context: Context?,
        page: String?
    ): MutableLiveData<NotificationsResponseModel> {

        Log.d(tag, "-----row body--$token")
        var commonResponseLiveData = MutableLiveData<NotificationsResponseModel>()
        var commonResponse: NotificationsResponseModel;
        val apiService: AppServices = ServiceGenerate.createService(AppServices::class.java, token)
        val entityMap: HashMap<String, String> = HashMap<String,String>()
        if (page != null) {
            page?.let { entityMap.put("page_no", it) }
        }
        apiService.getNotification(page)
            .enqueue(object : Callback<NotificationsResponseModel?> {
                override fun onResponse(
                    call: Call<NotificationsResponseModel?>,
                    response: Response<NotificationsResponseModel?>
                ) {
                    if (response.body() == null) {
                        return
                    }
                    if (response.isSuccessful()) {
                        commonResponse = response.body()!!
                        commonResponseLiveData.setValue(commonResponse)
                        Log.d(tag, "-notification  200---" + Gson().toJson(response.body()))
                    } else {
                        Log.d(tag, "-notification fail 200---" + Gson().toJson(response.body()))
                        commonResponse = response.body()!!
                        commonResponseLiveData.setValue(commonResponse)
                        Log.d(tag, "- 200---" + Gson().toJson(response.body()))
                    }
                }

                override fun onFailure(
                    call: Call<NotificationsResponseModel?>,
                    t: Throwable
                ) {
                    Log.d(tag, "------notification on failure --------" + t.message)
                }
            })
        return commonResponseLiveData
    }
}