package com.example.android.androidaop;

import android.util.Log;

public class Animal {
    private static final String TAG = "Animal";
    public void fly() {
        Log.e(TAG, this.toString() + "#fly");
    }

    public void testABC() {
        Log.e(TAG, this.toString() + "#testABC");
    }
}

