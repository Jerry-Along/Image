package com.along.image.base;

/**
 * Created by mip on 2018/2/28.
 */

public abstract class BasePresenter<V extends BaseView,M extends BaseMode> {

    public V mView;
    public M mModel;

    public BasePresenter() {
    }

    public void setViewModel(V mView, M mModel) {
        this.mView = mView;
        this.mModel = mModel;
    }
}
