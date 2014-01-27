package ca.ualberta.cs.curnow_counter;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

public class CurnowCounterMainActivity extends Activity {

	public final static String EXTRA_COUNTER = "ca.ualberta.cs.curnow_counter.MESSAGE";
	private static final String FILENAME = "file.sav";
	private static CounterListModel counterList = new CounterListModel();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_curnow__counter);
		
		//CounterListModel saves all the counter data
		
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.curnow__counter, menu);
		return true;
	}
	
	@Override
	//update the counter list
	protected void onResume() {
		super.onResume();
		
		CounterModel loadedCounter = loadFromFile();
		//counterList.
		
		
	}
	

	//activates when you push the create button
	public void createCounter(View view) {
		
		//Gets the string from CreateButton EditText
		EditText buttonTitle = (EditText) findViewById(R.id.CreateButtonEditText);
		String message = buttonTitle.getText().toString();

		//Creates a new counter (and serializes it) with the message from CreatButton
		//then passes it through a newly created intent
		CounterModel new_counter = new CounterModel(message);		
		Intent intent = new Intent(this, CounterActivity.class);	
		String counterString = serialization(new_counter);
		intent.putExtra(EXTRA_COUNTER, counterString);
		
		
		startActivity(intent);
	}


	public String serialization( CounterModel model) {
		
		Gson gson = new Gson();
		String json = gson.toJson(model);
		return json;
	}
	
	
	
	private CounterModel deserialization(String text) {
		
		Gson gson = new Gson();
		CounterModel new_model = gson.fromJson(text, CounterModel.class);
		return new_model;
	}
	
	private CounterModel loadFromFile() {
        CounterModel loadedCounter = new CounterModel();
        try {
        		 	
                FileInputStream fis = openFileInput(FILENAME);
                BufferedReader in = new BufferedReader(new InputStreamReader(fis));
                String line = in.readLine();          
                loadedCounter = deserialization(line);            
                
        } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        		return loadedCounter;
		}
	
	
}
