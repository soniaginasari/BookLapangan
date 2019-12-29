package com.example.booklapangan;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booklapangan.apihelper.BaseApiService;
import com.example.booklapangan.apihelper.UtilsApi;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DetailLapanganActivity extends AppCompatActivity {

    ImageView timg;
    TextView tnama, tno_hp, talamat;
    String pimgg;
    String pnamaa, pid, pno_hp, palamatt;
    String id_user, pid_user;
    Button edit, hapus, sewa;
    Preferences sharedPrefManager;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lapangan);

        timg = (ImageView) findViewById(R.id.imgw);
        tnama = (TextView) findViewById(R.id.namaw);
        tno_hp = (TextView) findViewById(R.id.telpw);
        talamat = (TextView) findViewById(R.id.alamatw);
        edit = findViewById(R.id.edit_btn);
        hapus = findViewById(R.id.hapus_btn);
        sewa = findViewById(R.id.sewa_btn);

        pimgg = getIntent().getStringExtra("image");
        pnamaa = getIntent().getStringExtra("nama");
        pno_hp = getIntent().getStringExtra("no_hp");
        palamatt = getIntent().getStringExtra("alamat");
        pid = getIntent().getStringExtra("id");
        pid_user = getIntent().getStringExtra("id_user");

        Picasso.get().load(pimgg).into(timg);
        tnama.setText(pnamaa);
        tno_hp.setText(pno_hp);
        talamat.setText(palamatt);
        sharedPrefManager = new Preferences(this);
        id_user = sharedPrefManager.getSPId();
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        sewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailLapanganActivity.this, SewaActivity.class);
                intent.putExtra("nama", pnamaa);
                intent.putExtra("alamat", palamatt);
                intent.putExtra("no_hp", pno_hp);
                intent.putExtra("id", pid);
                DetailLapanganActivity.this.startActivity(intent);

            }
        });

        if (id_user.equalsIgnoreCase(pid_user)) {
            edit.setVisibility(View.VISIBLE);
            hapus.setVisibility(View.VISIBLE);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DetailLapanganActivity.this, EditLapanganActivity.class);
                    intent.putExtra("nama", pnamaa);
                    intent.putExtra("alamat", palamatt);
                    intent.putExtra("no_hp", pno_hp);
                    intent.putExtra("id", pid);
                    DetailLapanganActivity.this.startActivity(intent);
                }
            });

            hapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog();
                }
            });

            //Digunakan untuk menyembunyikan TextView dan ImageView
        } else {
            edit.setVisibility(View.INVISIBLE);
            hapus.setVisibility(View.INVISIBLE);

            //Digunakan untuk menampilkan lagi  TextView dan ImageView
        }
    }
    private void requestHapus() {
        mApiService.HapusLapangan(pid)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("status").equals("true")) {

                                    Toast.makeText(mContext, "LAPANGAN BERHASIL DIHAPUS", Toast.LENGTH_SHORT).show();

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
                            Toast.makeText(mContext, "LAPANGAN GAGAL DIHAPUS", Toast.LENGTH_SHORT).show();
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

    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title dialog
        alertDialogBuilder.setTitle("Apakah Anda yakin ingin menghapus lapangan?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Klik Ya untuk menghapus lapangan!")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                        requestHapus();
                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }
}
