package com.saqcess.notification.listner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.saqcess.notification.datamodel.NotificationsResponseModel
import com.saqcess.notification.repository.NotificationRepositoy

interface NotificationListner {

   fun onsuccessResponse(notificationLiveData: MutableLiveData<NotificationsResponseModel>)
}