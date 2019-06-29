package com.pyp.traffic.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者：paopao on 2019/3/18 15:49
 * <p>
 * 作用:
 */
public class EnvironmentIndexChartViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public EnvironmentIndexChartViewPagerAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }


    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
