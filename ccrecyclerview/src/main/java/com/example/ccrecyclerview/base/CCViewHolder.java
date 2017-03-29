package com.example.ccrecyclerview.base;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
/**
 * Created by henryzheng on 2017/3/15.
 */
public class CCViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
    View convertView;
    Context context;
    private CCRecycleViewListener.OnItemClickListener onItemClickListener;
    private CCRecycleViewListener.OnItemLongClickListener onItemLongClickListener;
    /**
     * View滑动时执行的函数
     * @param context
     * @param itemView
     * @param onItemClickListener
     * @param onItemLongClickListner
     */
    public CCViewHolder(Context context, View itemView, CCRecycleViewListener.OnItemClickListener onItemClickListener, CCRecycleViewListener.OnItemLongClickListener onItemLongClickListner) {
        super(itemView);
        this.convertView = itemView;
        this.context = context;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        this.onItemClickListener=onItemClickListener;
        this.onItemLongClickListener=onItemLongClickListner;
    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClickListener(v,getPosition());
    }
    public void setText(int id, String text) {
        TextView tx = (TextView) convertView.findViewById(id);
        tx.setText(text);
    }

    @Override
    public boolean onLongClick(View v) {
        onItemLongClickListener.onItemLongClickListener(v,getPosition());
        return false;
    }
}
