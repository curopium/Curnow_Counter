package ca.ualberta.cs.curnow_counter;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CounterActivity extends Activity {

	static CounterModel counter = new CounterModel();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counter);
		
		//Gets the passed counter through the intent and deserializes it
		Intent intent = getIntent();
		String counterString = intent.getStringExtra(CurnowCounterMainActivity.EXTRA_COUNTER);
		counter = deserialization(counterString);
		
		//sets button title from counter and sets it in button
		Button buttonVars = (Button)findViewById(R.id.buttonCounter);
		buttonVars.setText(counter.getName());
		
		updateCounter(counter);
		
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
	
	//Gets the button value from counter and moves it to view
	private void updateCounter (CounterModel counter) {
		TextView buttonValue = (TextView)findViewById(R.id.buttonValue);
		int counterButtonValue = counter.getButtonValue();
		buttonValue.setText(String.valueOf(counterButtonValue));
		
	}
	
	//takes a Json string and turns it into a counter
	private CounterModel deserialization(String text) {
		Gson gson = new Gson();
		CounterModel new_model = gson.fromJson(text, CounterModel.class);
		return new_model;
	}
	
	public void incCounter(View view){
		counter.setButtonValue(counter.getButtonValue() + 1);
		updateCounter(counter);
		//TODO save
	}
	

}
