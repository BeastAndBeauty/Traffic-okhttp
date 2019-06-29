package com.pyp.traffic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.pyp.traffic.Application.MyApplication;
import com.pyp.traffic.CallBack.OnResponseListening;
import com.pyp.traffic.R;
import com.pyp.traffic.Request.UserRegisterRequest;

public class ResActivity extends AppCompatActivity implements View.OnClickListener {

    private Button res_black;
    private EditText res_id;
    private EditText res_userName;
    private EditText res_password;
    private EditText res_name;
    private EditText res_phone;
    private RadioButton res_boy;
    private RadioButton res_girl;
    private Button res_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        initView();
    }

    private void initView() {
        res_black = (Button) findViewById(R.id.res_black);
        res_id = (EditText) findViewById(R.id.res_id);
        res_userName = (EditText) findViewById(R.id.res_userName);
        res_password = (EditText) findViewById(R.id.res_password);
        res_name = (EditText) findViewById(R.id.res_name);
        res_phone = (EditText) findViewById(R.id.res_phone);
        res_boy = (RadioButton) findViewById(R.id.res_boy);
        res_girl = (RadioButton) findViewById(R.id.res_girl);
        res_res = (Button) findViewById(R.id.res_res);

        res_black.setOnClickListener(this);
        res_res.setOnClickListener(this);
        res_girl.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.res_black:
                startActivity(new Intent(ResActivity.this,LoginActivity.class));
                finish();
                break;
            case R.id.res_res:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String id = res_id.getText().toString().trim();
        if (TextUtils.isEmpty(id)) {
            Toast.makeText(this, "请输入身份证号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (res_id.getText().toString().length()!=18) {
            Toast.makeText(this, "身份证号不足18位", Toast.LENGTH_SHORT).show();
            return;
        }

        String userName = res_userName.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = res_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = res_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        String phone = res_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        String str=res_phone.getText().toString().substring(0,2);
        if (!(str.equals("13") || str.equals("15") || str.equals("18"))) {
            Toast.makeText(this, "手机号码格式错误", Toast.LENGTH_SHORT).show();
            return;
        }
        if (res_phone.getText().toString().length()!=11) {
            Toast.makeText(this, "手机号码长度不足11位", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        int num=0;
        if(res_boy.isChecked())
            num=1;
        else
            num=0;
        new UserRegisterRequest().setParams(new Object[]{res_id.getText().toString(),
                res_userName.getText().toString(),res_password.getText().toString(),
                res_name.getText().toString(),num,res_phone.getText().toString()}).sendRequest(new OnResponseListening() {
            @Override
            public void onResponse(Object response) {
                if(!response.toString().equals("F")){
                    Toast.makeText(ResActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    MyApplication.setUserName(res_userName.getText().toString());
                    MyApplication.setPassword(res_password.getText().toString());
                    startActivity(new Intent(ResActivity.this,LoginActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(ResActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
