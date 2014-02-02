package com.chambu.sparkhome;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import android.util.Log;
import android.view.Menu;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

public class SparkMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spark_main);
		
		TextView tempText = (TextView) findViewById(R.id.TempReading);
	Log.i("Test :", "Started 1");	
	RestAdapter restAdapter = new RestAdapter.Builder()
		.setEndpoint("https://api.spark.io")
        .build();
	Log.i("Test :", "Started 2");
	SparkService apiManager = restAdapter.create(SparkService.class);
	Log.i("Test :", "Started 3");
	TempData tempData = apiManager.getTemp("7c902d9f9a50679878a4dbfcadc1cf455b48cf46");
	Log.i("Test :", "Started 4");
	tempText.setText(tempData.result);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spark_main, menu);
		return true;
	}
	
	public interface SparkService {
		  @GET("/v1/devices/50ff6f065067545626270287/temperature")
		  TempData getTemp(@Query("access_token") String token);
		}

}
