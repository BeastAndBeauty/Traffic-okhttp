package com.pyp.traffic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.pyp.traffic.Application.MyApplication;
import com.pyp.traffic.CallBack.OnResponseListening;
import com.pyp.traffic.R;
import com.pyp.traffic.Request.UserLoginRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText login_uerName;
    private EditText login_password;
    private Button login_res;
    private Button login_login;
    private CheckBox login_loginbyself;
    private CheckBox login_remember;
    private Button login_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        login_uerName = (EditText) findViewById(R.id.login_uerName);
        login_password = (EditText) findViewById(R.id.login_password);
        login_res = (Button) findViewById(R.id.login_res);
        login_login = (Button) findViewById(R.id.login_login);
        login_loginbyself = (CheckBox) findViewById(R.id.login_loginbyself);
        login_remember = (CheckBox) findViewById(R.id.login_remember);
        login_setting = (Button) findViewById(R.id.login_setting);

        login_res.setOnClickListener(this);
        login_login.setOnClickListener(this);
        login_setting.setOnClickListener(this);
        login_loginbyself.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    login_remember.setChecked(true);
                    Toast.makeText(LoginActivity.this, "自动登录必选选择记住密码", Toast.LENGTH_SHORT).show();
                }

            }
        });
        login_uerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String editable = login_uerName.getText().toString();
                String regEx = "[^a-zA-Z0-9]";  //只能输入字母或数字
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(editable);
                String str = m.replaceAll("").trim();    //删掉不是字母或数字的字符
                if(!editable.equals(str)){
                    login_uerName.setText(str);  //设置EditText的字符
                    login_uerName.setSelection(str.length()); //因为删除了字符，要重写设置新的光标所在位置
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(login_uerName.getText().toString().length()>12){
                    Toast.makeText(LoginActivity.this, "用户名长度不能大于12", Toast.LENGTH_SHORT).show();
                    login_uerName.setText(login_uerName.getText().toString().substring(0,12));
                }
            }
        });


        try{
            login_uerName.setText(MyApplication.getUserName());
        }
        catch (Exception e){
            login_uerName.setText("");
        }
        try{
            login_password.setText(MyApplication.getPassword());
        }
        catch (Exception e){
            login_password.setText("");
        }
        try{
            login_remember.setChecked(MyApplication.getRemember());
        }
        catch (Exception e){
            login_remember.setChecked(false);
        }
        try{
            login_loginbyself.setChecked(MyApplication.getLogin());
        }
        catch (Exception e){
            login_loginbyself.setChecked(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_res:
                startActivity(new Intent(LoginActivity.this,ResActivity.class));
                finish();
                break;
            case R.id.login_login:
                MyApplication.setPassword(login_uerName.getText().toString());
                isCheck();
                submit();
                break;
            case R.id.login_setting:

                break;
        }
    }

    private void submit() {
        // validate
        String uerName = login_uerName.getText().toString().trim();
        if (TextUtils.isEmpty(uerName)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (login_uerName.getText().toString().length()<6) {
            Toast.makeText(this, "用户名长度不能小于6", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = login_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }


        //登录
        new UserLoginRequest().setParams(new Object[]{login_uerName.getText().toString(),login_password.getText().toString()}).sendRequest(new OnResponseListening() {
            @Override
            public void onResponse(Object response) {
                if(!response.toString().equals("F")){
                    MyApplication.setUserRole(response.toString());
                    startActivity(new Intent(LoginActivity.this,HomepageActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void isCheck(){
        MyApplication.setRemember(login_remember.isChecked());
        if(login_remember.isChecked())
            MyApplication.setPassword(login_password.getText().toString());
        MyApplication.setLogin(login_loginbyself.isChecked());
    }
}
