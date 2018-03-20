package tabjin.viewex.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Tabjin on 2018/3/15/015.
 */

public class Main2Adapter extends RecyclerView.Adapter<Main2Adapter.MyHolder> {


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView tv = new TextView(parent.getContext());
        return new MyHolder(tv);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tv.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        public TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView;
        }
    }
}
