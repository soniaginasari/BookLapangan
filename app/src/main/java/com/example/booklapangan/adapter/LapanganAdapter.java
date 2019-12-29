package com.example.booklapangan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.booklapangan.DetailLapanganActivity;
import com.example.booklapangan.R;
import com.example.booklapangan.model.CategoryItem;
import com.example.booklapangan.model.LapanganItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class LapanganAdapter extends RecyclerView.Adapter<LapanganAdapter.MyViewHolder>{

    List<LapanganItem> lapanganItemList;
    Context mContext;

    public LapanganAdapter(Context context, List<LapanganItem> lapanganList){
        this.mContext = context;
        lapanganItemList = lapanganList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_lapangan, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final LapanganItem lapanganitem = lapanganItemList.get(position);
        holder.mTextViewNamaLapangan.setText(lapanganitem.getNama_lapangan());
        holder.mTextViewAlamat.setText(lapanganitem.getAlamat());
        holder.mTextViewNo_hp.setText(lapanganitem.getNo_hp());
        Picasso.get().load(lapanganitem.getFoto_lapangan()).into(holder.mImageViewLapangan);
        holder.mCardViewLapangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, DetailLapanganActivity.class);
                intent.putExtra("image", lapanganitem.getFoto_lapangan());
                intent.putExtra("nama", lapanganitem.getNama_lapangan());
                intent.putExtra("alamat",lapanganitem.getAlamat());
                intent.putExtra("no_hp",lapanganitem.getNo_hp());
                intent.putExtra("id_user",lapanganitem.getId_user());
                intent.putExtra("id",lapanganitem.getId());
                mContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return lapanganItemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewNamaLapangan;
        public TextView mTextViewAlamat;
        public TextView mTextViewNo_hp;
        public ImageView mImageViewLapangan;
        public CardView mCardViewLapangan;

        public MyViewHolder(View itemView) {
            super(itemView);

            mTextViewNamaLapangan = itemView.findViewById(R.id.tvNamaLapangan);
            mTextViewAlamat = itemView.findViewById(R.id.tvAlamat);
            mTextViewNo_hp = itemView.findViewById(R.id.tvNo_hp);
            mImageViewLapangan = itemView.findViewById(R.id.ivFoto);
            mCardViewLapangan=itemView.findViewById(R.id.card_view);

        }
    }
}
