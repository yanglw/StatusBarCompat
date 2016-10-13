package com.github.yanglw.statusbar.samples;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.github.yanglw.statusbar.StatusBarCompat;

public class DrawerLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        int statusColor = ContextCompat.getColor(this, R.color.colorPrimaryDarkRed);
        StatusBarCompat.setStatusBarColor2DrawerLayout(this,
                                                       statusColor,
                                                       R.id.drawer_layout,
                                                       R.id.content_group,
                                                       R.id.navigation,
                                                       R.id.view);
    }
}
