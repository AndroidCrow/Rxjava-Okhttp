package com.chao.rxjavaokhttp;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yang2 on 2017/9/20.
 */

public class RxJavaUtils {

    public static Observable subscribe(Observable observable){

        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
