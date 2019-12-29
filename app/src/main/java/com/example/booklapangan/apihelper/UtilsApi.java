package com.example.booklapangan.apihelper;

public class UtilsApi {

    public static final String BASE_URL_API = "https://codingprogram.000webhostapp.com/api/";

    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
