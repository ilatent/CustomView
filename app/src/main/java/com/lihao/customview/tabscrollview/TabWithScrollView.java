package com.lihao.customview.tabscrollview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import java.util.List;

/**
 * Created by Hao on 2019/7/21.
 * Describe ScrollView和TabLayout的联动
 */
public class TabWithScrollView extends ScrollView {

    private static final String TAG = "TabWithScrollView";

    /**
     * 模块View的集合
     */
    private List<View> mViewList;

    /**
     * 是否是ScrollView引起的滑动，true-是，false-TabLayout引起的滑动
     */
    private boolean isManualScroll;

    /**
     * 记录上一次点击的position，防止多次点击
     */
    private int oldPosition = 0;

    /**
     * 需要联动的tabLayout
     */
    private TabLayout mTabLayout;

    /**
     * ScrollView的滑动回调
     */
    private OnScrollCallback onScrollCallback;

    /**
     * 距离顶部的偏移量，默认为10px;
     */
    private int mTranslationY = 10;

    private boolean mSelectTabFlag = false;


    public TabWithScrollView(Context context) {
        super(context);
    }

    public TabWithScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabWithScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            Log.i(TAG, "onTouch: ACTION_DOWN");
            isManualScroll = true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollCallback != null) {
            onScrollCallback.onScrollCallback(l, t, oldl, oldt);
        }
        if (isManualScroll) {
            if (mViewList == null) {
                return;
            }
            for (int i = mViewList.size() - 1; i >= 0; i--) {
                if (t > getViewTop(i)) {
                    setSelectedTab(i);
                    break;
                }
            }
        }
    }

    /**
     * 获取View距离顶部的高度(mTranslationY是距离顶部的偏移量)
     *
     * @param position
     * @return
     */
    private int getViewTop(int position) {
        if (position >= mViewList.size() + 1) {
            throw new IndexOutOfBoundsException("TabLayout的tab数量和视图View的数量不一致");
        }
        return mViewList.get(position).getTop() - mTranslationY;
    }

    /**
     * 设置选中的tab标签
     *
     * @param position
     */
    private void setSelectedTab(int position) {
        if (mTabLayout != null && position != oldPosition) {
            Log.i(TAG, "setSelectedTab: " + position);
            oldPosition = position;
            TabLayout.Tab newTab = mTabLayout.getTabAt(position);
            if (newTab != null) {
                mSelectTabFlag = true;
                newTab.select();
            }
        }
    }

    /**
     * 设置绑定的tabLayout,并给tabLayout添加OnTabSelectedListener监听
     *
     * @param tabLayout
     */
    public void setupWithTabLayout(TabLayout tabLayout) {
        if (tabLayout != null) {
            mTabLayout = tabLayout;
            mTabLayout.addOnTabSelectedListener(mTabSelectedListener);
        }
    }

    public void setAnchorList(List<View> anchorList) {
        this.mViewList = anchorList;
    }

    public void setOnScrollCallback(OnScrollCallback onScrollCallback) {
        this.onScrollCallback = onScrollCallback;
    }

    public void setTranslationY(int translationY) {
        this.mTranslationY = translationY;
    }

    TabLayout.OnTabSelectedListener mTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            oldPosition = tab.getPosition();
            isManualScroll = false;
            mSelectTabFlag = !mSelectTabFlag;
            if (mViewList == null) {
                return;
            }
            if (mSelectTabFlag) { // 通过点击Tab触发
                // smoothScrollTo可以平滑的滑动到指定位置，并打断惯性滑动
                smoothScrollTo(0, getViewTop(oldPosition));
            } else { //通过滑动时切换Tab触发
                isManualScroll = true;
            }
            mSelectTabFlag = false;
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            Log.i(TAG, "onTabUnselected: " + tab.getPosition());
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            Log.i(TAG, "onTabReselected: " + tab.getPosition());
        }
    };

    /**
     * ScrollView的滚动回调
     */
    public interface OnScrollCallback {
        void onScrollCallback(int l, int t, int oldl, int oldt);
    }

}
