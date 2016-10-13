package com.github.yanglw.statusbar.samples;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.github.yanglw.statusbar.StatusBarCompat;

public class StatusBarCompatTransparentActivity extends Activity {

    private StatusBarCompatTransparentActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent);
        mContext = this;

        StatusBarCompat.setStatusBarTransparent(this);

        final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                float scrollY = scrollView.getScrollY();
                if (scrollY > getResources().getDimensionPixelSize(R.dimen.image_height) - StatusBarCompat.getStatusBarHeight(mContext)) {
                    StatusBarCompat.setStatusBarColor(mContext,
                                                       ContextCompat.getColor(mContext, R.color.colorPrimaryDarkCyan)
                                                      );
                } else {
                    StatusBarCompat.setStatusBarTransparent(mContext);
                }
            }
        });
    }
}
