package ca.ualberta.cs.curnow_counter;

import ca.ualberta.cs.lonelytwitter.Gson;
import ca.ualberta.cs.lonelytwitter.NormalTweetModel;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import com.google.gson.Gson;

public class CurnowCounterMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_curnow__counter);
		
		final CounterListModel counterList = new CounterListModel();
		
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.curnow__counter, menu);
		return true;
	}
	

	public void createCounter(View view) {
		CounterModel new_counter = new CounterModel();
		Intent intent = new Intent(this, CounterActivity.class);
	
		//intent.putExtra("Todo, new_counter);
		
		startActivity(intent);
	}


	private String serialization( CounterModel model) {
		
		Gson gson = new Gson();
		String json = gson.toJson(model);
		return json;
	}
	
	
	
	private CounterModel deserialization(String text) {
		
		Gson gson = new Gson();
		CounterModel new_model = gson.fromJson(text, CounterModel.class);
		return new_model;
	}
	
	
}
