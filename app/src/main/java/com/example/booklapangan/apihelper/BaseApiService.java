package com.example.booklapangan.apihelper;

import com.example.booklapangan.model.ResponseCategory;
import com.example.booklapangan.model.ResponseLapangan;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface BaseApiService {

    @GET("lapangan")
    Call<ResponseLapangan> getLapangan(@Query("id_kategori") String id_kategori);

    @FormUrlEncoded
    @POST("tambahlapangan")
    Call<ResponseBody> SetLapangan( @Field("nama_lapangan") String nama_lapangan,
                                    @Field("alamat") String alamat,
                                    @Field("no_hp") String no_hp,
                                    @Field("id_kategori") String id_kategori,
                                    @Field("id_user") String id_user);

    @FormUrlEncoded
    @POST("editlapangan")
    Call<ResponseBody> EditLapangan(@Field("id") String id,
                                    @Field("nama_lapangan") String nama_lapangan,
                                    @Field("alamat") String alamat,
                                    @Field("no_hp") String no_hp,
                                    @Field("id_kategori") String id_kategori);

    @FormUrlEncoded
    @POST("hapuslapangan")
    Call<ResponseBody> HapusLapangan(@Field("id") String id);

    @GET("category")
    Call<ResponseCategory> getCategory();

    @POST("details")
    Call<ResponseBody> authRequest(@Header("Authorization") String Authorization);

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerRequest(@Field("name") String name,
                                       @Field("email") String email,
                                       @Field("password") String password,
                                       @Field("c_password") String c_password);

    @FormUrlEncoded
    @POST("edit")
    Call<ResponseBody> editRequest( @Field("id") String id,
                                    @Field("name") String name);

    @FormUrlEncoded
    @POST("editpass")
    Call<ResponseBody> chapassRequest(  @Field("id") String id,
                                        @Field("password") String password,
                                        @Field("passwordbaru") String passwordbaru,
                                        @Field("c_passwordbaru") String c_passwordbaru);

    @FormUrlEncoded
    @POST("daftargoogle")
    Call<ResponseBody> googleRequest(   @Field("name") String name,
                                        @Field("email") String email,
                                        @Field("password") String password);

}
