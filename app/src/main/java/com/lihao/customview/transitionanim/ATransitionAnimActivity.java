package com.lihao.customview.transitionanim;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.lihao.customview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hao on 2019/7/23.
 * Describe 转场动画A
 */
public class ATransitionAnimActivity extends AppCompatActivity {

    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.btn_to_bActivity)
    Button btnToBActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_anim_a);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_to_bActivity)
    public void onViewClicked() {
        Intent intent = new Intent(this, BTransitionAnimActivity.class);
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                ivImage, getString(R.string.transition_name)).toBundle();
        ActivityCompat.startActivity(this, intent, bundle);
    }
}
