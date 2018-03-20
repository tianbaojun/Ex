package com.tabjin.ex.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.tabjin.ex.R;

/**
 * 滑动冲突
 */
public class ScrollConflictActivity extends AppCompatActivity {

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_conflict);
        listview = findViewById(R.id.listview);
//        listview.setAdapter();
    }
}
