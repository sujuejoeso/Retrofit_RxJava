package com.joeso.retrofitplusrxjava;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface WebApi{
    @GET("/api/v1/employees")
    Observable<ResponseData> getData();
}
