package com.sds.study.recordapp.page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;




/**
    viewPager는 껍데기에 불과하므로, 뷰페이저에 어떤 보이질 프래그먼트가 총 몇개고, 어떤 페이지를 보여줘야 할지를
    관리해주는 어댑터를 이용해야 한다!!
 */

public class MyAdapter extends FragmentStatePagerAdapter {

    //앱에서 사용할 Fragment를 준비해 놓자!!
    Fragment[] fragments=new Fragment[3];

    public MyAdapter(FragmentManager fm) {//생성자 추가!
        super(fm);

        fragments[0]=new FragmentA();
        fragments[1]=new FragmentB();
        fragments[2]=new FragmentC();
    }

    @Override
    public int getCount() {//  총 몇페이지 인지.
        return fragments.length;
    }

    @Override
    public Fragment getItem(int position) {//어떤걸 보여줘야 할지
        return fragments[position];
    }
}
