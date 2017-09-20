package com.chao.rxjavaokhttp;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends BaseActivity implements BaseView {
    private EditText userNmae;

    private EditText password;

    private Button login;


    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        final Map<String,String> map = new HashMap<>();
        map.put("username",userNmae.getText().toString().trim());
        map.put("password",password.getText().toString().trim());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxJavaUtils.subscribe(OkhttpService.getInstance()
                        .request(UserBean.class,map)).subscribe(new AbsObserver<MainActivity,UserBean>(MainActivity.this) {
                    @Override
                    protected void response(UserBean userBean) {
                        Toast.makeText(MainActivity.this,userBean.getMsg(),Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        userNmae = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        login = (Button) findViewById(R.id.btn_login);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }
}
