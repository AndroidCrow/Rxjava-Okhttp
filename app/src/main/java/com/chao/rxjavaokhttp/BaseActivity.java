package com.chao.rxjavaokhttp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yang2 on 2017/9/20.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
        initEvent();
    }

    protected abstract void initData();

    protected abstract void initEvent();

    protected abstract int getLayoutId();

    protected abstract void initView();


}
