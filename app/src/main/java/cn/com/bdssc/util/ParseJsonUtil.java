package cn.com.bdssc.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.com.bdssc.bean.KaiJiangInfo;


/**
 * Created by Administrator on 2018/1/23.
 */

public class ParseJsonUtil {
    public static ArrayList<String> parseJson(String json) {
        ArrayList list=new ArrayList();
        try {
            JSONObject object = new JSONObject(json);
            JSONArray heWeather6 = object.optJSONArray("HeWeather6");
            JSONObject object1 = heWeather6.optJSONObject(0);
            JSONObject object2 = object1.optJSONObject("now");
            String cond_txt = object2.getString("cond_txt");
            list.add(cond_txt);
            Log.d("lee", cond_txt);
            String tmp = object2.getString("tmp");
            list.add(tmp);
            // JSONObject object2 = object1.optJSONObject("now");
           /* String cond_txt = object2.getString("cond_txt");
            Log.d("lee",cond_txt);*/

        } catch (Exception e) {
            Log.d("lee", e.toString());
        }
        return list;
    }

    public static ArrayList<KaiJiangInfo> ParseKaijiang(String json){
        ArrayList list=new ArrayList();
        try {
            JSONObject object = new JSONObject(json);
            String name=object.getString("code");
            JSONArray data = object.optJSONArray("data");
            Log.d("lee",data.length()+"");
            for (int l=0;l<data.length();l++){
                JSONObject object1=data.getJSONObject(l);
                KaiJiangInfo info=new KaiJiangInfo();
                info.setKaijiangName(name);
                info.setKaijiangCode(object1.getString("opencode"));
                info.setKaijiangNum(object1.getString("opentimestamp"));
                info.setKaijiangDate(object1.getString("opentime"));
                list.add(info);
            }

        } catch (Exception e) {
            Log.d("lee","解析失败"+e.toString());
        }
        return  list;
    }
}
