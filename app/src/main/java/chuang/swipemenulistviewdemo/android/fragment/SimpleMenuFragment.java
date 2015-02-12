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
import chuang.swipemenulistviewdemo.android.adapter.SimpleMenuAdapter;
import chuang.swipemenulistviewdemo.widget.lib_swipemenu.SwipeMenu;
import chuang.swipemenulistviewdemo.widget.lib_swipemenu.SwipeMenuCreator;
import chuang.swipemenulistviewdemo.widget.lib_swipemenu.SwipeMenuItem;
import chuang.swipemenulistviewdemo.widget.lib_swipemenu.SwipeMenuListView;

/**
 * Created by N-251 on 2015/2/12.
 */
public class SimpleMenuFragment extends Fragment {

    private SwipeMenuListView mSwipeListView;
    private SimpleMenuAdapter mSimpleMenuAdapter;
    private ArrayList<ApplicationInfo> mApplicationInfoList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(null !=bundle){
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
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getActivity());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mSwipeListView.setMenuCreator(creator);

    }


    private void initView(View contentView) {
        mSwipeListView = (SwipeMenuListView) contentView.findViewById(R.id.swipe_listview);
        mSimpleMenuAdapter = new SimpleMenuAdapter(getActivity(),mApplicationInfoList);
        mSwipeListView.setAdapter(mSimpleMenuAdapter);
        //1.
       mSwipeListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
           @Override
           public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
               switch (index){
                   case 0:
                       Toast.makeText(getActivity(),"Open",Toast.LENGTH_SHORT).show();
                       break;

                   case 1:
                       Toast.makeText(getActivity(),"Close",Toast.LENGTH_SHORT).show();
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
        mSwipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"test",Toast.LENGTH_SHORT).show();
            }
        });
        //5.
        //mSwipeListView.setOpenInterpolator(new BounceInterpolator());
        //6.
        //mSwipeListView.setCloseInterpolator(new AccelerateDecelerateInterpolator());
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
