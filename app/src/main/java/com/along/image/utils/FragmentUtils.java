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
                Fragment fragment = (Fragment) Class.forName(fragmentTag).newInstance();
                transaction.add(resId,fragment,fragmentTag);
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

    public void switchFragmentByClass(int resId,Class<? extends Fragment> cls){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (mShowFragment != null) {
            transaction.hide(mShowFragment);
        }
        Fragment fragment = fragmentManager.findFragmentByTag(cls.getName());
        transaction.add(resId,fragment,cls.getName());
        transaction.commit();
    }
}
