package com.lihao.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lihao.customview.tabscrollview.DetailActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnTabWithScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTabWithScrollView = findViewById(R.id.btn_tab_with_scrollView);
        btnTabWithScrollView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tab_with_scrollView:
                Intent intent = new Intent(this, DetailActivity.class);
                startActivity(intent);
                break;
        }
    }
}
