package com.lihao.customview.tabscrollview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lihao.customview.R;
import com.lihao.customview.util.ScreenUtils;
import com.lihao.customview.util.SizeUtils;

import java.util.List;

/**
 * Created by Hao on 2019/7/23.
 * Describe
 */
public class DetailRecommendAdapter extends RecyclerView.Adapter<DetailRecommendAdapter.ViewHolder> {

    private List<RecommendBean> mList;
    private Context mContext;

    public DetailRecommendAdapter(Context context, List<RecommendBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_recommend, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        ViewGroup.LayoutParams layoutParams = holder.ivCommodityImg.getLayoutParams();
        layoutParams.width = (ScreenUtils.getScreenWidth() - SizeUtils.dp2px(8)) / 2;
        layoutParams.height = layoutParams.width;
        holder.ivCommodityImg.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCommodityImg;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCommodityImg = itemView.findViewById(R.id.iv_commodity_img);
        }
    }
}
