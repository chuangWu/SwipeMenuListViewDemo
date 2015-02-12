package chuang.swipemenulistviewdemo.android.fragment;

import android.app.Fragment;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

import chuang.swipemenulistviewdemo.R;
import chuang.swipemenulistviewdemo.android.adapter.DifferentMenuAdapter;
import chuang.swipemenulistviewdemo.android.adapter.SimpleMenuAdapter;
import chuang.swipemenulistviewdemo.widget.lib_swipemenu.SwipeMenu;
import chuang.swipemenulistviewdemo.widget.lib_swipemenu.SwipeMenuCreator;
import chuang.swipemenulistviewdemo.widget.lib_swipemenu.SwipeMenuItem;
import chuang.swipemenulistviewdemo.widget.lib_swipemenu.SwipeMenuListView;

/**
 * Created by N-251 on 2015/2/12.
 */
public class DifferentMenuFragment extends Fragment {

    private SwipeMenuListView mSwipeListView;
    private DifferentMenuAdapter mDifferentMenuAdapter;
    private ArrayList<ApplicationInfo> mApplicationInfoList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (null != bundle) {
            mApplicationInfoList = bundle.getParcelableArrayList("appinfo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_simple_menu, container, false);
        initView(contentView);
        createMenu();
        return contentView;
    }

    private void createMenu() {
        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                switch (menu.getViewType()) {
                    case 0:
                        createMenu1(menu);
                        break;
                    case 1:
                        createMenu2(menu);
                        break;
                    case 2:
                        createMenu3(menu);
                        break;
                }
            }
        };
        // set creator
        mSwipeListView.setMenuCreator(creator);

    }

    private void createMenu1(SwipeMenu menu) {
        SwipeMenuItem item1 = new SwipeMenuItem(getActivity());
        item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0x18,0x5E)));
        item1.setWidth(dp2px(90));
        item1.setIcon(R.drawable.ic_action_favorite);
        menu.addMenuItem(item1);
        SwipeMenuItem item2 = new SwipeMenuItem(getActivity());
        item2.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,0xCE)));
        item2.setWidth(dp2px(90));
        item2.setIcon(R.drawable.ic_action_good);
        menu.addMenuItem(item2);
    }

    private void createMenu2(SwipeMenu menu) {
        SwipeMenuItem item1 = new SwipeMenuItem(getActivity());
        item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0xE0,0x3F)));
        item1.setWidth(dp2px(90));
        item1.setIcon(R.drawable.ic_action_important);
        menu.addMenuItem(item1);
        SwipeMenuItem item2 = new SwipeMenuItem(getActivity());
        item2.setBackground(new ColorDrawable(Color.rgb(0xF9,0x3F, 0x25)));
        item2.setWidth(dp2px(90));
        item2.setIcon(R.drawable.ic_action_discard);
        menu.addMenuItem(item2);
    }

    private void createMenu3(SwipeMenu menu) {
        SwipeMenuItem item1 = new SwipeMenuItem(getActivity());
        item1.setBackground(new ColorDrawable(Color.rgb(0x30, 0xB1,0xF5)));
        item1.setWidth(dp2px(90));
        item1.setIcon(R.drawable.ic_action_about);
        menu.addMenuItem(item1);
        SwipeMenuItem item2 = new SwipeMenuItem(getActivity());
        item2.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,0xCE)));
        item2.setWidth(dp2px(90));
        item2.setIcon(R.drawable.ic_action_share);
        menu.addMenuItem(item2);
    }

    private void initView(View contentView) {
        mSwipeListView = (SwipeMenuListView) contentView.findViewById(R.id.swipe_listview);
        mDifferentMenuAdapter = new DifferentMenuAdapter(getActivity(), mApplicationInfoList);
        mSwipeListView.setAdapter(mDifferentMenuAdapter);
        //1.
        mSwipeListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Toast.makeText(getActivity(), "Open", Toast.LENGTH_SHORT).show();
                        break;

                    case 1:
                        Toast.makeText(getActivity(), "Close", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        //2.
        mSwipeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mSwipeListView.smoothOpenMenu(position);
                return false;
            }
        });
        //3.
        mSwipeListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {

            }

            @Override
            public void onSwipeEnd(int position) {

            }
        });
        //4.
        //mSwipeListView.setOpenInterpolator(new BounceInterpolator());
        //5.
        //mSwipeListView.setCloseInterpolator(new AccelerateDecelerateInterpolator());
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
