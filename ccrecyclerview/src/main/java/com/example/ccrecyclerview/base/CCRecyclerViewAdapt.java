package com.example.ccrecyclerview.base;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.commonlibrary.utils.CCLog;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by henryzheng on 2017/3/15.
 */
public abstract class CCRecyclerViewAdapt<T> extends RecyclerView.Adapter<CCViewHolder> {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private CCViewHolder holder; //自定义ViewHolder
    private List<T> datas;// 数据源
    private CCRecycleViewListener.OnItemClickListener onItemClickListener;
    private CCRecycleViewListener.OnItemLongClickListener onItemLongClickListener;

    public CCRecyclerViewAdapt(Context context, List<T> datas){
        this(context);
    }
    public CCRecyclerViewAdapt(Context context){
        mContext=context;
        datas=new ArrayList<>();
        mLayoutInflater= LayoutInflater.from(context);
    }

    @Override
    public CCViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mLayoutInflater.inflate(getItemLayoutId(),null);
        holder=new CCViewHolder(mContext,view, onItemClickListener, onItemLongClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(CCViewHolder holder, int position) {
        convert(holder,datas.get(position));
    }

    /**
     * 滑动时，重复执行函数
     * @param holder
     * @param bean
     */
    protected abstract void convert(CCViewHolder holder, T bean);

    /**
     * 让子类返回要显示的layoutId
     * @return
     */
    public  abstract  int getItemLayoutId() ;

    public void setOnItemClickListener(CCRecycleViewListener.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(CCRecycleViewListener.OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
    @Override
    public int getItemCount() {
        if (datas.size() > 0) {
            return datas.size() ;
        }
        return 0;
    }

    /**
     * 加载更多的数据
     * @param data
     */
    public void loadMoreData(List<T> data) {
        int start = this.datas.size() ;
        for (int i = 0; i < data.size(); i++) {
            this.datas.add(data.get(i));
            notifyItemInserted(start);
        }
        CCLog.i("loadMoreData:" + this.datas.size());
    }

    /**
     * 刷新数据
     * @param data
     */
    public void refreshData(List<T> data) {
        this.datas.clear();
        notifyDataSetChanged();
        for (int i = (data).size()-1; i >= 0; i--) {
            this.datas.add(0, data.get(i));
            notifyItemInserted(0);
        }
    }
}
