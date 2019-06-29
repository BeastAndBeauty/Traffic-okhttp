package com.pyp.traffic.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.pyp.traffic.Application.MyApplication;
import com.pyp.traffic.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnvironmentIndexSettingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.open_menu)
    ImageView openMenu;
    @BindView(R.id.switch_status)
    TextView switchStatus;
    @BindView(R.id.switch_control)
    Switch switchControl;
    @BindView(R.id.edt_temperature)
    EditText edtTemperature;
    @BindView(R.id.edt_humidity)
    EditText edtHumidity;
    @BindView(R.id.edt_sun_shine)
    EditText edtSunShine;
    @BindView(R.id.edt_co)
    EditText edtCo;
    @BindView(R.id.edt_pm)
    EditText edtPm;
    @BindView(R.id.edt_road_status)
    EditText edtRoadStatus;
    @BindView(R.id.btn_save)
    Button btnSave;

    private boolean isSave=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviroment_index_setting);
        ButterKnife.bind(this);

        switchControl.setOnCheckedChangeListener(this);
        setEdtEditable(false);

    }

    private void setEdtEditable(boolean edtEditable) {
        edtTemperature.setEnabled(edtEditable);
        edtHumidity.setEnabled(edtEditable);
        edtSunShine.setEnabled(edtEditable);
        edtCo.setEnabled(edtEditable);
        edtPm.setEnabled(edtEditable);
        edtRoadStatus.setEnabled(edtEditable);
    }

    @OnClick({R.id.open_menu, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.open_menu:
                finish();
                break;
            case R.id.btn_save:
                if (isSave){
                    putInSP();
                }
                break;
        }
    }

    private void putInSP() {
        if (!TextUtils.isEmpty(edtTemperature.getText().toString()))
            MyApplication.set("edtTemperature", edtTemperature.getText().toString());
        if (!TextUtils.isEmpty(edtHumidity.getText().toString()))
            MyApplication.set("edtHumidity", edtHumidity.getText().toString());
        if (!TextUtils.isEmpty(edtSunShine.getText().toString()))
            MyApplication.set("edtSunShine", edtSunShine.getText().toString());
        if (!TextUtils.isEmpty(edtCo.getText().toString()))
            MyApplication.set("edtCo", edtCo.getText().toString());
        if (!TextUtils.isEmpty(edtPm.getText().toString()))
            MyApplication.set("edtPm", edtPm.getText().toString());
        if (!TextUtils.isEmpty(edtRoadStatus.getText().toString()))
            MyApplication.set("edtRoadStatus", edtRoadStatus.getText().toString());

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        isSave=isChecked;
        MyApplication.set("IsOpen",isChecked);
        setEdtEditable(isChecked);
        if (isChecked)
            switchStatus.setText("关");
        else
            switchStatus.setText("开");
    }


}
