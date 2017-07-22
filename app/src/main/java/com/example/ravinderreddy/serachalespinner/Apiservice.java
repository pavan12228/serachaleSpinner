package com.example.ravinderreddy.serachalespinner;

import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.http.GET;

public interface Apiservice
{

    @GET("/bins/31b8v")
    public void apiDetails(Callback<JsonObject> callback);




}