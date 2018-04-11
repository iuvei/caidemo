package cn.com.testchart;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.stonesun.newssdk.NewsAgent;
import com.stonesun.newssdk.fragment.NewsAFragment;
import com.stonesun.newssdk.sharesdk.NewsSDKShare;

import cn.com.testchart.adapter.ShapeLoadingDialog;

/**
 * Created by Administrator on 2018/1/30.
 */

public class NewsActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    private ShapeLoadingDialog shapeLoadingDialog;
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
        //    shapeLoadingDialog.dismiss();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);
        /*shapeLoadingDialog = new ShapeLoadingDialog.Builder(this)
                .loadText("新闻获取中...")
                .build();
        shapeLoadingDialog.setCanceledOnTouchOutside(false);
        shapeLoadingDialog.show();*/
        frameLayout=findViewById(R.id.fl_main);
        try {
            NewsAgent.setDebugMode(true);//打开调试日志
            NewsAgent.setPermission(this, true);//sdk请求权限
            NewsAgent.init(this);
            NewsAgent.createShareCommentViewActivity("详情页");
            NewsAgent.setLoading(false, true, true, true);
            NewsAgent.SetUserImpForSDK(null,ToastActivity.class);
            NewsAgent.setShowShare(new NewsSDKShare(),"详情页");
            NewsAgent.createDefaultRecomFragment("导航栏", "SPOTS-414592", "详情页");
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            NewsAFragment recomFragment = NewsAgent.getDefaultRecomFragment("导航栏");
            transaction.add(R.id.fl_main, recomFragment).commit();
          //  transaction.replace(R.id.fl_main, recomFragment).commit();
            //  NewsAgent.setContentViewActivity(new WebContentView(),"重写的详情页");
        }catch (Exception e){
              Toast.makeText(this,"获取失败，稍后重试",Toast.LENGTH_SHORT).show();
            Log.d("lee",e.toString());
        }
        handler.sendEmptyMessageDelayed(1,1500);

    }

}
