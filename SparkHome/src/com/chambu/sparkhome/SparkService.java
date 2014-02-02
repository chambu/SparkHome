package com.chambu.sparkhome;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;


/**
 * Created by Gaurav Gupta on 2/2/14.
 * Edited by Gowtham on 2/2/14
 */
public interface SparkService {
    @GET("/v1/devices/50ff6f065067545626270287/{SensorType}")
    void getSensorData(@Path("SensorType") String SensorType, @Query("access_token") String token, Callback<SparkCoreData> sensorData);
}