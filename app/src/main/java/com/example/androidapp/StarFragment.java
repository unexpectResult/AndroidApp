package com.example.androidapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.androidapp.bean.StarInfoBean;
import com.example.androidapp.starfrag.StarBaseAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * 星座Fragment
 * 包含ViewPaper和GridView
 */
public class StarFragment extends Fragment {

    ViewPager starVp;
    GridView starGv;
    LinearLayout pointLayout;
    private List<StarInfoBean.StarinfoBean> mDadas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_star, container, false);
        initView(view);
        Bundle bundle = getArguments();
        StarInfoBean infoBean = (StarInfoBean)bundle.getSerializable("info");
        mDadas = infoBean.getStarinfo();//获取关于星座信息的集合数据
        //        创建适配器
        StarBaseAdapter starBaseAdapter = new StarBaseAdapter(getContext(), mDadas);
        starGv.setAdapter(starBaseAdapter);
        return view;

    }

//    初始化控件的操作
    private void initView(View view) {
        starVp = view.findViewById(R.id.starfrag_vp);
        starGv = view.findViewById(R.id.starfrag_gv);
        pointLayout = view.findViewById(R.id.starfrag_layout);
    }
}