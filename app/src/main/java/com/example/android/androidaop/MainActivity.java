package com.example.android.androidaop;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.textView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View testAop) {
                testAop(testAop);
            }
        });
        findViewById(R.id.before).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testBeford();
            }
        });
    }

    @TBehaviorTrace(value = "name")
    public void testAop(View view) {
        SystemClock.sleep(3000);
        Log.i(TAG,"  摇到一个嫩模：  约不约");
    }

    public void testBeford(){
        System.out.println("执行 testBeford（）");
        Animal animal = new Animal();
        animal.fly();
        animal.testABC();
    }
}
