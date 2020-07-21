package com.lihao.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lihao.customview.hencoder.CustomViewActivity;
import com.lihao.customview.tabscrollview.DetailActivity;
import com.lihao.customview.transitionanim.ATransitionAnimActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnTabWithScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTabWithScrollView = findViewById(R.id.btn_tab_with_scrollView);
        btnTabWithScrollView.setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.custom1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_tab_with_scrollView:
                intent = new Intent(this, DetailActivity.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                intent = new Intent(this, ATransitionAnimActivity.class);
                startActivity(intent);
                break;
            case R.id.custom1:
                startActivity(new Intent(this, CustomViewActivity.class));
                break;
        }
    }
}
