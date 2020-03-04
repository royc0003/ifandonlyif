package com.example.a24feb1630;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BackEndController {
    @GET("getAllDeals")
    Call<ArrayList<Deal>> getAllDeals();

    @GET("getFoodDeals") //Note this is to specify the http to retrieve from django or get req
    Call<ArrayList<Deal>> getFoodDeals();

    @GET("getEntertainmentDeals")
    Call<ArrayList<Deal>> getEntertainmentDeals();

    @GET("getRetailDeals")
    Call<ArrayList<Deal>> getRetailDeals();

    @GET("getOthersDeals")
    Call<ArrayList<Deal>> getOthersDeals();
}
