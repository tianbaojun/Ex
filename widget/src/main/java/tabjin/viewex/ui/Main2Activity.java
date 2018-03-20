package tabjin.viewex.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import tabjin.viewex.R;
import tabjin.viewex.ui.adapter.Main2Adapter;

public class Main2Activity extends AppCompatActivity {


    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new Main2Adapter());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.main2_item_decoration));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(itemDecoration );
    }
}
