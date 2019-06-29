package com.pyp.traffic.Adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.pyp.traffic.Bean.BusStationInfoBean;
import com.pyp.traffic.R;

import java.util.List;

public class BusMessageExpandableListviewAdapter implements ExpandableListAdapter {
    private Context context;
    private String item1[];
    private List<BusStationInfoBean> item2[];

    public BusMessageExpandableListviewAdapter(Context context, String[] item1, List<BusStationInfoBean>[] item2) {
        this.context = context;
        this.item1 = item1;
        this.item2 = item2;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }


    @Override
    public int getGroupCount() {
        return item1.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return item2[groupPosition].size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return item1[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return item2[groupPosition].get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.fg_bm_listviewitem1, null);
            holder = new GroupViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        holder.fg_bm_lv1_TV.setText(item1[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.fg_bm_listviewitem2, null);
            holder=new ChildViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder= (ChildViewHolder) convertView.getTag();
        }
        BusStationInfoBean bean=item2[groupPosition].get(childPosition);
        holder.fg_bm_lv2_BusNumber.setText(bean.getBusId()+"号");
        Log.i("hhh","groupPosition:"+groupPosition+"    BusId:"+bean.getBusId());
        holder.fg_bm_lv2_distance.setText("距离站台"+bean.getDistance()+"m");
        holder.fg_bm_lv2_Time.setText(bean.getTime()+"分钟后到达");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return childId;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return groupId;
    }


    public static
    class GroupViewHolder {
        public View rootView;
        public TextView fg_bm_lv1_TV;

        public GroupViewHolder(View rootView) {
            this.rootView = rootView;
            this.fg_bm_lv1_TV = (TextView) rootView.findViewById(R.id.fg_bm_lv1_TV);
        }

    }

    public static
    class ChildViewHolder {
        public View rootView;
        public TextView fg_bm_lv2_BusNumber;
        public TextView fg_bm_lv2_Time;
        public TextView fg_bm_lv2_distance;

        public ChildViewHolder(View rootView) {
            this.rootView = rootView;
            this.fg_bm_lv2_BusNumber = (TextView) rootView.findViewById(R.id.fg_bm_lv2_BusNumber);
            this.fg_bm_lv2_Time = (TextView) rootView.findViewById(R.id.fg_bm_lv2_Time);
            this.fg_bm_lv2_distance = (TextView) rootView.findViewById(R.id.fg_bm_lv2_distance);
        }

    }
}
