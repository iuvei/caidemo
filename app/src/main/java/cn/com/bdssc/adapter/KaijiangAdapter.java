package cn.com.bdssc.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.com.bdssc.R;
import cn.com.bdssc.bean.KaiJiangInfo;

/**
 * Created by Administrator on 2018/1/25.
 */

public class KaijiangAdapter extends BaseAdapter{
    private List<KaiJiangInfo> kaijiangList;
    private Context mContext;
    SharedPreferences caipiaoName;
    public KaijiangAdapter(Context ctx, List<KaiJiangInfo>list){
        mContext=ctx;
        Log.d("lee","开奖list:"+list.size());
        kaijiangList=list;
        caipiaoName = mContext.getSharedPreferences("CAIPIAONAME", Context.MODE_PRIVATE);
    }
    @Override
    public int getCount() {
        return kaijiangList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHoder myHoder;
        if(convertView==null){
            myHoder=new MyHoder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lottery_info, null);
            //图片
            myHoder.kaijiangima=(ImageView)convertView.findViewById(R.id.caipiaoimage);
            //期号
            myHoder.kaijiangNum=convertView.findViewById(R.id.tv_odd_number);
            //号码
            myHoder.kaijiangCode=convertView.findViewById(R.id.tv_lottery_number);
            //时间
            myHoder.kaijiangDate=convertView.findViewById(R.id.tv_time);

           /* myHoder.kaijiangNum=convertView.findViewById(R.id.kaijiangdate);
            myHoder.kaijiangCode=convertView.findViewById(R.id.kaijiangnum);*/
           // myHoder.kaijiangDate=convertView.findViewById(R.id.kaijiangdate);
            convertView.setTag(myHoder);
        }else{
            myHoder=(MyHoder)convertView.getTag();
        }
        KaiJiangInfo kaiJiangInfo = kaijiangList.get(position);
        Log.d("lee","kaiJiangInfo.getKaijiangName()"+kaiJiangInfo.getKaijiangName());
        if(myHoder.kaijiangima==null){
            Log.d("lee","fuck");
        }else{
            myHoder.kaijiangima.setBackgroundResource(caipiaoName.getInt(kaiJiangInfo.getKaijiangName(),R.drawable.ic_launcher));
        }
        myHoder.kaijiangDate.setText("开奖日期："+kaiJiangInfo.getKaijiangDate());
        myHoder.kaijiangNum.setText("开奖期号："+kaiJiangInfo.getKaijiangNum());
        myHoder.kaijiangCode.setText("开奖号码："+kaiJiangInfo.getKaijiangCode());
        return convertView;
    }

    class  MyHoder  {
        ImageView kaijiangima;
        TextView kaijiangNum;
        TextView kaijiangCode;
        TextView kaijiangDate;

    }
}
