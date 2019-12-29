package com.example.booklapangan.model;

import com.google.gson.annotations.SerializedName;

public class LapanganItem {
    @SerializedName("id")
    private String id;

    @SerializedName("nama_lapangan")
    private String nama_lapangan;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("no_hp")
    private String no_hp;

    @SerializedName("id_kategori")
    private String id_kategori;

    @SerializedName("foto_lapangan")
    private String foto_lapangan;

    @SerializedName("id_user")
    private String id_user;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setNama_lapangan(String nama_lapangan){
        this.nama_lapangan = nama_lapangan;
    }

    public String getNama_lapangan(){
        return nama_lapangan;
    }

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }

    public String getAlamat(){
        return alamat;
    }

    public void setNo_hp(String no_hp){
        this.no_hp = no_hp;
    }

    public String getNo_hp(){
        return no_hp;
    }

    public void setId_kategori(String id_kategori){
        this.id_kategori = id_kategori;
    }

    public String getId_kategori(){
        return id_kategori;
    }

    public void setFoto_lapangan(String foto_lapangan){
        this.foto_lapangan = foto_lapangan;
    }

    public String getFoto_lapangan(){
        return foto_lapangan;
    }

    public void setId_user(String id_user){
        this.id_user = id_user;
    }

    public String getId_user(){
        return id_user;
    }

    @Override
    public String toString(){
        return
                "LapanganItem{" +
                        "id = '" + id + '\'' +
                        "nama_lapangan = '" + nama_lapangan + '\'' +
                        "alamat = '" + alamat + '\'' +
                        "no_hp = '" + no_hp + '\'' +
                        "id_kategori = '" + id_kategori + '\'' +
                        "foto_lapangan = '" + foto_lapangan + '\'' +
                        "id_user = '" + id_user + '\'' +
                        "}";
    }
}
