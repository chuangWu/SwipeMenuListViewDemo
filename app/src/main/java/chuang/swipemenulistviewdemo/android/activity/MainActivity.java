package chuang.swipemenulistviewdemo.android.activity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;

import chuang.swipemenulistviewdemo.R;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button simpleMenu = (Button) findViewById(R.id.simple_menu);
        Button differentMenu = (Button) findViewById(R.id.different_menu);

        simpleMenu.setOnClickListener(this);
        differentMenu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SwipeMenuActivity.class);
        switch (v.getId()) {
            case R.id.simple_menu:
                intent.putExtra("menu", "simple");
                break;
            case R.id.different_menu:
                intent.putExtra("menu", "different");
                break;
        }
        startActivity(intent);
    }
}
