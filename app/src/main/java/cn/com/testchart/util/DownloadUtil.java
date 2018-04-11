package cn.com.testchart.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2018/2/6.
 */

public class DownloadUtil {
    private static Handler handler1 = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what==1){
                pBar.cancel();
                update(mContext);
            }else {
                pBar.show();
            }

        }
    };

    public static Context mContext;
    public static ProgressDialog pBar;
     public   static void downFile(Context ctx,final String url) {
         mContext=ctx;
        pBar = new ProgressDialog(ctx, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
        pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pBar.setProgressNumberFormat("%1d kb/%2d kb");
        pBar.setCanceledOnTouchOutside(false);
        pBar.setCancelable(false);
        pBar.setTitle("正在下载");
        /*pBar.setMessage("请稍候...");
        pBar.setButton("后台下载", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                pBar.hide();
               // notifyKJ();
            }
        });*/
        pBar.setProgress(0);
        handler1.sendEmptyMessage(2);
        new Thread() {
            public void run() {
                Log.d("lee","联网下载");
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
                org.apache.http.HttpResponse response;
                try {
                    SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    int length = (int) entity.getContentLength(); // 获取文件大小
                    Log.d("lee","文件总大小：："+length);
                    pBar.setMax(length); // 设置进度条的总长度
                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {
                        File file =new File( Environment.getExternalStorageDirectory()+"/bjsc");
                        if(!file.exists()){
                            file.mkdirs();
                        }
                        File file1 = new File(
                                Environment.getExternalStorageDirectory()+"/bjsc",
                                "115.apk");
                        fileOutputStream = new FileOutputStream(file1);
                        byte[] buf = new byte[200]; // 这个是缓冲区
                        int ch = -1;
                        int process = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            process += ch;
                            pBar.setProgress(process); // 这里就是关键的实时更新进度了！
                        }
                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    handler1.sendEmptyMessageDelayed(1,500);
                } catch (Exception e) {
                    Log.d("lee","联网错误！"+e.toString());
                    e.printStackTrace();
                }
            }

        }.start();
    }

    protected static void update(Context ctx) {
        // TODO Auto-generated method stub
        Log.d("lee", "开始执行安装: ");
        /*Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(
                Uri.fromFile(new File(
                        Environment.getExternalStorageDirectory(), "ssc.apk")),"application/vnd.android.package-archive");
                startActivity(intent);*/
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File apkFile = new File(Environment.getExternalStorageDirectory()+"/bjsc/115.apk");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.d("lee", "版本大于 N ，开始使用 fileProvider 进行安装");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(
                    ctx
                    , "com.cn.sschwsc.fileprovider"
                    , apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            Log.d("lee", "正常进行安装");
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        ctx.startActivity(intent);
    }

}
