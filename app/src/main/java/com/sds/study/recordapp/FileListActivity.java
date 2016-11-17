package com.sds.study.recordapp;

/*
    녹음으로 인하여 생성된 파일을 목록으로 보여주고,
    해당 파일을 선택하면 재생시키자!!
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sds.study.recordapp.record.RecoardPageAdapter;

public class FileListActivity extends AppCompatActivity{
    String TAG;
    ViewPager viewPager;
    RecoardPageAdapter pageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        TAG=this.getClass().getName();
        Log.d(TAG,"FileListActivity : "+this);
        viewPager=(ViewPager)findViewById(R.id.viewPager);

        //현재 액티비티가 Appcompat일때만 가능하다(getSupportFragmentManager).
        pageAdapter=new RecoardPageAdapter(getSupportFragmentManager());

        //연결
        viewPager.setAdapter(pageAdapter);


    }
}
