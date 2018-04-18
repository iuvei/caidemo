package cn.com.bdssc.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Administrator on 2018/4/17.
 */

public class CheckUtil {
    //网络是否连接
    public static boolean isNetworkAvailable(Context context) {
        boolean flag = false;
        ConnectivityManager systemService = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (systemService.getActiveNetworkInfo() != null) {
            flag = systemService.getActiveNetworkInfo().isAvailable();
        }
        return flag;
    }
}
