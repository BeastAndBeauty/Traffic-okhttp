package com.pyp.traffic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.pyp.traffic.Fragment.EnvironmentIndexFragment;
import com.pyp.traffic.Fragment.SunShineDetectionFragment;
import com.pyp.traffic.Fragment.TrafficLightManagerFragment;
import com.pyp.traffic.R;
import com.pyp.traffic.Service.AllSenseAndRoadStatusService;
import com.pyp.traffic.testRequestActivity;


import butterknife.BindView;
import butterknife.ButterKnife;

public class PPActivity extends AppCompatActivity {

    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pp);
        ButterKnife.bind(this);
        startService(new Intent(PPActivity.this, AllSenseAndRoadStatusService.class));

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new EnvironmentIndexFragment()).commit();

    }
}
