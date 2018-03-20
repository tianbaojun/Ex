package com.tabjin.ex.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.tabjin.ex.R;
import com.tabjin.ex.bean.Function;
import com.tabjin.ex.ui.ItemDecoration.GridWhiteItemDecoration;
import com.tabjin.ex.ui.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * tabjin
 * 作为一个菜单launcher
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private MainAdapter mainAdapter;

    private List<Function> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager manager = new GridLayoutManager(this, 4, OrientationHelper.VERTICAL, false);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        GridWhiteItemDecoration itemDecoration = new GridWhiteItemDecoration(getResources().getDrawable(R.drawable.item_decoration_white));
        recyclerView.addItemDecoration(itemDecoration);

        initActivities();
        mainAdapter = new MainAdapter(list);
        recyclerView.setAdapter(mainAdapter);
    }

    private void initActivities() {
        Function function = new Function("滑动冲突", ScrollConflictActivity.class);
        list.add(function);
    }
}
