package com.app.androidcore.controls.recyclerview.decoration;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemSpacingDecoration extends RecyclerView.ItemDecoration {
    private int mInnerSpacing;
    private int mOuterSpacing;
    private int mOuterSpacingLeft;
    private int mOuterSpacingRight;
    private int mOuterSpacingTop;
    private int mOuterSpacingBottom;
    private boolean mIsDynamicOuterSpacing = false;

    public ItemSpacingDecoration(int innerSpacing, int outerSpacing) {
        mInnerSpacing = innerSpacing;
        mOuterSpacing = outerSpacing;
    }

    public ItemSpacingDecoration(int innerSpacing, int leftOuterSpacing, int rightOuterSpacing, int topOuterSpacing, int bottomOuterSpacing) {
        mIsDynamicOuterSpacing = true;
        mInnerSpacing = innerSpacing;
        mOuterSpacingLeft = leftOuterSpacing;
        mOuterSpacingRight = rightOuterSpacing;
        mOuterSpacingTop = topOuterSpacing;
        mOuterSpacingBottom = bottomOuterSpacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemCount = parent.getAdapter().getItemCount();
        int viewPosition = parent.getChildLayoutPosition(view);

        outRect.top = mInnerSpacing / 2;
        outRect.bottom = mInnerSpacing / 2;
        outRect.left = mInnerSpacing / 2;
        outRect.right = mInnerSpacing / 2;

        if(parent.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
            applyGridItemSpacing(outRect, layoutManager, itemCount, viewPosition);
        } else if(parent.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
            applyLinearItemSpacing(outRect, layoutManager, itemCount, viewPosition);
        }
    }

    private void applyLinearItemSpacing(Rect outRect, LinearLayoutManager layoutManager, int itemCount, int viewPosition) {
        if(layoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
            if(viewPosition == 0) {
                if(mIsDynamicOuterSpacing) {
                    outRect.top = mOuterSpacingTop;
                } else {
                    outRect.top = mOuterSpacing;
                }
            }
            if(viewPosition == itemCount - 1) {
                if(mIsDynamicOuterSpacing) {
                    outRect.bottom = mOuterSpacingBottom;
                } else {
                    outRect.bottom = mOuterSpacing;
                }
            }

            outRect.left = mOuterSpacing;
            outRect.right = mOuterSpacing;
        } else if(layoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {
            if(viewPosition == 0) {
                if(mIsDynamicOuterSpacing) {
                    outRect.left = mOuterSpacingLeft;
                } else {
                    outRect.left = mOuterSpacing;
                }
            }
            if(viewPosition == itemCount - 1) {
                if(mIsDynamicOuterSpacing) {
                    outRect.right = mOuterSpacingRight;
                } else {
                    outRect.right = mOuterSpacing;
                }
            }

            outRect.top = mOuterSpacing;
            outRect.bottom = mOuterSpacing;
        }
    }

    private void applyGridItemSpacing(Rect outRect, GridLayoutManager layoutManager, int itemCount, int viewPosition) {
        int spanCount = layoutManager.getSpanCount();

        boolean isTopView = viewPosition < spanCount;
        if (isTopView) {
            if(mIsDynamicOuterSpacing) {
                outRect.top = mOuterSpacingTop;
            } else {
                outRect.top = mOuterSpacing;
            }
        }

        boolean isLeftView = (viewPosition % spanCount) == 0;
        if (isLeftView) {
            if(mIsDynamicOuterSpacing) {
                outRect.left = mOuterSpacingLeft;
            } else {
                outRect.left = mOuterSpacing;
            }
        }

        boolean isRightView = ((viewPosition + 1) % spanCount) == 0;
        if (isRightView) {
            if(mIsDynamicOuterSpacing) {
                outRect.right = mOuterSpacingRight;
            } else {
                outRect.right = mOuterSpacing;
            }

        }

        boolean isBottomView = (((itemCount - 1) - viewPosition) / spanCount) == 0;
        if (isBottomView) {
            if(mIsDynamicOuterSpacing) {
                outRect.bottom = mOuterSpacingBottom;
            } else {
                outRect.bottom = mOuterSpacing;
            }
        }
    }
}
