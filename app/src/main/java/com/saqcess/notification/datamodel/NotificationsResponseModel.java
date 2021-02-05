package com.saqcess.notification.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Manish Kumar
 * @since 17/10/18
 */

public class NotificationsResponseModel  {


    @SerializedName("error")
    @Expose
    private boolean error;

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName("message")
    @Expose
    String message;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("data")
    @Expose
    private List<NotificationModel> data;


    public void setData(List<NotificationModel> data) {
        this.data = data;
    }
    public List<NotificationModel> getData() {
        return data;
    }
}
