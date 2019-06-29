package com.pyp.traffic.Request;

import android.os.Handler;
import android.os.Looper;

import com.pyp.traffic.CallBack.OnResponseListening;
import com.pyp.traffic.Util.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class BaseRequest {
    public abstract String getType();

    public abstract BaseRequest setParams(Object[] params);

    public abstract String getRequestBody();

    public abstract Object onResponseParse(String parse);

    private Handler handler = new Handler(Looper.getMainLooper());

    public void sendRequest(final OnResponseListening onResponseListening) {
        String url = "http://192.168.0.12:8080/transportservice3/action/" + getType() + ".do";
        OkHttpUtil.sendRequest(url, getRequestBody(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String parse = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onResponseListening.onResponse(onResponseParse(parse));
                    }
                });
            }
        });
    }
}
