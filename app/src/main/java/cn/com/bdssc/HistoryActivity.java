package cn.com.bdssc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import cn.com.bdssc.adapter.KaijiangAdapter;
import cn.com.bdssc.adapter.ShapeLoadingDialog;
import cn.com.bdssc.bean.KaiJiangInfo;
import cn.com.bdssc.util.CheckUtil;
import cn.com.bdssc.util.ParseJsonUtil;

/**
 * Created by Administrator on 2018/4/11.
 */
public class HistoryActivity extends AppCompatActivity {

    private ArrayList<KaiJiangInfo> arrayList;
    private ListView listView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                shapeLoadingDialog.dismiss();
                listView.setAdapter(new KaijiangAdapter(HistoryActivity.this, arrayList));
            }else   if(msg.what==2){
                getLotteryData((String) msg.obj);
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        String charExtra = getIntent().getStringExtra("TAG");
        listView=findViewById(R.id.hislistview);
        shouDialog("正在获取中....");
        Message message = handler.obtainMessage();
        message.what=2;
        message.obj=charExtra;
        handler.sendMessageDelayed(message,2500);
        ImageView imageView=findViewById(R.id.chartview);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HistoryActivity.this,PieChartActivity.class));
            }
        });
    }

    private RequestQueue mRequestQueue = null;

    private void getLotteryData(final String tag) {
        if(!CheckUtil.isNetworkAvailable(HistoryActivity.this)){
            Toast.makeText(HistoryActivity.this,"没有检测到数据连接，请检查设备网络状态！",Toast.LENGTH_SHORT).show();
            if(shapeLoadingDialog!=null){
                shapeLoadingDialog.dismiss();
            }

            return;
        }
        arrayList = new ArrayList<>();
        new Thread() {
            @Override
            public void run() {
                mRequestQueue = Volley.newRequestQueue(HistoryActivity.this);

                String url = "http://f.apiplus.net/" + tag + "-20.json";
                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("lee", s);
                        arrayList = ParseJsonUtil.ParseKaijiang(s);
                        handler.sendEmptyMessage(1);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("lee", volleyError.toString());
                        handler.sendEmptyMessage(2);
                        Toast.makeText(HistoryActivity.this,"数据获取失败，请检查网络",Toast.LENGTH_SHORT).show();
                    }
                });
                mRequestQueue.add(request);
            }

    }.start();

}
    private ShapeLoadingDialog shapeLoadingDialog;
    private void shouDialog(String string) {
        shapeLoadingDialog = new ShapeLoadingDialog.Builder(this)
                .loadText(string)
                .build();
        shapeLoadingDialog.setCanceledOnTouchOutside(false);
        shapeLoadingDialog.show();
    }
}
