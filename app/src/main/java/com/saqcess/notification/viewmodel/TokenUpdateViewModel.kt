package com.saqcess.notification.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.saqcess.notification.Preferance.MyPreferenceManager
import com.saqcess.notification.datamodel.CommonResponse
import com.saqcess.notification.datamodel.NotificationsResponseModel
import com.saqcess.notification.listner.NotificationListner
import com.saqcess.notification.repository.NotificationRepositoy
import com.saqcess.notification.repository.UploadToken

class TokenUpdateViewModel {
    var commonResponseLiveData: LiveData<CommonResponse>? = null
    var preferance:MyPreferenceManager?=null;
    var notificationListner: NotificationListner?=null;
    fun uploadToken(context: Context?, token: String?) {
        preferance=MyPreferenceManager(context);
        if (commonResponseLiveData == null) {
            commonResponseLiveData = MutableLiveData<CommonResponse>()
            //we will load it asynchronously from server in this method
            val uploadToken:UploadToken=UploadToken();
            commonResponseLiveData = uploadToken.uploadToken(token,context);
             preferance!!.setToken(token);
        } else {
            commonResponseLiveData = UploadToken().uploadToken(token, context)
            preferance!!.setToken(token);
        }
    }

    var notificationLiveData:LiveData<NotificationsResponseModel>?=null

    fun getNotification(context: Context?,page:String?){
        preferance=MyPreferenceManager(context);
        if (notificationLiveData == null) {
            notificationLiveData = MutableLiveData<NotificationsResponseModel>()
            //we will load it asynchronously from server in this method
            val notificationRepositoy:NotificationRepositoy=NotificationRepositoy();
            notificationLiveData = notificationRepositoy.getNotification( preferance!!.toke,context,page);
            notificationListner!!.onsuccessResponse(notificationLiveData as MutableLiveData<NotificationsResponseModel>)
        } else {
            val notificationRepositoy:NotificationRepositoy=NotificationRepositoy();
            notificationLiveData = notificationRepositoy.getNotification(
                preferance!!.toke,
                context,
                page
            );
            notificationListner!!.onsuccessResponse(notificationLiveData as MutableLiveData<NotificationsResponseModel>);
        }
    }

}