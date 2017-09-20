package com.chao.rxjavaokhttp;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by yang2 on 2017/9/20.
 */

public class OkhttpService {

    private static Gson gson = new Gson();

    private OkhttpService() {
    }

    public static OkhttpService getInstance() {
        return Hodler.instance;
    }

    static class Hodler {

        public static OkhttpService instance = new OkhttpService();

    }

    public <T>Observable request(final Class<?> clazz, final Map<String,String> params) {

        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(final ObservableEmitter<T> observable) throws Exception {

                OkHttpUtils.post().url("http://116.196.82.151:8080/mmall/user/login.do?")
                        .params(params)
                        .build()
                        .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        observable.onError(e);
                    }

                    @Override
                    public void onResponse(String response) {
                        T bean = (T) gson.fromJson(response, clazz);
                        if (bean != null) {
                            observable.onNext(bean);
                            observable.onComplete();
                        }
                    }
                });

            }
        });
    }
}
