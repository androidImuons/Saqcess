package com.saqcess.notification.network;

import com.saqcess.notification.datamodel.CommonResponse;
import com.saqcess.notification.datamodel.NotificationsResponseModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AppServices {

    @FormUrlEncoded
    @POST("insert_device_token")
    Call<CommonResponse> postToke(@FieldMap Map<String, String> entityMap);


    @GET("get_push_notifications/{page_no}")
    Call<NotificationsResponseModel> getNotification(@Path(value="page_no", encoded=true) String pageSize);
}
