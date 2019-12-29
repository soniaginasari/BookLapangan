package com.example.booklapangan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booklapangan.LapanganActivity;
import com.example.booklapangan.R;
import com.example.booklapangan.model.CategoryItem;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{

    List<CategoryItem> categoryItemList;
    Context mContext;

    public CategoryAdapter(Context context, List<CategoryItem> categoryList){
        this.mContext = context;
        categoryItemList = categoryList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_categories, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final CategoryItem categoryitem = categoryItemList.get(position);
        holder.mTextViewCategories.setText(categoryitem.getCategory_name());
        holder.mTextViewId.setText(categoryitem.getId_kategori());
        holder.mCardViewCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LapanganActivity.class);
                intent.putExtra("id_kategori", categoryitem.getId_kategori());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryItemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewCategories;
        public TextView mTextViewId;
        public CardView mCardViewCategories;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewCategories = itemView.findViewById(R.id.tvCategories);
            mTextViewId = itemView.findViewById(R.id.tvId);
            mCardViewCategories = itemView.findViewById(R.id.card_view1);
        }
    }
}
