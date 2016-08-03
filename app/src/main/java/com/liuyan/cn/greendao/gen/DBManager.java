package com.liuyan.cn.greendao.gen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by apple on 16/8/2.下午6:43
 */
public class DBManager {
    private Context context;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private static DBManager instance;
    private static final String DB_Name="test_db";

    private DBManager(Context context){
        this.context=context;
        devOpenHelper=new DaoMaster.DevOpenHelper(context,DB_Name,null);
    }

    public static DBManager getInstance(Context context){
        if(instance==null){
            synchronized (DBManager.class){
                if(instance==null){
                    instance=new DBManager(context);
                }
            }
        }
        return instance;
    }
    public SQLiteDatabase getReadableDatabase(){
        if(devOpenHelper==null){
            devOpenHelper=new DaoMaster.DevOpenHelper(context,DB_Name,null);
        }
        SQLiteDatabase db=devOpenHelper.getReadableDatabase();
        return db;
    }


}
