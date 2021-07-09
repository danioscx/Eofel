package com.eofelx.eofel.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {

    public static final String TOKEN = "token";
    public static final String STATUS = "status";

    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setToken(Context context, String token) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public static String getToken(Context context) {
        return getSharedPreferences(context).getString(TOKEN, "");
    }

    public static void setStatus(Context context, boolean status) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(STATUS, status);
        editor.apply();
    }

    public static boolean getStatus(Context context) {
        return getSharedPreferences(context).getBoolean(STATUS, false);
    }

    public static void clear(Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(TOKEN);
        editor.remove(STATUS);
        editor.apply();
    }
}
