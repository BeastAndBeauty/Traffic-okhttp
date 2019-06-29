package com.pyp.traffic.ChildFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.pyp.traffic.Adapter.FgMcMListviewitemAdapter;
import com.pyp.traffic.Entity.CarAccountRecharge;
import com.pyp.traffic.R;
import com.pyp.traffic.Request.GetCarRechargeLog;

import java.util.List;
import java.util.Map;

public class fg_mc_messageFragment extends Fragment {
    private  List<CarAccountRecharge> list;
    private ListView fg_mc_m_listview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_mc_message, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        fg_mc_m_listview = (ListView) view.findViewById(R.id.fg_mc_m_listview);

        GetCarRechargeLog();
        fg_mc_m_listview.setAdapter(new FgMcMListviewitemAdapter(list,getContext()));
    }

    public void GetCarRechargeLog(){
        list= GetCarRechargeLog.getData();
        Log.i("hhh","list.size()="+list.size());
    }
}
