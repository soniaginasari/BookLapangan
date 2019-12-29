package com.example.booklapangan;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.booklapangan.apihelper.BaseApiService;
import com.example.booklapangan.apihelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class TambahLapanganActivity extends AppCompatActivity {

    EditText txt_nama_lapangan, txt_alamat, txt_no_hp,txt_id_kategori;
    String nama_lapangan, alamat, no_hp,id_kategori, kat;
    String id_user;
    Spinner spinner;

    Button simpan;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    Preferences sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_lapangan);

        setTitle("Tambah Lapangan");

        txt_nama_lapangan = findViewById(R.id.Nama);
        txt_alamat = findViewById(R.id.Alamat);
        txt_no_hp = findViewById(R.id.Telp);
//        txt_id_kategori = findViewById(R.id.Kategori);
        spinner = (Spinner) findViewById(R.id.Kategori);

        simpan = findViewById(R.id.Simpan_btn);

        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new Preferences(this);
        id_user = sharedPrefManager.getSPId();




        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama_lapangan      = txt_nama_lapangan.getText().toString();
                alamat    = txt_alamat.getText().toString();
                no_hp  = txt_no_hp.getText().toString();
//                id_kategori    = txt_id_kategori.getText().toString();
                kat = String.valueOf(spinner.getSelectedItem());
                if ("sepak bola".equalsIgnoreCase(kat)){
                    id_kategori ="61";
                }else if("renang".equalsIgnoreCase(kat)) {
                    id_kategori = "62";
                }else if("basket".equalsIgnoreCase(kat)) {
                    id_kategori = "63";
                }else if("bulutangkis".equalsIgnoreCase(kat)) {
                    id_kategori = "64";
                }else if("futsal".equalsIgnoreCase(kat)) {
                    id_kategori = "65";
                }else {
                    id_kategori="66";
                }
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestTambah();
            }
        });
    }

    private void requestTambah() {
        mApiService.SetLapangan(nama_lapangan,alamat,no_hp,id_kategori,id_user)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("status").equals("true")) {

                                    Toast.makeText(mContext, "LAPANGAN BERHASIL DITAMBAHKAN", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(mContext, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(mContext, "LAPANGAN GAGAL DITAMBAHKAN", Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
    }
}
