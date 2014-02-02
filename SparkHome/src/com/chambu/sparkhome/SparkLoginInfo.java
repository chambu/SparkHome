package com.chambu.sparkhome;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;


/**
 * Created by Gaurav Gupta on 2/2/14.
 * Edited by Gowtham on 2/2/14
 */
public interface SparkLoginInfo {
    @GET("/v1/devices/{DeviceID}/{SensorType}")
    void getSensorData(@Path("DeviceID") String DeviceID,@Path("SensorType") String SensorType, @Query("access_token") String token, Callback<SparkCoreData> sensorData);
}