package com.pyp.traffic.ChildFragment;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.pyp.traffic.Entity.AllSenseAndRoadStatus;
import com.pyp.traffic.Entity.AllSenseAndRoadStatusMinute;
import com.pyp.traffic.R;
import com.pyp.traffic.Request.GetSenseAndRoadData;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoadStatusChartFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {


    @BindView(R.id.chart_name)
    TextView chartName;
    @BindView(R.id.second_minute_change)
    Switch secondMinuteChange;
    @BindView(R.id.line_chart)
    LineChart mChart;
    Unbinder unbinder;

    private String[] second_x = {"03", "06", "09", "12", "15", "18", "21", "24", "27", "30", "33", "36", "39", "42", "45", "48", "51", "54", "57", "60",
            "03", "06", "09", "12", "15", "18", "21", "24", "27", "30", "33", "36", "39", "42", "45", "48", "51", "54", "57", "60",
            "03", "06", "09", "12", "15", "18", "21", "24", "27", "30",};

    private Timer timer;
    private TimerTask timerTask;
    private int second_or_minute = 0;
    private XAxis xAxis;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setData();
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temperature_chart, container, false);
        unbinder = ButterKnife.bind(this, view);

        chartName.setText("道路状态");
        secondMinuteChange.setOnCheckedChangeListener(this);
        initChart();
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        };
        timer.schedule(timerTask, 0, 3000);

        return view;
    }

    private void initChart() {

        // no description text
        mChart.getDescription().setEnabled(false);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        mChart.setDragDecelerationFrictionCoef(0.9f);
        mChart.setExtraOffsets(10f, 30f, 10f, 50f);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        mChart.setBackgroundColor(Color.LTGRAY);

        xAxis = mChart.getXAxis();
        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // add data


        // get the legend (only possible after setting data)
        mChart.getLegend().setEnabled(false);



        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);

        mChart.getAxisRight().setEnabled(false);

    }


    private void setData() {
        mChart.clear();

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        int j = 0;
        if (second_or_minute == 0) {
            for (AllSenseAndRoadStatus i : GetSenseAndRoadData.getSecondData()) {
                Log.i("5秒一次", i.getStatus() + "    " + i.getId());
                yVals1.add(new Entry(j, i.getStatus()));
                j++;
            }
            xAxis.setValueFormatter(new IndexAxisValueFormatter(second_x));
            xAxis.setLabelCount(second_x.length);


        } else {
            for (AllSenseAndRoadStatusMinute i : GetSenseAndRoadData.getMinuteData()) {
                Log.i("一分钟一次", i.getStatus() + "");
                yVals1.add(new Entry(j, i.getStatus()));
                j++;
            }

        }


        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);

            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals1, "温度");

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setDrawCircleHole(false);
            //set1.setFillFormatter(new MyFillFormatter(0f));
            //set1.setDrawHorizontalHighlightIndicator(false);
            //set1.setVisible(false);
            //set1.setCircleHoleColor(Color.WHITE);


            // create a data object with the datasets
            LineData data = new LineData(set1);
            data.setValueTextColor(Color.BLACK);
            data.setValueTextSize(9f);

            // set data
            mChart.setData(data);
            mChart.invalidate();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            second_or_minute = 1;
        } else {
            second_or_minute = 0;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();
        timerTask.cancel();
        timerTask=null;
    }

}
