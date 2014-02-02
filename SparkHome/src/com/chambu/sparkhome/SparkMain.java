package com.chambu.sparkhome;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SparkMain extends Activity {
    TextView tempText, humText;
    
    private String TAG = "SparkMain";
    public String core_token = "7c902d9f9a50679878a4dbfcadc1cf455b48cf46", DeviceID = "50ff6f065067545626270287"; 
    //need to get token from login
    
    public String SensorType = "humidity";

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spark_main);

    tempText = (TextView) findViewById(R.id.TempReading);
    humText = (TextView) findViewById(R.id.HumidityReading);
    
    readSensorData("humidity");
    readSensorData("temperature");
    
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spark_main, menu);
		return true;
	}
	
	public void readSensorData(final String SensorType){

		RestAdapter restAdapter = new RestAdapter.Builder()
		.setEndpoint("https://api.spark.io")
        .build();
		
		SparkService apiManager = restAdapter.create(SparkService.class);

		apiManager.getSensorData(DeviceID, SensorType, core_token, new Callback<SparkCoreData>() {

			@Override
	        public void success(SparkCoreData sensorData, Response response) {
	            Log.d(TAG, "SensorData for " + SensorType +" is " + sensorData.result);
	            if(SensorType=="temperature"){
	            tempText.setText(sensorData.result);
	            }else{
	            humText.setText(sensorData.result);
	            }
	        }
	        @Override
	        public void failure(RetrofitError retrofitError) {

	        }
	    });

	}


}