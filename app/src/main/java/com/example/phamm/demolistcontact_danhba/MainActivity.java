package com.example.phamm.demolistcontact_danhba;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TabHost;

public class MainActivity extends FragmentActivity implements TabHost.OnTabChangeListener,ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    TabHost tabHost;
    SectionsPagerAdapter msectionadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msectionadapter =new  SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pagers);
        initialiseTabHost();
        viewPager.setAdapter(msectionadapter);
        viewPager.setOnPageChangeListener(MainActivity.this);

    }
    private static void AddTab(MainActivity activity, TabHost tabHost, TabHost.TabSpec tabSpec) {
        tabSpec.setContent(new TabFactory(activity));
        tabHost.addTab(tabSpec);
    }
    private void initialiseTabHost() {
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        MainActivity.AddTab(this, this.tabHost, this.tabHost.newTabSpec("Contact").setIndicator("Contact"));
        MainActivity.AddTab(this, this.tabHost, this.tabHost.newTabSpec("List Contact").setIndicator("List Contact"));
        tabHost.setOnTabChangedListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int pos  = this.viewPager.getCurrentItem();
        this.tabHost.setCurrentTab(pos);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String s) {
        int position  = this.tabHost.getCurrentTab();
        this.viewPager.setCurrentItem(position);
    }

    class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position){
                case 0: Contact con = new Contact();
                    return con;
                case 1:
                    ListContacts listContacts = new ListContacts();
                    return listContacts;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
