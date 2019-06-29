package com.pyp.traffic.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pyp.traffic.R;

import java.util.List;
import java.util.Map;

/**
 * 作者：paopao on 2019/3/18 10:35
 * <p>
 * 作用:
 */
public class TableListViewAdapter extends BaseAdapter {

    private List<Map<String,Object>> mapList;

    public TableListViewAdapter(List<Map<String, Object>> mapList) {
        this.mapList = mapList;
    }

    @Override
    public int getCount() {
        return mapList.size();
    }

    @Override
    public Object getItem(int position) {
        return mapList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.traffic_light_table_layout,null);
        }
        TextView road_number=convertView.findViewById(R.id.road_number);
        TextView road_status=convertView.findViewById(R.id.road_status);
        TextView traffic_light_number=convertView.findViewById(R.id.traffic_light_number);
        TextView road_light_time_before=convertView.findViewById(R.id.road_light_time_before);
        TextView road_light_time=convertView.findViewById(R.id.road_light_time);
        TextView modify_date=convertView.findViewById(R.id.modify_date);

        road_number.setText(mapList.get(position).get("RoadNumber").toString());
        road_status.setText(mapList.get(position).get("RoadStatus").toString());
        traffic_light_number.setText(mapList.get(position).get("TrafficLightNumber").toString());
        road_light_time_before.setText(mapList.get(position).get("RoadLightTimeBefore").toString());
        road_light_time.setText(mapList.get(position).get("RoadLightTime").toString());
        modify_date.setText(mapList.get(position).get("ModifyData").toString());

        if (Integer.valueOf(mapList.get(position).get("RoadStatus").toString())>3)
            road_status.setBackgroundResource(R.drawable.red_rectangle);
        else
            road_status.setBackgroundResource(R.drawable.rectangle);

        return convertView;
    }
}
