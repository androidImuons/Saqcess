package com.saqcess.notification.Preferance;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class MyPreferenceManager {

    public static final String KEY_ABOUT_US  ="about_us";
    private String TAG = MyPreferenceManager.class.getSimpleName();
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "ozochat";
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String CONTACT_TAG = "MyContacts";
    JSONArray jsonArrayContact= new JSONArray();


    // All Shared Preferences Keys
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_USER_MOBILE = "user_mobile";
    public static final String KEY_PROFILE_PIC = "user_profile_pic";
    public static final String KEY_OTP_VERIFY = "otp_verify";
    public static final String KEY_DEVICE_ID = "user_device_id";
    public static final String KEY_IS_LOGIN = "user_is_login";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_CONTACTS= "contacts";

    // Constructor
    public MyPreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }




    public void setProfilePic(String url){
        editor.putString(KEY_PROFILE_PIC,url);
        editor.commit();
    }
    public void setUserName(String name){
        editor.putString(KEY_USER_NAME,name);
        editor.commit();
    }
    public void setAboutus(String name){
        editor.putString(KEY_ABOUT_US,name);
        editor.commit();
    }
    public void setToken(String name){
        editor.putString(KEY_TOKEN,name);
        editor.commit();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_USER_ID, pref.getString(KEY_USER_ID, null));
        user.put(KEY_USER_NAME, pref.getString(KEY_USER_NAME, null));
        user.put(KEY_USER_MOBILE, pref.getString(KEY_USER_MOBILE, null));
        user.put(KEY_PROFILE_PIC, pref.getString(KEY_PROFILE_PIC, null));
        user.put(KEY_OTP_VERIFY, pref.getString(KEY_OTP_VERIFY, null));
        user.put(KEY_DEVICE_ID, pref.getString(KEY_DEVICE_ID, null));
        user.put(KEY_TOKEN, pref.getString(KEY_TOKEN, null));
        user.put(KEY_ABOUT_US,pref.getString(KEY_ABOUT_US,null));
        // return user
        return user;
    }

    public String getUserId() {
        return pref.getString(KEY_USER_ID, null);
    }


    public String getToke() {
        return pref.getString(KEY_TOKEN, null);
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }


}
