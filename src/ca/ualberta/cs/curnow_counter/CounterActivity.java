package ca.ualberta.cs.curnow_counter;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class CounterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counter);
		
		Intent intent = getIntent();
		String counterString = intent.getStringExtra(CurnowCounterMainActivity.EXTRA_COUNTER);
		
		CounterModel counter = deserialization(counterString);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.counter, menu);
		return true;
	}
	
	
	public void goback(View view) {
		
		//TODO add saving
		
		finish();
		
	}
	
	private CounterModel deserialization(String text) {
		
		Gson gson = new Gson();
		CounterModel new_model = gson.fromJson(text, CounterModel.class);
		return new_model;
	}

}
