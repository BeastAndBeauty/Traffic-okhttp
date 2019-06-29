package com.pyp.traffic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.pyp.traffic.R;
import com.pyp.traffic.Service.AllSenseAndRoadStatusService;

public class HomepageActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Log.i("service开始启动", "成功");
        startService(new Intent(HomepageActivity.this, AllSenseAndRoadStatusService.class));
        findViewById(R.id.fg_hp_Environment).setOnClickListener(this);
        findViewById(R.id.fg_hp_BusMessage).setOnClickListener(this);
        findViewById(R.id.fg_hp_MyCar).setOnClickListener(this);
        findViewById(R.id.fg_hp_TrafficLight).setOnClickListener(this);
        findViewById(R.id.fg_hp_Idea).setOnClickListener(this);
        findViewById(R.id.fg_hp_LightChecking).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(HomepageActivity.this, MainActivity.class);
        switch (v.getId()) {
            case R.id.fg_hp_Environment:
                intent.putExtra("Fragment", "fg_hp_Environment");
                break;
            case R.id.fg_hp_BusMessage:
                intent.putExtra("Fragment", "fg_hp_BusMessage");
                break;
            case R.id.fg_hp_MyCar:
                intent.putExtra("Fragment", "fg_hp_MyCar");
                break;
            case R.id.fg_hp_TrafficLight:
                intent.putExtra("Fragment", "fg_hp_TrafficLight");
                break;
            case R.id.fg_hp_LightChecking:
                intent.putExtra("Fragment", "fg_hp_LightChecking");
                break;
            case R.id.fg_hp_Idea:
                intent.putExtra("Fragment", "fg_hp_Idea");
                break;
        }
        startActivity(intent);
        finish();
    }
}
