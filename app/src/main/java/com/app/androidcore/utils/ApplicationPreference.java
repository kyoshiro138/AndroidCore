package com.app.androidcore.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class ApplicationPreference {
    private static final String DEBUG_TAG = "APPLICATION_PREFERENCE";
    private static final boolean LOG_DEBUG = true;

    public static final String PREFERENCE_TYPE_STRING = "PREFERENCE_TYPE_STRING";
    public static final String PREFERENCE_TYPE_INTEGER = "PREFERENCE_TYPE_INTEGER";
    public static final String PREFERENCE_TYPE_BOOLEAN = "PREFERENCE_TYPE_BOOLEAN";
    public static final String PREFERENCE_TYPE_FLOAT = "PREFERENCE_TYPE_FLOAT";
    public static final String PREFERENCE_TYPE_LONG = "PREFERENCE_TYPE_LONG";

    private Context mContext;
    private SharedPreferences mSharedPreferences;

    public ApplicationPreference(Context context) {
        this(context, context.getPackageName());
    }

    public ApplicationPreference(Context context, String PrefName) {
        this(context, PrefName, Context.MODE_PRIVATE);
    }

    public ApplicationPreference(Context context, String PrefName, int mode) {
        mContext = context;
        mSharedPreferences = context.getSharedPreferences(PrefName, mode);
    }

    public boolean putValue(String key, Object value, String valueType) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        switch (valueType) {
            case PREFERENCE_TYPE_STRING:
                editor.putString(key, String.valueOf(value));
                break;
            case PREFERENCE_TYPE_INTEGER:
                editor.putInt(key, (int) value);
                break;
            case PREFERENCE_TYPE_BOOLEAN:
                editor.putBoolean(key, (boolean) value);
                break;
            case PREFERENCE_TYPE_FLOAT:
                editor.putFloat(key, (float) value);
                break;
            case PREFERENCE_TYPE_LONG:
                editor.putLong(key, (long) value);
                break;
            default:
                editor.apply();

                if (LOG_DEBUG) {
                    Log.d(DEBUG_TAG, "PREFERENCE TYPE NOT SUPPORTED");
                }
                return false;
        }

        editor.apply();

        if (LOG_DEBUG) {
            String logMessage = String.format("PREFERENCE PUT [TYPE:%s] [KEY:%s] [VALUE:%s]", valueType, key, value.toString());
            Log.d(DEBUG_TAG, logMessage);
        }
        return true;
    }

    public Object getValue(String key, Object defValue, String valueType) {
        Object value;
        switch (valueType) {
            case PREFERENCE_TYPE_STRING:
                value = mSharedPreferences.getString(key, String.valueOf(defValue));
                break;
            case PREFERENCE_TYPE_INTEGER:
                value = mSharedPreferences.getInt(key, (int) defValue);
                break;
            case PREFERENCE_TYPE_BOOLEAN:
                value = mSharedPreferences.getBoolean(key, (boolean) defValue);
                break;
            case PREFERENCE_TYPE_FLOAT:
                value = mSharedPreferences.getFloat(key, (float) defValue);
                break;
            case PREFERENCE_TYPE_LONG:
                value = mSharedPreferences.getLong(key, (long) defValue);
                break;
            default:
                if (LOG_DEBUG) {
                    Log.d(DEBUG_TAG, "PREFERENCE TYPE NOT SUPPORTED");
                }
                return null;
        }

        if (LOG_DEBUG) {
            String logMessage = String.format("PREFERENCE GET [TYPE:%s] [KEY:%s] [VALUE:%s]", valueType, key, value.toString());
            Log.d(DEBUG_TAG, logMessage);
        }
        return value;
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor = editor.remove(key);
        editor.apply();

        if (LOG_DEBUG) {
            String logMessage = String.format("PREFERENCE REMOVE [KEY:%s]", key);
            Log.d(DEBUG_TAG, logMessage);
        }
    }
}
