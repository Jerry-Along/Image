package com.along.image.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.along.image.contract.Contracts;

/**
 * Created by mip on 2017/12/5.
 * @author Along
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_TABLE= "create table "+ Contracts.TABLE_NAME.TABLE_NAME+" ("
            +Contracts.TABLE_NAME.ID+" integer primary key autoincrement, "
            + Contracts.TABLE_NAME.COLUM1 +" varchar(20), "
            + Contracts.TABLE_NAME.COLUM2+"varchar(32) );";
    public static final String CREATE_TABLE2="";
    public static final String CREATE_TABLE3="";
    public static final String CREATE_TABLE4="";

    public DataBaseHelper(Context context) {
        super(context, Contracts.DATABASE_NAME, null, Contracts.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //在数据库中创建表
//        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //数据库升级，改变DATABASE_VERSION会调用
//        db.beginTransaction();
//        db.execSQL(tempTable);
//        String createTemp="insert into tempTable select * from "+Contracts.TABLE_NAME.TABLE_NAME;
//        db.execSQL(createTemp);
//        String dropOldTable="drop table"+Contracts.TABLE_NAME.TABLE_NAME;
//        db.execSQL(dropOldTable);
//        String createNewTable="";
//        db.execSQL(createNewTable);
//        String insertValues="insert into CREATE_TABLE('name','age') select * from tempTable";
//        db.execSQL(insertValues);
//        db.setTransactionSuccessful();
//        db.endTransaction();

    }
}
