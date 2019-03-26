package com.example.raven.utils;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Consts {
    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;

    public static final String APP_PREFERENCES = "settings";
    public static final String APP_PREFERENCES_NAME = "UserName";
    public static final String APP_PREFERENCES_Id = "UserId";
    public static final String APP_PREFERENCES_CONTACTS = "Contacts";
    public static final String APP_PREFERENCES_CHATS="chats";

    public static GsonBuilder gsonBuilder;
    public static Gson gson;
}
