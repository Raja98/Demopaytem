package com.example.su.demopaytem;

import android.util.Log;

/**
 * Created by su on 24/1/18.
 */

public class Logger {


        public static void MSG(String TAG, String MSG) {
            Log.d(TAG, MSG);
        }

        public static void Error(String TAG, String MSG) {
            Log.e(TAG, MSG);
        }
    }


