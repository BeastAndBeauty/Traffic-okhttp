package com.pyp.traffic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.pyp.traffic.Fragment.BusMessageFragment;
import com.pyp.traffic.Fragment.CarParkingFragment;
import com.pyp.traffic.Fragment.EnvironmentIndexFragment;
import com.pyp.traffic.Fragment.MyCarFragment;
import com.pyp.traffic.Fragment.SunShineDetectionFragment;
import com.pyp.traffic.Fragment.TrafficLightManagerFragment;
import com.pyp.traffic.R;
import com.pyp.traffic.Service.AllSenseAndRoadStatusService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout main_Framelayout;
    private Button main_Idea;
    private Button main_Environment;
    private Button main_BusMessage;
    private Button main_MyCar;
    private Button main_TrafficLight;
    private Button main_LightChecking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        main_Framelayout = (FrameLayout) findViewById(R.id.main_Framelayout);
        main_Idea = (Button) findViewById(R.id.main_Idea);
        main_Environment = (Button) findViewById(R.id.main_Environment);
        main_BusMessage = (Button) findViewById(R.id.main_BusMessage);
        main_MyCar = (Button) findViewById(R.id.main_MyCar);
        main_TrafficLight = (Button) findViewById(R.id.main_TrafficLight);
        main_LightChecking = (Button) findViewById(R.id.main_LightChecking);

        main_Environment.setOnClickListener(this);
        main_BusMessage.setOnClickListener(this);
        main_MyCar.setOnClickListener(this);
        main_TrafficLight.setOnClickListener(this);
        main_LightChecking.setOnClickListener(this);
        main_Idea.setOnClickListener(this);

        Intent intent = getIntent();
        String str = "";
        str = intent.getStringExtra("Fragment");
        if (str.equals("fg_hp_Environment")) {
            main_Environment.performClick();
        } else if (str.equals("fg_hp_BusMessage")) {
            main_BusMessage.performClick();
        } else if (str.equals("fg_hp_MyCar")) {
            main_MyCar.performClick();
        } else if (str.equals("fg_hp_TrafficLight")) {
            main_TrafficLight.performClick();
        } else if (str.equals("fg_hp_LightChecking")) {
            main_LightChecking.performClick();
        } else if (str.equals("fg_hp_Idea")) {
            main_Idea.performClick();
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.main_Environment:
                transaction.replace(R.id.main_Framelayout,new EnvironmentIndexFragment());

                break;
            case R.id.main_BusMessage:
                transaction.replace(R.id.main_Framelayout,new BusMessageFragment());
                break;
            case R.id.main_MyCar:
                transaction.replace(R.id.main_Framelayout,new MyCarFragment());
                break;
            case R.id.main_TrafficLight:
                transaction.replace(R.id.main_Framelayout,new TrafficLightManagerFragment());

                break;
            case R.id.main_LightChecking:
                transaction.replace(R.id.main_Framelayout,new SunShineDetectionFragment());

                break;
            case R.id.main_Idea:
                transaction.replace(R.id.main_Framelayout,new CarParkingFragment());
                break;
        }
        transaction.commit();
    }
}
