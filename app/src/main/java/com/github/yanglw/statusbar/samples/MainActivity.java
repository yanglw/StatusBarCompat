package com.github.yanglw.statusbar.samples;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new SimpleAdapter(this, getData(),
                                         android.R.layout.simple_list_item_1, new String[]{"title"},
                                         new int[]{android.R.id.text1}));
    }

    protected List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN);
        mainIntent.addCategory(Intent.CATEGORY_SAMPLE_CODE);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(mainIntent, 0);

        if (null == resolveInfoList) {
            return list;
        }

        int len = resolveInfoList.size();

        for (int i = 0; i < len; i++) {
            ResolveInfo info = resolveInfoList.get(i);
            CharSequence labelSeq = info.loadLabel(pm);
            String label = labelSeq != null
                           ? labelSeq.toString()
                           : info.activityInfo.name;
            if (info.activityInfo.applicationInfo.packageName.equals(getPackageName())) {
                Intent intent = new Intent();
                intent.setClassName(info.activityInfo.applicationInfo.packageName, info.activityInfo.name);

                Map<String, Object> temp = new HashMap<>();
                temp.put("title", label);
                temp.put("intent", intent);
                list.add(temp);
            }
        }

        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);

        Intent intent = new Intent((Intent) map.get("intent"));
        intent.addCategory(Intent.CATEGORY_SAMPLE_CODE);
        startActivity(intent);
    }
}



























