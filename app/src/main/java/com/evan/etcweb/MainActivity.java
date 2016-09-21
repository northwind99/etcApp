package com.evan.etcweb;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.evan.etcweb.home_indicator.FirstPageFragment;
import com.evan.etcweb.home_indicator.SecondPageFragment;
import com.evan.etcweb.home_indicator.ThirdPageFragment;
import com.evan.etcweb.main.ChurchDetailActivity;
import com.evan.etcweb.main.SelectCRC;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static TabLayout downTabLayout;
    public static TabLayout upTabLayout;
    private static int churchID;

    @BindView(R.id.person_viewPager)
    ViewPager mPager;

    @BindView(R.id.main_tab_pray)
    TextView pray;

    @BindView(R.id.church_selected)
    TextView church_selected;

    @BindView(R.id.select_church_layout)
    RelativeLayout select_church;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){

        try {
            Intent intent = getIntent();
            String churchName = intent.getStringExtra("churchName");
            churchID = intent.getIntExtra("churchID", 0);
            if(churchName.length() != 0) {
                church_selected.setText(churchName);
                church_selected.setTextColor(getResources().getColor(R.color.light_green1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        IndicatorAdapter  adapter = new IndicatorAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstPageFragment());
        adapter.addFragment(new SecondPageFragment());
        adapter.addFragment(new ThirdPageFragment());
        mPager.setAdapter(adapter);
        final CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        pray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(church_selected.getText().toString().contains("Select")){
                    Toast.makeText(getBaseContext(), "Please select a church first.",
                            Toast.LENGTH_LONG).show();
                    ChurchDetailActivity.churchDetailBtn_clicked = false;
                }else {
                    Intent intent = new Intent(getApplicationContext(), SectionsActivity.class);
                    intent.putExtra("churchID", churchID);
                    startActivity(intent);
                }
            }
        });

        select_church.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SelectCRC.class);
                startActivity(intent);
            }
        });

    }

    public static class IndicatorAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();

        public IndicatorAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

    }

    public static class TabAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;
    }
}
