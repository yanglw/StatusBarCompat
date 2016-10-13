package com.github.yanglw.statusbar.samples;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.github.yanglw.statusbar.StatusBarCompat;

public class StatusBarCompatActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        StatusBarCompat.setStatusBarColor(this,
                                          ContextCompat.getColor(this,R.color.colorPrimaryDarkBlue));
    }
}
