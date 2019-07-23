package com.lihao.customview.tabscrollview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.TabLayout;
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
    private List<View> mAnchorList;

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


    public TabWithScrollView(Context context) {
        super(context);
        setOnTouchListener();
    }

    public TabWithScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener();
    }

    public TabWithScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setOnTouchListener() {
        super.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    isManualScroll = true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollCallback != null) {
            onScrollCallback.onScrollCallback(l, t, oldl, oldt);
        }
        if (isManualScroll) {
            if (mAnchorList == null) {
                return;
            }
            for (int i = mAnchorList.size() - 1; i >= 0; i--) {
                if (t > getScrollDistance(i)) {
                    setSelectedTab(i);
                    break;
                }
            }
        }
    }

    /**
     * 设置选中的tab标签
     *
     * @param position
     */
    private void setSelectedTab(int position) {
        if (mTabLayout != null && position != oldPosition) {
            // 该方法不会走tabLayout的onTabSelected监听
            mTabLayout.setScrollPosition(position, 0, true);
        }
        oldPosition = position;
    }

    /**
     * 设置绑定的tabLayout,并给tabLayout添加OnTabSelectedListener监听
     *
     * @param tabLayout
     */
    public void setupWithTabLayout(TabLayout tabLayout) {
        if (mTabLayout != null) {
            mTabLayout.removeOnTabSelectedListener(mTabSelectedListener);
        }
        if (tabLayout != null) {
            mTabLayout = tabLayout;
            mTabLayout.addOnTabSelectedListener(mTabSelectedListener);
        }
    }

    public void setAnchorList(List<View> anchorList) {
        this.mAnchorList = anchorList;
    }

    public void setOnScrollCallback(OnScrollCallback onScrollCallback) {
        this.onScrollCallback = onScrollCallback;
    }

    public void setTranslationY(int translationY) {
        this.mTranslationY = translationY;
    }

    /**
     * 获取模块的锚点
     *
     * @param position
     * @return
     */
    private int getScrollDistance(int position) {
        if (position >= mAnchorList.size() + 1) {
            throw new IndexOutOfBoundsException("TabLayout的tab数量和视图View的数量不一致");
        }
        return mAnchorList.get(position).getTop() - mTranslationY;
    }

    TabLayout.OnTabSelectedListener mTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            isManualScroll = false;
            if (mAnchorList == null) {
                Log.i(TAG, "onTabSelected: 未设置View集合");
                return;
            }
            // smoothScrollTo可以平滑的滑动到指定位置，并打断惯性滑动
            smoothScrollTo(0, getScrollDistance(tab.getPosition()));
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    /**
     * ScrollView的滚动回调
     */
    public interface OnScrollCallback {
        void onScrollCallback(int l, int t, int oldl, int oldt);
    }

}
