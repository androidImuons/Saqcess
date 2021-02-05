package com.saqcess.notification.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationModel extends AppBaseModel {
    @SerializedName("title")
    @Expose
   private String title;

    @SerializedName("notification")
    @Expose
     private  String notification;

    @SerializedName("sender_type")
    @Expose
   private String sender_type;

    @SerializedName("created")
    @Expose
   private long created;

    @SerializedName("image_thumb")
    @Expose
   private String image_thumb;

    @SerializedName("image_large")
    @Expose
    private String image_large;

    boolean readMore = true;

    public String getTitle() {
        return getValidString(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotification() {
        return getValidString(notification);
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getSender_type() {
        return getValidString(sender_type);
    }

    public void setSender_type(String sender_type) {
        this.sender_type = sender_type;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getImage_thumb() {
        return getValidString(image_thumb);
    }

    public void setImage_thumb(String image_thumb) {
        this.image_thumb = image_thumb;
    }

    public String getImage_large() {
        return getValidString(image_large);
    }

    public void setImage_large(String image_large) {
        this.image_large = image_large;
    }

    public boolean isReadMore() {
        return readMore;
    }

    public void setReadMore(boolean readMore) {
        this.readMore = readMore;
    }

    public boolean isSendByAdmin() {
        return getSender_type().equals("ADMIN");
    }



    public String getCreatedDateText() {
        return getFormatedDateString(DATETIME_MMMDDYYYY_hh_mm_ss_a, getCreated());
    }

    public String getCreatedTimeText() {
        return getFormatedDateString(hh_mm_a, getCreated());
    }
}
