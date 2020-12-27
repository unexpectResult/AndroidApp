package com.example.androidapp.starfrag;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidapp.R;
import com.example.androidapp.bean.StarInfo;
import com.example.androidapp.utils.AssetsUtils;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class StarBaseAdapter extends BaseAdapter {
    Context context;
    List<StarInfo.StarinfoBean> mDatas;

    public StarBaseAdapter(Context context, List<StarInfo.StarinfoBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        logoMap = AssetsUtils.getLogoImgMap();
    }

    Map<String, Bitmap> logoMap;

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_star_gv,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        //获取指定位置的数据
        StarInfo.StarinfoBean bean = mDatas.get(position);
        holder.tv.setText(bean.getName());
        //根据图片名称在内存当中进行查找图片
        String logoname = bean.getLogoname();
        Bitmap bitmap = logoMap.get(logoname);
        holder.iv.setImageBitmap(bitmap);
        return convertView;
    }

//    对于item 当中的控件进行声明和初始化的操作
    class ViewHolder{
        CircleImageView iv;
        TextView tv;
        public ViewHolder(View view){
            iv = view.findViewById(R.id.item_star_iv);
            tv = view.findViewById(R.id.item_star_tv);
        }
    }
}
