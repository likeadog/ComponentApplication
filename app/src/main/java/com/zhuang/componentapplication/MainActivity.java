package com.zhuang.componentapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.zhuang.home.HomeMainFragment;
import com.zhuang.personal.PersonalMainFragment;
import com.zhuang.video.VideoMainFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private Fragment[] fragments = new Fragment[3];
    private int currentIndex = 0;//当前显示的fragment的索引位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initHomeFragment();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    /**
     * 初始化主页
     */
    public void initHomeFragment() {
        if (fragments[0] == null) {
            fragments[0] = new HomeMainFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.content, fragments[0], "home").commit();
        } else {
            getSupportFragmentManager().beginTransaction().show(fragments[0]);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                if (currentIndex == 0) return true;//如果已经是当前的fragment，不用切换
                FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
                hideAndShow(0,transition);
                return true;

            case R.id.navigation_video:
                if (currentIndex == 1) return true;//如果已经是当前的fragment，不用切换
                FragmentTransaction transition1 = getSupportFragmentManager().beginTransaction();
                if (fragments[1] == null) {
                    fragments[1] = new VideoMainFragment();
                    transition1.add(R.id.content, fragments[1], "video");
                }
                hideAndShow(1,transition1);
                return true;

            case R.id.navigation_personal:
                if (currentIndex == 2) return true;//如果已经是当前的fragment，不用切换
                FragmentTransaction transition2 = getSupportFragmentManager().beginTransaction();
                if (fragments[2] == null) {
                    fragments[2] = new PersonalMainFragment();
                    transition2.add(R.id.content, fragments[2], "personal");
                }
                hideAndShow(2,transition2);
                return true;
        }

        return false;
    }

    /**
     * 除了指定的fragment不hide，其他fragment全hide
     *
     * @param expectIndex 指定的fragment在fragments中的位置
     */
    private void hideAndShow(int expectIndex,FragmentTransaction transition) {
        for (int i = 0; i < fragments.length; i++) {
            if (i != expectIndex && fragments[i] != null) {
                transition.hide(fragments[i]);
            }
        }
        transition.show(fragments[expectIndex]);
        transition.commit();
        currentIndex = expectIndex;
    }
}
