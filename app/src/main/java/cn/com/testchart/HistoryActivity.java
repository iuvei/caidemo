package cn.com.testchart;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import cn.com.testchart.adapter.KaijiangAdapter;
import cn.com.testchart.bean.KaiJiangInfo;
import cn.com.testchart.util.ParseJsonUtil;

/**
 * Created by Administrator on 2018/4/11.
 */
public class HistoryActivity extends AppCompatActivity {

    private ArrayList<KaiJiangInfo> arrayList;
    private ListView listView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            listView.setAdapter(new KaijiangAdapter(HistoryActivity.this, arrayList));
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        String charExtra = getIntent().getStringExtra("TAG");
        listView=findViewById(R.id.hislistview);
        getLotteryData(charExtra);
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
                    }
                });
                mRequestQueue.add(request);
            }

    }.start();

}
}
