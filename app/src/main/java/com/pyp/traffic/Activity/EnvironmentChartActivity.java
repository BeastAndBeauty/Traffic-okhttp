package com.pyp.traffic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pyp.traffic.Adapter.EnvironmentIndexChartViewPagerAdapter;
import com.pyp.traffic.ChildFragment.COChartFragment;
import com.pyp.traffic.ChildFragment.HumidityChartFragment;
import com.pyp.traffic.ChildFragment.PMChartFragment;
import com.pyp.traffic.ChildFragment.RoadStatusChartFragment;
import com.pyp.traffic.ChildFragment.SunShineChartFragment;
import com.pyp.traffic.ChildFragment.TemperatureChartFragment;
import com.pyp.traffic.Fragment.SunShineDetectionFragment;
import com.pyp.traffic.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnvironmentChartActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener {

    @BindView(R.id.open_menu)
    ImageView openMenu;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.ll_point_ground)
    LinearLayout llPointGround;

    private List<ImageView> points;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment_chart);
        ButterKnife.bind(this);

        initData();
        initPoints();
        initGetIntentData();

    }

    private void initGetIntentData() {
        Intent intent=getIntent();
        updatePoints(intent.getIntExtra("position",0));
        viewPager.setCurrentItem(intent.getIntExtra("position",0));
    }

    private void initData() {
        fragmentList=new ArrayList<>();
        fragmentList.add(new TemperatureChartFragment());
        fragmentList.add(new HumidityChartFragment());
        fragmentList.add(new SunShineChartFragment());
        fragmentList.add(new COChartFragment());
        fragmentList.add(new PMChartFragment());
        fragmentList.add(new RoadStatusChartFragment());
        viewPager.setAdapter(new EnvironmentIndexChartViewPagerAdapter(getSupportFragmentManager(),fragmentList));
        viewPager.setOnPageChangeListener(this);
    }

    private void initPoints() {
        points=new ArrayList<>();
        for (int i=0;i<fragmentList.size();i++){
            ImageView imageView=new ImageView(this);
            imageView.setBackgroundResource(R.drawable.circle);
            points.add(imageView);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(30,30);
            imageView.setLayoutParams(params);
            imageView.setOnClickListener(this);
            imageView.setTag(i);
            if (i!=0)
                params.leftMargin=20;
            llPointGround.addView(imageView);
        }
    }

    @OnClick(R.id.open_menu)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onClick(View v) {
        updatePoints((int)v.getTag());
        viewPager.setCurrentItem((int)v.getTag());

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        updatePoints(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private void updatePoints(int position){
        for (int i=0;i<fragmentList.size();i++){
            if (position==i)
                points.get(i).setBackgroundResource(R.drawable.gray_circle);
            else
                points.get(i).setBackgroundResource(R.drawable.circle);
        }
    }

}
