package com.example.ccrecyclerview;
import android.content.Context;
import com.example.ccrecyclerview.base.CCRecyclerViewAdapt;
import com.example.ccrecyclerview.base.CCViewHolder;
import java.util.List;
/**
 * Created by henryzheng on 2017/3/15.
 */

public class TestRecyclerAdapt extends CCRecyclerViewAdapt{

    public TestRecyclerAdapt(Context context) {
        super(context);
    }
    public TestRecyclerAdapt(Context context, List datas) {
        super(context, datas);
    }

    @Override
    protected void convert(CCViewHolder holder, Object bean) {
        Person p= (Person) bean;
        holder.setText(R.id.tv_name,p.getName());
        holder.setText(R.id.tv_year,p.getYear());
    }
    @Override
    public int getItemLayoutId() {
        return R.layout.main_activity_recycler_view_item_view;
    }

}
