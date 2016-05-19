package com.app.androidcore.controls.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.app.androidcore.controls.recyclerview.decoration.ItemSpacingDecoration;

public abstract class BaseRecyclerView extends RecyclerView {
    private static final int DEFAULT_GRID_SPAN_COUNT = 2;

    private int mGridSpanCount;

    protected int getDefaultGridSpanCount() {
        return DEFAULT_GRID_SPAN_COUNT;
    }

    protected BaseRecyclerView(Context context) {
        super(context);
        initRecyclerView(context);
    }

    protected BaseRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initRecyclerView(context);
    }

    protected BaseRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initRecyclerView(context);
    }

    protected void initRecyclerView(Context context) {
        mGridSpanCount = getDefaultGridSpanCount();
        setHasFixedSize(true);
    }

    public final void initLayoutType(LayoutType viewType) {
        switch (viewType) {
            case LINEAR_VERTICAL:
                LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                setLayoutManager(linearLayoutManagerVertical);
                break;
            case LINEAR_HORIZONTAL:
                LinearLayoutManager linearLayoutManagerHorizontal = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                setLayoutManager(linearLayoutManagerHorizontal);
                break;
            case GRID:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), mGridSpanCount);
                setLayoutManager(gridLayoutManager);
                break;
        }
    }

    public final void setGridSpanCount(int spanCount) {
        mGridSpanCount = spanCount;
    }

    public final void setItemSpacing(int innerSpacing, int outerSpacing) {
        ItemSpacingDecoration decoration = new ItemSpacingDecoration(innerSpacing, outerSpacing);
        addItemDecoration(decoration);
    }

    public final void setItemSpacing(int innerSpacing, int leftOuterSpacing, int rightOuterSpacing, int topOuterSpacing, int bottomOuterSpacing) {
        ItemSpacingDecoration decoration = new ItemSpacingDecoration(innerSpacing, leftOuterSpacing, rightOuterSpacing, topOuterSpacing, bottomOuterSpacing);
        addItemDecoration(decoration);
    }
}
