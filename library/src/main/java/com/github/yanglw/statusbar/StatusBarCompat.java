package com.github.yanglw.statusbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by yanglw on 2016-9-14.
 */
public class StatusBarCompat {
    /** 设置状态栏的颜色。 */
    public static void setStatusBarColor(@NonNull Activity activity, @ColorInt int color) {
        setStatusBarColor(activity, color, 0);
    }

    /** 设置状态栏的颜色。 */
    public static void setStatusBarColor(@NonNull Activity activity, @ColorInt int color, @IdRes int fitsViewId) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = getStatusBarHeight(activity);
            if (statusBarHeight <= 0) {
                return;
            }
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            ViewGroup rootView = (ViewGroup) window.getDecorView();
            View view = rootView.findViewById(R.id.status_bar);
            if (view == null) {
                view = new View(activity);
                view.setId(R.id.status_bar);
                rootView.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
            }
            view.setBackgroundColor(color);

            setFitsSystemWindows(activity, fitsViewId, true);
        }
    }

    /** 设置状态栏为透明。 */
    public static void setStatusBarTransparent(@NonNull Activity activity) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int uiVisibility = window.getDecorView().getSystemUiVisibility();
            window.getDecorView()
                  .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                         | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                         | uiVisibility);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup rootView = (ViewGroup) window.getDecorView();
            View view = rootView.findViewById(R.id.status_bar);
            if (view != null) {
                view.setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

    /** 修改状态栏图标为灰色图标。 */
    public static void setStatusBarGaryIcon(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View rootView = activity.getWindow().getDecorView();
            int uiVisibility = rootView.getSystemUiVisibility();
            rootView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | uiVisibility);
        }
    }

    public static void setStatusBarColor2DrawerLayout(@NonNull Activity activity, @ColorInt int statusColor,
                                                      @IdRes int drawerLayoutId, @IdRes int contentGroupId,
                                                      @IdRes int drawerViewId, @IdRes int statusBarId) {
        Window window = activity.getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = getStatusBarHeight(activity);
            if (statusBarHeight <= 0) {
                return;
            }

            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            View view = activity.findViewById(statusBarId);
            if (view != null) {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = statusBarHeight;
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                view.setVisibility(View.VISIBLE);
                view.setLayoutParams(layoutParams);
                view.setBackgroundColor(statusColor);
            }
            setFitsSystemWindows(activity, drawerLayoutId, false);
            setFitsSystemWindows(activity, contentGroupId, false);
            setFitsSystemWindows(activity, drawerViewId, false);
        }
    }

    private static void setFitsSystemWindows(@NonNull Activity activity, @IdRes int id, boolean value) {
        View view = null;
        if (id != 0) {
            view = activity.findViewById(id);
        } else {
            ViewGroup group = (ViewGroup) activity.findViewById(android.R.id.content);
            if (group != null && group.getChildCount() > 0) {
                view = group.getChildAt(0);
            }
        }
        if (view != null) {
            view.setFitsSystemWindows(value);
        }
    }

    public static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId == 0) {
            return 0;
        }
        return context.getResources().getDimensionPixelSize(resourceId);
    }
}
