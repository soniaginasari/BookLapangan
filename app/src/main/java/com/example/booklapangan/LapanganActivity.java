package com.example.booklapangan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.booklapangan.adapter.CategoryAdapter;
import com.example.booklapangan.adapter.LapanganAdapter;
import com.example.booklapangan.apihelper.BaseApiService;
import com.example.booklapangan.apihelper.UtilsApi;
import com.example.booklapangan.model.CategoryItem;
import com.example.booklapangan.model.LapanganItem;
import com.example.booklapangan.model.ResponseCategory;
import com.example.booklapangan.model.ResponseLapangan;

import java.util.ArrayList;
import java.util.List;

public class LapanganActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<LapanganItem> lapanganList = new ArrayList<>();
    LapanganAdapter lapanganAdapter;
    BaseApiService mApiService;
    Context mContext;
    String id_kategori;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.tambah_lapangan_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.tambah_lapangan:
                Intent myintent = new Intent(this, TambahLapanganActivity.class);
                startActivity(myintent);

                return true;

        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapangan);

//        View myView = inflater.inflate(R.layout.fragment_home, container, false);
        id_kategori = getIntent().getStringExtra("id_kategori");
        recyclerView = findViewById(R.id.recyclerView);


        mContext = this;

        mApiService = UtilsApi.getAPIService();

        lapanganAdapter = new LapanganAdapter(mContext, lapanganList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);


        getResult();

    }

    private void getResult() {
        mApiService.getLapangan(id_kategori).enqueue(new Callback<ResponseLapangan>() {
            @Override
            public void onResponse(Call<ResponseLapangan> call, Response<ResponseLapangan> response) {
                if (response.isSuccessful()){

                    final List<LapanganItem> lapangan = response.body().getLapangan();

                    recyclerView.setAdapter(new LapanganAdapter(mContext, lapangan));
                    lapanganAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLapangan> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        menu.add(0, v.getId(), 0, "Edit");
        menu.add(0, v.getId(), 0, "Delete");
    }

    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Edit")Toast.makeText(getApplicationContext(), "Edit Clicked", Toast.LENGTH_LONG).show();
        if(item.getTitle()=="Delete")Toast.makeText(getApplicationContext(), "Delete Clicked", Toast.LENGTH_LONG).show();
        return true;
    }
}
