package cn.com.testchart;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * Created by Administrator on 2018/2/1.
 */

public class ZoushiActivty extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout ssq;
    private LinearLayout fc3d;
    private LinearLayout qlc;
    private LinearLayout pl3;
    private LinearLayout pl5;
    private LinearLayout dlt;
    private LinearLayout qxc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zoushi_activity);
        ssq=findViewById(R.id.ssq);
        ssq.setOnClickListener(this);
        fc3d=findViewById(R.id.fc3d);
        fc3d.setOnClickListener(this);
        dlt=findViewById(R.id.dlt);
        dlt.setOnClickListener(this);
        qlc=findViewById(R.id.qlc);
        qlc.setOnClickListener(this);
        pl3=findViewById(R.id.pl3);
        pl3.setOnClickListener(this);
        pl5=findViewById(R.id.pl5);
        pl5.setOnClickListener(this);
        qxc=findViewById(R.id.qxc);
        qxc.setOnClickListener(this);
        initWeb();
    }
    private PopupWindow popu;
    private WebView webView;
    private void initWeb(){
        View inflate = getLayoutInflater().inflate(R.layout.popupwindow, null);
        popu=new PopupWindow(inflate, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                true);
        //支持点击Back虚拟键退出
        popu.setBackgroundDrawable(new ColorDrawable(0xffffff));
        webView = inflate.findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings .setSupportZoom(false);
        webSettings .setUseWideViewPort(true);
        webSettings .setLoadWithOverviewMode(true);
        webSettings .setLoadsImagesAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ssq:
                popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                webView.loadUrl("file:///android_asset/ssq.html");
                break;
            case R.id.fc3d:
                popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                webView.loadUrl("file:///android_asset/fc3d.html");
                break;
            case R.id.qlc:
                popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                webView.loadUrl("file:///android_asset/qlc.html");
                break;
            case R.id.dlt:
                popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                webView.loadUrl("file:///android_asset/dlt.html");
                break;
            case R.id.pl3:
                popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                webView.loadUrl("file:///android_asset/pl3.html");
                break;
            case R.id.pl5:
                popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                webView.loadUrl("file:///android_asset/pl5.html");
                break;
            case R.id.qxc:
                popu.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                webView.loadUrl("file:///android_asset/qxc.html");
                break;
        }
    }
}
