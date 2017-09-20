package com.chao.rxjavaokhttp;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
