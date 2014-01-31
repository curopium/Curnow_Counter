package ca.ualberta.cs.curnow_counter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CounterActivity extends Activity {
	static private CounterModel counter = new CounterModel();
	private Context context = CounterActivity.this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counter);
		//Gets the passed counter through the intent and deserializes it
		Intent intent = getIntent();
		String counterString = intent.getStringExtra(CurnowCounterMainActivity.EXTRA_COUNTER);
		counter = counter.deserialization(counterString);
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
	
	//Gets the button value from counter and moves it to view, and updates name
	private void updateCounter (CounterModel counter) {
		CurnowCounterMainActivity.counterList.addCounter(counter);
		TextView buttonValue = (TextView)findViewById(R.id.buttonValue);
		int counterButtonValue = counter.getButtonValue();
		buttonValue.setText(String.valueOf(counterButtonValue));
		TextView buttonTitle = (TextView)findViewById(R.id.buttonCounter);
		buttonTitle.setText(counter.getName());
	}
	
	//called by a button in the activity_couner.xml
	public void goBack(View view) {
		finish();
	}
	
	//called by a button in the activity_couner.xml
	public void incCounter(View view){
		counter.setButtonValue(counter.getButtonValue() + 1);
		updateCounter(counter);
		
		
		
		String counter_serial = counter.serialization();
		counter.saveToFile(counter_serial, context);
	}
	
	//called by a button in the activity_couner.xml
	public void editCounter(View view){
		
		//Gets the string from CreateButton EditText
		EditText buttonTitle = (EditText) findViewById(R.id.editText);
		String message = buttonTitle.getText().toString();
		//only change name if string length is > 0
		if(message.length() > 0) {
			//Creates a new counter (and serializes it) with the message from CreatButton
			//then passes it through a newly created intent
			CounterModel newCounter = new CounterModel(counter.getButtonValue(), message);
			CurnowCounterMainActivity.counterList.delete(counter.getName());
			counter = newCounter;
			updateCounter(counter);
			String counter_serial = counter.serialization();
			counter.saveToFile(counter_serial, context);
		}
	}
	
	//called by a button in the activity_couner.xml
	public void resetCounter(View view){
		counter.setButtonValue(0);
		updateCounter(counter);
		String counterSerial = counter.serialization();
		counter.saveToFile(counterSerial, context);
	}
	
	//called by a button in the activity_couner.xml
	public void deleteCounter(View view){	
		CurnowCounterMainActivity.counterList.delete(counter.getName());
		counter.setName("DeleteMe");
		String counterSerial = counter.serialization();
		counter.saveToFile(counterSerial, context);
		finish();
	}
}
