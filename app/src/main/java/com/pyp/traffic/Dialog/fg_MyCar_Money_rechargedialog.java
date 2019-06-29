package com.pyp.traffic.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pyp.traffic.Application.MyApplication;
import com.pyp.traffic.CallBack.OnResponseListening;
import com.pyp.traffic.R;
import com.pyp.traffic.Request.SetCarAccountRechargeRequest;

public class fg_MyCar_Money_rechargedialog extends Dialog implements View.OnClickListener {
    private EditText fg_mc_money_rd_money;
    private Button fg_mc_money_rd_sure;
    private Button fg_mc_money_rd_cancel;
    private int CarId;

    public fg_MyCar_Money_rechargedialog(Context context, int carId) {
        super(context);
        CarId = carId;
    }

    public fg_MyCar_Money_rechargedialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fg_mc_money_rechargedilog);
        initview();
    }


    public void initview(){
        fg_mc_money_rd_money=findViewById(R.id.fg_mc_money_rd_money);
        fg_mc_money_rd_sure=findViewById(R.id.fg_mc_money_rd_sure);
        fg_mc_money_rd_cancel=findViewById(R.id.fg_mc_money_rd_cancel);

        fg_mc_money_rd_cancel.setOnClickListener(this);
        fg_mc_money_rd_sure.setOnClickListener(this);
        fg_mc_money_rd_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!fg_mc_money_rd_money.getText().toString().equals("")){
                    if(Integer.valueOf(fg_mc_money_rd_money.getText().toString())<1)
                        fg_mc_money_rd_money.setText("1");
                    else if(Integer.valueOf(fg_mc_money_rd_money.getText().toString())>50)
                        fg_mc_money_rd_money.setText("50");
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fg_mc_money_rd_sure:
                if(fg_mc_money_rd_money.getText().toString().equals("")){
                    Toast.makeText(getContext(),"请输入充值的数额",Toast.LENGTH_SHORT).show();
                    return;
                }
                SetCarAccountRechargeRequest();
                break;
            case R.id.fg_mc_money_rd_cancel:
                dismiss();
                break;
        }
    }

    public void SetCarAccountRechargeRequest(){
        new SetCarAccountRechargeRequest().setParams(new Object[]{MyApplication.getUserName(),CarId,fg_mc_money_rd_money.getText().toString()}).sendRequest(new OnResponseListening() {
            @Override
            public void onResponse(Object response) {
                if(!response.toString().equals("F")){
                    Toast.makeText(getContext(),CarId+"号小车充值"+fg_mc_money_rd_money.getText().toString()+"元成功",Toast.LENGTH_SHORT).show();
                    dismiss();
                }
                else {
                    Toast.makeText(getContext(),CarId+"号小车充值"+fg_mc_money_rd_money.getText().toString()+"元失败",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
