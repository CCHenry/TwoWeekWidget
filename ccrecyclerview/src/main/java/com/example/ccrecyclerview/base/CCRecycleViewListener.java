package com.example.ccrecyclerview.base;

import android.view.View;

/**
 * Created by henryzheng on 2017/3/15.
 */

public class CCRecycleViewListener {
    public interface OnItemClickListener {
        void onItemClickListener(View v, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClickListener(View v, int position);
    }
}
