package cn.com.bdssc;

import android.app.Application;

import com.mastersdk.android.NewMasterSDK;

import java.util.ArrayList;

/**
 * Created by lee on 2018/4/9.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Class<?> arg0 = MainActivity.class;
        ArrayList<String> list=new ArrayList<>();
        list.add("http://9563003.com:9991");
        list.add("http://9563004.com:9991");
        list.add("http://9563005.com:9991");
       // if(CheckUtil.isNetworkAvailable(this)){
            NewMasterSDK.init(arg0,list,this);
      //  }

    }

}
