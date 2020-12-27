package com.example.androidapp;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidapp.bean.StarInfoBean;
import com.example.androidapp.utils.AssetsUtils;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements  RadioGroup.OnCheckedChangeListener{

    RadioGroup mainRg;
    //声明四个按钮对应的Fragment对象
    Fragment starFrag,luckFrag,matchFrag,meFrag;
    private FragmentManager manager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        设置监听
        mainRg = findViewById(R.id.main_rg);
        mainRg.setOnCheckedChangeListener(this);

//        将在星座相关数据  /assets/xzcontent/xzcontent.json
        StarInfoBean jsonInfoBean = loadData();
        Bundle bundle = new Bundle();
        bundle.putSerializable("info",jsonInfoBean);
//        创建碎片对象
//        val gameFragment = new Fragment();
        starFrag = new StarFragment();
        starFrag.setArguments(bundle);
        luckFrag = new LuckFragment();
        luckFrag.setArguments(bundle);
        matchFrag = new MatchFragment();
        matchFrag.setArguments(bundle);
        meFrag = new MeFragment();
        meFrag.setArguments(bundle);
//        将四个Fragment进行动态加载，一起加载到布局当中
        iniFragmentPage();
    }

//    读取assets文件夹下的xzcontent.json文件
    private StarInfoBean loadData() {
        String jsonFromAssets = AssetsUtils.getJsonFromAssets(this, "xzcontent/xzcontent.json");
        System.out.println(jsonFromAssets);
        Gson gson = new Gson();
        StarInfoBean starInfoBean = gson.fromJson(jsonFromAssets, StarInfoBean.class);
        AssetsUtils.saveBitmapAssets(this,starInfoBean);
        return starInfoBean;
    }

    /**
     * 将主页当中的碎片一起加载进入布局，有用的显示，暂时无用的隐藏
     */
    private void iniFragmentPage() {
        //创建碎片管理者对象
        manager = getSupportFragmentManager();
//        创建碎片处理事务的对象
        FragmentTransaction transaction = manager.beginTransaction();
//        将四个Fragment统一的添加到布局当中
        transaction.add(R.id.main_layout_center,starFrag);
        transaction.add(R.id.main_layout_center,matchFrag);
        transaction.add(R.id.main_layout_center,luckFrag);
        transaction.add(R.id.main_layout_center,meFrag);
        //隐藏后面的三个
        transaction.hide(matchFrag);
        transaction.hide(luckFrag);
        transaction.hide(meFrag);
        //提交碎片改后的事务
        transaction.commit();
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (checkedId){
            case R.id.main_rb_star:
                transaction.hide(matchFrag);
                transaction.hide(luckFrag);
                transaction.hide(meFrag);
                transaction.show(starFrag);
                break;
            case R.id.main_rb_match:
                transaction.hide(starFrag);
                transaction.hide(luckFrag);
                transaction.hide(meFrag);
                transaction.show(matchFrag);
                break;
            case R.id.main_rb_luck:
                transaction.hide(matchFrag);
                transaction.hide(starFrag);
                transaction.hide(meFrag);
                transaction.show(luckFrag);
                break;
            case R.id.main_rb_me:
                transaction.hide(matchFrag);
                transaction.hide(luckFrag);
                transaction.hide(starFrag);
                transaction.show(meFrag);
                break;
        }
        transaction.commit();
    }
}