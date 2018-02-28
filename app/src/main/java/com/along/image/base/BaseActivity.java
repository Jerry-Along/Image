package com.along.image.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.along.image.utils.TypeUtils;

/**
 * Created by mip on 2018/2/28.
 */

public abstract class BaseActivity<P extends BasePresenter,M extends BaseMode> extends AppCompatActivity{

    public P mPresenter;
    public M mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在设置布局钱操作
        doBeforeLayout();
        //设置布局
        setContentView(getLayoutId());
        //View注入

        //初始化Presenter
        mPresenter= TypeUtils.getObjectType(this,0);
        mModel=TypeUtils.getObjectType(this,1);

        //初始化VPresenter
        if (mPresenter != null) {
            initPresenter();
        }
        //初始化View
        initView();

    }

    protected abstract void initView();

    protected abstract void initPresenter();

    protected abstract int getLayoutId();

    protected abstract void doBeforeLayout();
}
