package chuang.swipemenulistviewdemo.android.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import chuang.swipemenulistviewdemo.R;
import chuang.swipemenulistviewdemo.android.fragment.DifferentMenuFragment;
import chuang.swipemenulistviewdemo.android.fragment.SimpleMenuFragment;

/**
 * Created by N-251 on 2015/2/12.
 */
public class SwipeMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipemenu);
        String menu = getIntent().getStringExtra("menu");

        //pre data
        //pre data
        ArrayList<ApplicationInfo> applicationInfoList = (ArrayList<ApplicationInfo>) getPackageManager().getInstalledApplications(0);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("appinfo", applicationInfoList);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (menu.equals("simple")) {
            SimpleMenuFragment simpleMenuFragment = new SimpleMenuFragment();
            simpleMenuFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.content, simpleMenuFragment, SimpleMenuFragment.class.getSimpleName());
        } else if (menu.equals("different")) {
            DifferentMenuFragment differentMenuFragment = new DifferentMenuFragment();
            differentMenuFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.content, differentMenuFragment, DifferentMenuFragment.class.getSimpleName());
        }

        fragmentTransaction.commit();

    }
}
