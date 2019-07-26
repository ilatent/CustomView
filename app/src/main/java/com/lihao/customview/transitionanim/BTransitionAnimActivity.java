package com.lihao.customview.transitionanim;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.Window;

import com.lihao.customview.R;

/**
 * Created by Hao on 2019/7/23.
 * Describe 转场动画B
 */
public class BTransitionAnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 必须在setContentView之前调用
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        }
        setContentView(R.layout.activity_transition_anim_b);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setExitTransition(new Slide());
//            getWindow().setEnterTransition(new Slide());
//        }
    }
}
