package com.phone1000.martialstudyself.utils;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.phone1000.martialstudyself.activitys.FirstStartActivity;

/**
 * Created by my on 2016/11/29.
 */
public class DBHelperStart extends SQLiteOpenHelper {

    private Context context ;

    public DBHelperStart(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        context.startActivity(new Intent(context, FirstStartActivity.class));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        context.startActivity(new Intent(context,FirstStartActivity.class));
    }
}
