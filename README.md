# Rxjava-Okhttp
## 首先写这个整合框架的原因是我公司居然是用第三方的后台，导致我写网络访问的时候非常痛苦，最近刚好没什么事情，就乘着这个机会把这些东西整合到一起
### 1、首先先定义一个okhttp请求类这里我叫OkhttpService，里面的内容很少 request方法主要是统一所有的请求，本来是想一个请求一个方法，写了一半发现这个类里面的代码会爆炸，花了点时间来封装好,这样我们只需要一个方法就能请求大部分接口！

```
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
```
### 2、接下来mainActivity里面就传递请求参数就行了，这样代码就很直观,一幕了然

```
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
```
### 3、这边我还重写了Observer统一了显示隐藏进度条，封装了Bean对象，使用起来更加的方便

```
/**
 * Created by yang2 on 2017/9/20.
 */

public abstract class AbsObserver<V extends BaseView,T> implements Observer {

    public AbsObserver(BaseView baseView){
        mView = (V) baseView;
    }

    private V mView;

    private T t;
    @Override
    public void onSubscribe(Disposable d) {
        mView.showDialog();
    }

    @Override
    public void onComplete() {
        mView.hideDialog();
    }

    @Override
    public void onNext(Object value) {
        t = (T) value;
        response(t);
    }
    protected abstract void response(T t);
}

```
主要的代码就这些很简单.
