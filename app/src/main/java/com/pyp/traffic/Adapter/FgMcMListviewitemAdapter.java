package com.pyp.traffic.Adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pyp.traffic.Entity.CarAccountRecharge;
import com.pyp.traffic.R;

public class FgMcMListviewitemAdapter extends BaseAdapter {

    private List<CarAccountRecharge> objects;

    private Context context;

    public FgMcMListviewitemAdapter(List<CarAccountRecharge> objects, Context context) {
        this.objects = objects;
        this.context = context;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public CarAccountRecharge getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("hhh","positio:"+position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context,R.layout.fg_mc_m_listviewitem, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }
        CarAccountRecharge bean=objects.get(position);
        holder.fgMcMLvCarId.setText(bean.getCarId()+"");
        holder.fgMcMLvDate.setText(bean.getTime());
        holder.fgMcMLvNumber.setText(position+1+"");
        holder.fgMcMLvMoney.setText(bean.getMoney()+"");
        return convertView;
    }


    protected class ViewHolder {
        private TextView fgMcMLvNumber;
    private TextView fgMcMLvCarId;
    private TextView fgMcMLvMoney;
    private TextView fgMcMLvDate;

        public ViewHolder(View view) {
            fgMcMLvNumber = (TextView) view.findViewById(R.id.fg_mc_m_lv_number);
            fgMcMLvCarId = (TextView) view.findViewById(R.id.fg_mc_m_lv_carId);
            fgMcMLvMoney = (TextView) view.findViewById(R.id.fg_mc_m_lv_money);
            fgMcMLvDate = (TextView) view.findViewById(R.id.fg_mc_m_lv_date);
        }
    }
}
