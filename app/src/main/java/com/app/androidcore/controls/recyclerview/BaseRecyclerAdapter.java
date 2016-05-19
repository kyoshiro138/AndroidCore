package com.app.androidcore.controls.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseRecyclerAdapter<TViewHolder extends RecyclerView.ViewHolder, TViewData> extends RecyclerView.Adapter<TViewHolder> {
    private static final int TAG_ITEM_POSITION_KEY = 100;

    protected Context mContext;
    protected List<TViewData> mDataSource;
    private OnRecyclerItemClickListener mItemClickListener;

    protected abstract int getRecyclerItemLayoutResource(int viewType);

    protected abstract TViewHolder initViewHolder(View itemView);

    public final List<TViewData> getDataSource() {
        return mDataSource;
    }

    protected BaseRecyclerAdapter(Context context, List<TViewData> dataSource) {
        mContext = context;
        mDataSource = dataSource;
    }

    @Override
    public final TViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(getRecyclerItemLayoutResource(viewType), parent, false);;
        TViewHolder viewHolder = initViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onViewAttachedToWindow(TViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener != null) {
                    int position = (int) v.getTag();
                    mItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public void onViewDetachedFromWindow(TViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.setOnClickListener(null);
    }

    @Override
    public void onBindViewHolder(TViewHolder holder, int position) {
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        if(mDataSource != null) {
            return mDataSource.size();
        }
        return 0;
    }

    public final void setOnItemClickListener(OnRecyclerItemClickListener listener) {
        mItemClickListener = listener;
    }

    public interface OnRecyclerItemClickListener {
        void onItemClick(View view, int position);
    }
}
