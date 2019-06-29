package com.pyp.traffic.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pyp.traffic.Activity.HomepageActivity;
import com.pyp.traffic.Application.MyApplication;
import com.pyp.traffic.CallBack.OnResponseListening;
import com.pyp.traffic.R;
import com.pyp.traffic.Request.GetParkFreeRequest;

import java.util.List;

public class CarParkingFragment extends Fragment implements View.OnClickListener {

    private ImageView imgCar1;
    private ImageView imgCar2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_car_parking, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.fg_bm_black).setOnClickListener(this);
        view.findViewById(R.id.bt_query).setOnClickListener(this);
        view.findViewById(R.id.bt_shuom).setOnClickListener(this);
        imgCar1 = view.findViewById(R.id.img_car1);
        imgCar2 = view.findViewById(R.id.img_car2);
        update();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fg_bm_black:
                startActivity(new Intent(getActivity(), HomepageActivity.class));
                getActivity().finish();
                break;
            case R.id.bt_query:
                update();
                break;
            case R.id.bt_shuom:
                new AlertDialog.Builder(getContext()).
                        setMessage("实现当前空闲停车位查询，当 1 号车位和 2 号车位有车和无车时，分别显示对应的图片")
                        .create().show();
                break;
        }
    }

    private void update() {
        new GetParkFreeRequest().setParams(new Object[]{MyApplication.getUserName()})
                .sendRequest(new OnResponseListening() {
                    @Override
                    public void onResponse(Object response) {
                        List<Integer> list = (List<Integer>) response;
                        if (list.contains(1))
                            imgCar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_nocar));
                        else
                            imgCar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_havecar));
                        if (list.contains(2))
                            imgCar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_nocar));
                        else
                            imgCar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_havecar));

                    }
                });
    }
}
