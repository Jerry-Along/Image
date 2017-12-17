package com.along.image.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ALong on 2017-12-16.
 *
 * @author  Alongss
 */

public abstract class AlmightAdapter<T> extends BaseAdapter{

    private List<T> data;
    private LayoutInflater layoutInflater;
    private int layoutResourceId;

    public AlmightAdapter(Context context,List<T> data, int layoutResourceId) {

        this.layoutInflater = LayoutInflater.from(context);
        if (data != null) {
            this.data = data;
        }else {
            this.data=new ArrayList<>();
        }
        this.layoutResourceId=layoutResourceId;
    }

    public void updateDataResult(List<T> data){
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addDataResult(List<T> data){
        if (data != null && data.size()>0) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }

    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public T getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView=layoutInflater.inflate(layoutResourceId,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder=((ViewHolder) convertView.getTag());
        }

        bindData(holder,getItem(position),position);
        return convertView;
    }

    /**
     *
     * @param holder 每个item用来查找控件，填充控件
     * @param item   赋值
     * @param position 预留字段
     */
    abstract void bindData(ViewHolder holder, T item, int position);


    public static class ViewHolder{

        public View itemView;
        /**
         * 使用一个map对已经实例化的item的itemView进行实例化
         */
        private Map<Integer,View> cacheViews;

        public ViewHolder(View itemView) {
            this.itemView=itemView;
            this.cacheViews=new HashMap<>();
        }
        /**
         * 根据id去convertView中去找
         */
        public View findView(int resId){
            View v;
            if (cacheViews.containsKey(resId)) {
                v=cacheViews.get(resId);
            }else {
                v=itemView.findViewById(resId);
                cacheViews.put(resId,v);
            }
            return v;
        }
        public void setText(int resId,String text){
            ((TextView) findView(resId)).setText(text);
        }
    }



}
