package com.sds.study.recordapp.page;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sds.study.recordapp.R;

public class MainActivity extends AppCompatActivity {//AppcompatActivity는 과거 버전까지 다 호환이 된다.(앱을만들때 activity보단 이걸 선호하자!)

    ViewPager viewPager;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager=(ViewPager)findViewById(R.id.viewPager);

        myAdapter=new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myAdapter);
    }
    public void btnClick(View view){
        switch (view.getId()){
            case R.id.bt_a:viewPager.setCurrentItem(0);break;//item은 페이지를 말한다
            case R.id.bt_b:viewPager.setCurrentItem(1);break;
            case R.id.bt_c:viewPager.setCurrentItem(2);break;
        }
    }
}
