package com.pyp.traffic.Application;

import android.app.Application;
import android.content.SharedPreferences;

import com.pyp.traffic.Entity.DaoMaster;
import com.pyp.traffic.Entity.DaoSession;

import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;

public class MyApplication extends Application {

    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        sp=getSharedPreferences("traffic7",MODE_PRIVATE);
        editor=sp.edit();
        try {
            sp.getBoolean("first",false);
        }
        catch (Exception e){
            editor.putBoolean("first",false).commit();
            setRemember(true);
        }
        setupDataBase();
    }

    private void setupDataBase() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "traffic.db", null);
        Database db = openHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }



    public static void set(String key, Object value) {
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        }else if (value instanceof Integer){
            editor.putInt(key, (Integer) value);
        }
        editor.commit();
    }

    public static Object get(String key,Object obj){
        if (obj instanceof String)
            return sp.getString(key, (String) obj);
        else if(obj instanceof Boolean)
            return sp.getBoolean(key, (Boolean) obj);
        else
            return sp.getString(key, (String) obj);

    }

    //存取用户名
    public static void setUserName(String value){
        editor.putString("UserName",value).commit();
    }
    public static String getUserName() {
        return sp.getString("UserName", "user1");
    }


    //存取密码
    public static void setPassword(String value){
        editor.putString("Password",value).commit();
    }
    public static String getPassword(){
        return sp.getString("Password","");
    }
    //存取用户等级
    public static void setUserRole(String value){
        editor.putString("UserRole",value).commit();
    }
    public static String getUserRole(){
        return sp.getString("UserRole","");
    }
    //存取记住密码
    public static void setRemember(Boolean value){
        editor.putBoolean("Remember",value).commit();
    }
    public static Boolean getRemember(){
        return sp.getBoolean("Remember",false);
    }
    //存取自动登录
    public static void setLogin(Boolean value){
        editor.putBoolean("Login",value).commit();
    }
    public static Boolean getLogin(){
        return sp.getBoolean("Login",false);
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
