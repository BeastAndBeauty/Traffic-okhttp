package com.pyp.traffic.ChildFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.pyp.traffic.Application.MyApplication;
import com.pyp.traffic.CallBack.OnResponseListening;
import com.pyp.traffic.R;
import com.pyp.traffic.Request.GetCarMoveRequest;
import com.pyp.traffic.Request.SetCarMoveRequest;

public class fg_mc_controlFragment extends Fragment implements View.OnClickListener {
    private Button fg_mc_c_start1;
    private Button fg_mc_c_stop1;
    private Button fg_mc_c_start2;
    private Button fg_mc_c_stop2;
    private Button fg_mc_c_start3;
    private Button fg_mc_c_stop3;
    private Button fg_mc_c_start4;
    private Button fg_mc_c_stop4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_mc_control, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        fg_mc_c_start1 = (Button) view.findViewById(R.id.fg_mc_c_start1);
        fg_mc_c_stop1 = (Button) view.findViewById(R.id.fg_mc_c_stop1);
        fg_mc_c_start2 = (Button) view.findViewById(R.id.fg_mc_c_start2);
        fg_mc_c_stop2 = (Button) view.findViewById(R.id.fg_mc_c_stop2);
        fg_mc_c_start3 = (Button) view.findViewById(R.id.fg_mc_c_start3);
        fg_mc_c_stop3 = (Button) view.findViewById(R.id.fg_mc_c_stop3);
        fg_mc_c_start4 = (Button) view.findViewById(R.id.fg_mc_c_start4);
        fg_mc_c_stop4 = (Button) view.findViewById(R.id.fg_mc_c_stop4);

        fg_mc_c_start1.setOnClickListener(this);
        fg_mc_c_stop1.setOnClickListener(this);
        fg_mc_c_start2.setOnClickListener(this);
        fg_mc_c_stop2.setOnClickListener(this);
        fg_mc_c_start3.setOnClickListener(this);
        fg_mc_c_stop3.setOnClickListener(this);
        fg_mc_c_start4.setOnClickListener(this);
        fg_mc_c_stop4.setOnClickListener(this);

        GetCarMoveRequest(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fg_mc_c_start1:
                SetCarMoveRequest(1, SetCarMoveRequest.CarAction.Start);
                break;
            case R.id.fg_mc_c_stop1:
                SetCarMoveRequest(1, SetCarMoveRequest.CarAction.Stop);
                break;
            case R.id.fg_mc_c_start2:
                SetCarMoveRequest(2, SetCarMoveRequest.CarAction.Start);
                break;
            case R.id.fg_mc_c_stop2:
                SetCarMoveRequest(2, SetCarMoveRequest.CarAction.Stop);
                break;
            case R.id.fg_mc_c_start3:
                SetCarMoveRequest(3, SetCarMoveRequest.CarAction.Start);
                break;
            case R.id.fg_mc_c_stop3:
                SetCarMoveRequest(3, SetCarMoveRequest.CarAction.Stop);
                break;
            case R.id.fg_mc_c_start4:
                SetCarMoveRequest(4, SetCarMoveRequest.CarAction.Start);
                break;
            case R.id.fg_mc_c_stop4:
                SetCarMoveRequest(4, SetCarMoveRequest.CarAction.Stop);
                break;
        }
    }

    public void GetCarMoveRequest(final int CarID){
        new GetCarMoveRequest().setParams(new Object[]{MyApplication.getUserName(),CarID}).sendRequest(new OnResponseListening() {
            @Override
            public void onResponse(Object response) {
                if(!response.toString().equals("F")){
                    setCar(CarID,response.toString());
                }
                else {
                    Log.i("hhh","GetCarMoveRequestAgain");
                    GetCarMoveRequest(CarID);
                }
                if(CarID<4)
                    GetCarMoveRequest(CarID+1);
            }
        });
    }

    public void SetCarMoveRequest(final int CarID, final SetCarMoveRequest.CarAction carAction){
        new SetCarMoveRequest().setParams(new Object[]{MyApplication.getUserName(),CarID,carAction}).sendRequest(new OnResponseListening() {
            @Override
            public void onResponse(Object response) {
                if(!response.toString().equals("F")){
                    if(carAction==SetCarMoveRequest.CarAction.Start){
                        Toast.makeText(getContext(),CarID+"号小车启动成功",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(),CarID+"号小车停止成功",Toast.LENGTH_SHORT).show();
                    }
                    seyCar(CarID,carAction);
                }
                else {
                    if(carAction==SetCarMoveRequest.CarAction.Start){
                        Toast.makeText(getContext(),CarID+"号小车启动失败",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(),CarID+"号小车停止失败",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    public void Carstart(Button button){
        button.setBackgroundColor(Color.GREEN);
    }

    public void CarStop(Button button){
        button.setBackgroundColor(Color.WHITE);
    }

    public void setCar(int CarID,String carAction){
        if(carAction.equals("Start"))
            seyCar(CarID, SetCarMoveRequest.CarAction.Start);
        else
            seyCar(CarID, SetCarMoveRequest.CarAction.Stop);
    }


    public void seyCar(int CarID,SetCarMoveRequest.CarAction carAction){
        switch (CarID){
            case 1:
                if(carAction== SetCarMoveRequest.CarAction.Start) {
                    Carstart(fg_mc_c_start1);
                    CarStop(fg_mc_c_stop1);
                }
                else {
                    Carstart(fg_mc_c_stop1);
                    CarStop(fg_mc_c_start1);
                }
                break;
            case 2:
                if(carAction== SetCarMoveRequest.CarAction.Start) {
                    Carstart(fg_mc_c_start2);
                    CarStop(fg_mc_c_stop2);
                }
                else {
                    Carstart(fg_mc_c_stop2);
                    CarStop(fg_mc_c_start2);
                }
                break;
            case 3:
                if(carAction== SetCarMoveRequest.CarAction.Start) {
                    Carstart(fg_mc_c_start3);
                    CarStop(fg_mc_c_stop3);
                }
                else {
                    Carstart(fg_mc_c_stop3);
                    CarStop(fg_mc_c_start3);
                }
                break;
            case 4:
                if(carAction== SetCarMoveRequest.CarAction.Start) {
                    Carstart(fg_mc_c_start4);
                    CarStop(fg_mc_c_stop4);
                }
                else {
                    Carstart(fg_mc_c_stop4);
                    CarStop(fg_mc_c_start4);
                }
                break;
        }
    }
}
