package com.saqcess.notification.application;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

public class SaqcessApplication extends Application {
   public static SaqcessApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        instance=this;
    }
    public static SaqcessApplication getInstance(){
        return instance;
    }
}
