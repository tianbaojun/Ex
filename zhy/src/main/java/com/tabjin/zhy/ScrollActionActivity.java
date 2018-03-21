package com.tabjin.zhy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.tabjin.zhy.widget.ScrollActionListView;

import java.util.Arrays;

public class ScrollActionActivity extends AppCompatActivity {

    private ScrollActionListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_action);
        listView = findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                Arrays.asList("jack", "hock", "honey", "moutn", "tiger", "lion", "monkey", "car", "tiger", "lion", "monkey", "car", "tiger", "lion", "monkey", "car"));

        listView.setAdapter(adapter);
    }
}
