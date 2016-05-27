package com.abhilashmishra.playmenttest;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private int[] colors;
    private ArgbEvaluator argbEvaluator;
    private RelativeLayout parentLayout;
    private ImageView imageView;
    int heightPixels;
    int widthPixels;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        imageView = (ImageView)findViewById(R.id.imageView);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        parentLayout = (RelativeLayout)findViewById(R.id.parentlayout);
        argbEvaluator = new ArgbEvaluator();
        colors = getResources().getIntArray(R.array.colors);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        heightPixels = px2dp(metrics.heightPixels);
        widthPixels = px2dp(metrics.widthPixels);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            float sumPosOffset=-1;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position < (mSectionsPagerAdapter.getCount() -1) && position < (colors.length - 1)) {
                    parentLayout.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]));
                } else {
                    parentLayout.setBackgroundColor(colors[colors.length - 1]);
                }

                boolean left=false;
                if(position+positionOffset>sumPosOffset){
                    left=true;
                }else {
                    left=false;
                }

                //System.out.println(position+"--"+positionOffset);
                RelativeLayout.LayoutParams imageLayoutParams =(RelativeLayout.LayoutParams) imageView.getLayoutParams();
                if(position==0){

                    imageLayoutParams.height = dp2px((int)(200+200*positionOffset));
                    imageLayoutParams.width = dp2px((int)(100+150*positionOffset));
                    imageLayoutParams.leftMargin = dp2px((int)((int)(32+32*positionOffset)));
//                    imageLayoutParams.leftMargin = dp2px((int)((int)(32+32*positionOffset)));
                    imageView.setLayoutParams(imageLayoutParams);
                }else if (position==1){
                    imageLayoutParams.leftMargin = dp2px(64) - positionOffsetPixels;
                    imageLayoutParams.rightMargin = dp2px(64) + positionOffsetPixels;
                    imageView.setLayoutParams(imageLayoutParams);
                }else if (position==2){

                }

                sumPosOffset = position+positionOffset;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }





    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:return new Fragment1();
                case 1:return new Fragment2();
                case 2:return new Fragment3();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,   dp,this.getResources().getDisplayMetrics());
    }
    public int px2dp(float px)  {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px,this.getResources().getDisplayMetrics());
    }
}
