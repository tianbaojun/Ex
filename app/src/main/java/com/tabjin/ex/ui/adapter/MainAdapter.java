package com.tabjin.ex.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tabjin.ex.R;
import com.tabjin.ex.bean.Function;

import java.util.List;

/**
 * Created by tabjin on 18-3-15.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyHolder> {

    private List<Function> list;

    public MainAdapter(List<Function> list) {
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final Function function = list.get(position);
        holder.tv.setText(function.name);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), function.activityName);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        CardView cardView;

        TextView tv;

        public MyHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
