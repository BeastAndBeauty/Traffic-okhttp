package com.pyp.traffic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.pyp.traffic.Activity.HomepageActivity;
import com.pyp.traffic.Adapter.BusMessageExpandableListviewAdapter;
import com.pyp.traffic.Bean.BusStationInfoBean;
import com.pyp.traffic.CallBack.OnResponseListening;
import com.pyp.traffic.R;
import com.pyp.traffic.Request.GetBusStationInfoRequest;

import java.util.List;

public class BusMessageFragment extends Fragment implements View.OnClickListener {
    private List<BusStationInfoBean> busStationInfoBeans[]=new  List[2];
    private Button fg_bm_black;
    private ExpandableListView fg_bm_eListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_busmrssage, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        fg_bm_black = (Button) view.findViewById(R.id.fg_bm_black);
        fg_bm_eListView = (ExpandableListView) view.findViewById(R.id.fg_bm_eListView);

        fg_bm_black.setOnClickListener(this);
        GetBusStationInfoRequest(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fg_bm_black:
                startActivity(new Intent(getActivity(), HomepageActivity.class));
                getActivity().finish();
                break;
        }
    }

    public void GetBusStationInfoRequest(final int BusStationId){
        new GetBusStationInfoRequest().setParams(new Object[]{BusStationId}).sendRequest(new OnResponseListening() {
            @Override
            public void onResponse(Object response) {
                busStationInfoBeans[BusStationId-1] = (List<BusStationInfoBean>) response;
                if(BusStationId!=2)
                    GetBusStationInfoRequest(BusStationId+1);
                else
                    setAdapter();
            }
        });
    }

    public void setAdapter(){
        Log.i("hhh","busStationInfoBeans[0].size():"+busStationInfoBeans[0].size());
        fg_bm_eListView.setAdapter(new BusMessageExpandableListviewAdapter(getContext(),new String[]{"一号站台","二号站台"},busStationInfoBeans));
    }
}
