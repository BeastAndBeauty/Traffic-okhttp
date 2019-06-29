package com.pyp.traffic.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.pyp.traffic.Adapter.TableListViewAdapter;
import com.pyp.traffic.Application.MyApplication;
import com.pyp.traffic.CallBack.OnResponseListening;
import com.pyp.traffic.R;
import com.pyp.traffic.Request.GetRoadStatusRequest;
import com.pyp.traffic.Request.GetTrafficLightConfigActionRequest;
import com.pyp.traffic.Request.SetTrafficLightNowStatusRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrafficLightManagerFragment extends Fragment {


    @BindView(R.id.open_menu)
    ImageView openMenu;
    @BindView(R.id.list_view)
    ListView listView;
    Unbinder unbinder;
    private List<Map<String, Object>> mapList;

    private TableListViewAdapter adapter;

    private int trafficLightId=1;
    private int RoadId=1;

    private Timer timer;
    private TimerTask timerTask;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_traffic_light_manager, container, false);
        unbinder = ButterKnife.bind(this, view);

        initListView();

        getTrafficLightTime(trafficLightId);

        initTimer();

        return view;
    }

    private void initTimer() {
        timer=new Timer();
        timerTask=new TimerTask() {
            @Override
            public void run() {
                RoadId=1;
                getRoadStatus(RoadId);
            }
        };
        timer.schedule(timerTask,3000,3000);
    }

    private void getTrafficLightTime(int index) {
        new GetTrafficLightConfigActionRequest().setParams(new Object[]{MyApplication.getUserName(),trafficLightId}).sendRequest(new OnResponseListening() {
            @Override
            public void onResponse(Object response) {
                if (!response.equals("F")){
                    mapList.get(trafficLightId-1).put("RoadLightTime",response.toString());
                    mapList.get(trafficLightId-1).put("RoadLightTimeBefore",response.toString());
                }
                trafficLightId++;
                Log.i("papa","trafficLightId="+trafficLightId);
                if (trafficLightId<4){
                    getTrafficLightTime(trafficLightId);
                }
                if (trafficLightId==4){
                    getRoadStatus(RoadId);
                }

            }
        });

    }

    private void getRoadStatus(int index){
        Log.i("papa","RoadId1="+RoadId);
        new GetRoadStatusRequest().setParams(new Object[]{RoadId}).sendRequest(new OnResponseListening() {
            @Override
            public void onResponse(Object response) {
                if (!response.equals("F")){
                    mapList.get(RoadId-1).put("RoadStatus",response.toString());
                    if (Integer.valueOf(response.toString())>3){
                        mapList.get(RoadId-1).put("RoadLightTimeBefore",mapList.get(RoadId-1).get("RoadLightTime"));
                        mapList.get(RoadId-1).put("RoadLightTime",30);
                        setTrafficLightTime(RoadId,30);
                    }else if (Integer.valueOf(mapList.get(RoadId-1).get("RoadLightTime").toString())==30){
                        mapList.get(RoadId-1).put("RoadLightTime",mapList.get(RoadId-1).get("RoadLightTimeBefore"));
                        mapList.get(RoadId-1).put("RoadLightTimeBefore",30);
                        setTrafficLightTime(RoadId,Integer.valueOf(mapList.get(RoadId-1).get("RoadLightTimeBefore").toString()));
                    }
                }
                RoadId++;
                Log.i("papa","RoadId2="+RoadId);
                if (RoadId<4)
                    getRoadStatus(RoadId);

                adapter.notifyDataSetChanged();
            }
        });
    }

    //设置路灯时间
    private void setTrafficLightTime(final int roadId, int lightTime){
        new SetTrafficLightNowStatusRequest().setParams(new Object[]{MyApplication.getUserName(),RoadId,lightTime}).sendRequest(new OnResponseListening() {
            @Override
            public void onResponse(Object response) {
                if (!response.equals("F"))
                    mapList.get(roadId-1).put("ModifyData",response.toString());
            }
        });
    }


    private void initListView() {
        mapList=new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();
        map1.put("RoadNumber", 1);
        map1.put("RoadStatus", 5);
        map1.put("TrafficLightNumber", 1);
        map1.put("RoadLightTimeBefore", 20);
        map1.put("RoadLightTime", 30);
        map1.put("ModifyData", "2017.3.18 13:23");
        map2.put("RoadNumber", 2);
        map2.put("RoadStatus", 5);
        map2.put("TrafficLightNumber", 2);
        map2.put("RoadLightTimeBefore", 20);
        map2.put("RoadLightTime", 30);
        map2.put("ModifyData", "2017.3.18 13:23");
        map3.put("RoadNumber", 3);
        map3.put("RoadStatus", 5);
        map3.put("TrafficLightNumber", 3);
        map3.put("RoadLightTimeBefore", 20);
        map3.put("RoadLightTime", 30);
        map3.put("ModifyData", "2017.3.18 13:23");
        mapList.add(map1);
        mapList.add(map2);
        mapList.add(map3);
         adapter=new TableListViewAdapter(mapList);
        listView.setAdapter(adapter);

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.open_menu)
    public void onViewClicked() {
    }

    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();
        timer=null;
        timerTask.cancel();
        timerTask=null;
    }
}
