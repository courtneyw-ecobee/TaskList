package com.example.courtneyw.tasklist.util;

public class Log {

    public static void e(Throwable t) {
        android.util.Log.e("Ecobee", "Exception: ", t);
    }

}
