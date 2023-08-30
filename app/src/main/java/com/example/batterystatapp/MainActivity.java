package com.example.batterystatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    BatteryManager batteryManager;
    Handler handler = new Handler(Looper.getMainLooper());
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        batteryManager = (BatteryManager) getSystemService(Context.BATTERY_SERVICE);
        textView = findViewById(R.id.center_label);
        getWindow().addFlags(FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        handler.postDelayed new Runnable()
        {
            @Override
            public void run()
            {
                int batteryCapacity = batteryManager.getBatteryCapacity();
                int remainingBattery = batteryManager.getRemainingBattery();
                int batteryVoltage = batteryManager.getBatteryVoltage();
                Log.e("Main", "Energy Counter: " + batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER) + ", remaining: " + batteryManager.getRemainingBattery());
                textView.setText("Battery Capacity: " + (batteryCapacity) + " mAh\nBattery Remaining: " + remainingBattery + " mAh\nBattery Voltage: " + (batteryVoltage / 1000.0) + " V");
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }
}