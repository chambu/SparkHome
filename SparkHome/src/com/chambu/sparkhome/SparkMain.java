package com.chambu.sparkhome;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SparkMain extends Activity {
    TextView tempText, humText;
    
    
    private String TAG = "SparkIO-TestAPP";
    public String core_token = "token here", DeviceID = "device ID Here"; 
    //need to implement grabbing token and Device 
    
    public String SensorType = "humidity";
    
    private Thread timerThread;
    private Handler mHandler = new Handler();
 

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spark_main);

    tempText = (TextView) findViewById(R.id.TempReading);
    humText = (TextView) findViewById(R.id.HumidityReading);
    
    readSensorData("humidity");
    readSensorData("temperature");
    
   // Timer to refresh the data every 10 Seconds 
   timerThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
            	
            
            	try {
            		Thread.sleep(10000);
                    readSensorData("humidity");
                    readSensorData("temperature");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } 
            }

        }
    });
    timerThread.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spark_main, menu);
		return true;
	}
	
	@Override
	public void onPause(){
		super.onPause();
	//	mHandler.removeCallbacks(timerThread);
		Log.d(TAG, "onPause");
	}
	
	protected void onResume() {
	    super.onResume();
	//    mHandler.removeCallbacks(timerThread);
	//	mHandler.postDelayed(timerThread, 10000);
		Log.d(TAG, "onResume");
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