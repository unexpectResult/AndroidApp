package com.example.androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),RadioGroup.OnCheckedChangeListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        设置监听
        main_rg.setOnCheckedChangeListener(this);
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when(checkedId){
            R.id.main_rb_star  -> null;
            R.id.main_rb_match -> null;
            R.id.main_rb_luck  -> null;
            R.id.main_rb_me    -> null;
        }
    }

}