package com.along.image.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ALong on 2017-12-3.
 *
 * @author Along
 */

public class FragmentUtils extends AppCompatActivity {

    private Fragment mShowFragment;

    /**
     * 根据Tag切换Fragment 减少内存浪费
     */
    public void switchFragmentByTag(int resId,String fragmentTag){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //隐藏显示的Fragment
        if (mShowFragment != null) {
            transaction.hide(mShowFragment);
        }
        //如果内存中找到进行显示
        mShowFragment=fragmentManager.findFragmentByTag(fragmentTag);
        if (mShowFragment != null) {
            transaction.show(mShowFragment);
        }else {
            try {
                mShowFragment = (Fragment) Class.forName(fragmentTag).newInstance();
                transaction.add(resId,mShowFragment,fragmentTag);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        transaction.commit();
    }
    /**
     * 根据Class切换Fragment 减少内存浪费
     */
    public void switchFragmentByClass(int resId,Class<? extends Fragment> cls){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (mShowFragment != null) {
            transaction.hide(mShowFragment);
        }
        mShowFragment = fragmentManager.findFragmentByTag(cls.getName());
        if (mShowFragment != null) {
            transaction.show(mShowFragment);
        }else {
            try {
                mShowFragment=cls.newInstance();
                transaction.add(resId,mShowFragment,cls.getName());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        transaction.commit();
    }
}
