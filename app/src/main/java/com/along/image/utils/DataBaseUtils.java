package com.along.image.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.along.image.contract.Contracts;
import com.along.image.database.DataBaseHelper;
import com.along.image.model.TableModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mip on 2017/12/5.
 * @author Along
 */

public class DataBaseUtils {
    private static SQLiteDatabase mSqLiteDatabase;

    /**
     * 获取数据库的对象
     */
    public static SQLiteDatabase getDataBase(Context context){
        if (mSqLiteDatabase != null) {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
            mSqLiteDatabase=dataBaseHelper.getWritableDatabase();
        }
        return mSqLiteDatabase;
    }

    /**
     * 向表中插入一条数据
     */
    public static boolean insertContent(Context context,String values1,String values2,String values3,String values4,String values5){
        SQLiteDatabase dataBase = getDataBase(context);
        ContentValues values = new ContentValues();
        values.put(Contracts.TABLE_NAME.COLUM1,values1);
        values.put(Contracts.TABLE_NAME.COLUM2,values2);
        values.put(Contracts.TABLE_NAME.COLUM3,values3);
        values.put(Contracts.TABLE_NAME.COLUM4,values4);
        values.put(Contracts.TABLE_NAME.COLUM5,values5);
        long insertNum = dataBase.insert(Contracts.TABLE_NAME.TABLE_NAME, null, values);
        return insertNum!=-1;
    }

    /**
     * 查询表中的数据
     */
    public static List<TableModel> queryTableName(Context context){
        SQLiteDatabase dataBase = getDataBase(context);
        Cursor cursor = dataBase.query(Contracts.TABLE_NAME.TABLE_NAME, null, null, null, null, null, null, null);

        List<TableModel> models=new ArrayList<>();
        while (cursor.moveToNext()) {
            //将查到的每一行数据放到数据集合
            TableModel model=new TableModel();
            model.setName(cursor.getString(cursor.getColumnIndex(Contracts.TABLE_NAME.COLUM1)));
            model.setAge(cursor.getInt(cursor.getColumnIndex(Contracts.TABLE_NAME.COLUM2)));
            model.setSex(cursor.getString(cursor.getColumnIndex(Contracts.TABLE_NAME.COLUM3)));
            models.add(model);
        }
        return models;
    }

    public static boolean deleteTableByName(Context context,String delName){
        SQLiteDatabase dataBase = getDataBase(context);
        int delete = dataBase.delete(Contracts.TABLE_NAME.TABLE_NAME, Contracts.TABLE_NAME.ID + "=?", new String[]{delName});
        return delete!=0;
    }
}
