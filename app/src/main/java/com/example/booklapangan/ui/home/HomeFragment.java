package com.example.booklapangan.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booklapangan.adapter.CategoryAdapter;
import com.example.booklapangan.R;
import com.example.booklapangan.apihelper.BaseApiService;
import com.example.booklapangan.apihelper.UtilsApi;
import com.example.booklapangan.model.CategoryItem;
import com.example.booklapangan.model.ResponseCategory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    List<CategoryItem> categoryList = new ArrayList<>();
    CategoryAdapter categoryAdapter;
    BaseApiService mApiService;
    Context mContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = myView.findViewById(R.id.recyclerView);

        mContext = myView.getContext();

        mApiService = UtilsApi.getAPIService();

        categoryAdapter = new CategoryAdapter(mContext, categoryList);
        GridLayoutManager mLayoutManager = new GridLayoutManager(mContext, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        getResult();

        return myView;
    }

    private void getResult() {
        mApiService.getCategory().enqueue(new Callback<ResponseCategory>() {
            @Override
            public void onResponse(Call<ResponseCategory> call, Response<ResponseCategory> response) {
                if (response.isSuccessful()){

                    final List<CategoryItem> cat = response.body().getCategory();

                    recyclerView.setAdapter(new CategoryAdapter(getContext(), cat));
                    categoryAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCategory> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}