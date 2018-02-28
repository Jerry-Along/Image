package com.along.image.base;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mip on 2018/2/28.
 */

public class AndroidToMain {

    public static <T> Observable<T> ioToMain(Observable<T> observable){

        Observable<T> compose = observable.compose(new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        });
        return compose;
    }


    public static class ioToMainTransFormer<T> implements ObservableTransformer<T,T>{

        @Override
        public ObservableSource apply(Observable upstream) {
            return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    }


}
