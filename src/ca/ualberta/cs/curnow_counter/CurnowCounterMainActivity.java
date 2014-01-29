package ca.ualberta.cs.curnow_counter;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

public class CurnowCounterMainActivity extends Activity {

	public final static String EXTRA_COUNTER = "ca.ualberta.cs.curnow_counter.MESSAGE";
	private static final String FILENAME = "file.sav";
	static CounterController counterController = new CounterController();
	private ListView counterListView; 
	private static CounterListModel counterList = new CounterListModel();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_curnow__counter);
		
		//CounterListModel saves all the counter data
		//final static CounterController counterController = new CounterController();
		
		counterListView = (ListView) findViewById(R.id.counterList);
		 
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
		
		//CounterModel loadedCounter = loadFromFile();
		
		//debug
		//System.out.println(loadedCounter.getName());
		//System.out.println(loadedCounter.getButtonValue());
		//System.out.println(loadedCounter.getTimestamp());
		
		//counterController.addCounter(loadedCounter);
		
	}
	

    @Override
    protected void onStart() {
            
            
            
            // TODO Auto-generated method stub
            super.onStart();
            
            //Adapter code adapted from lonely Twitter
            CounterModel[] counters = loadFromFile();
            
            System.out.println(counterList.getNameList());
            
            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, counterMap.getKeys());
            
            //counterList.setAdapter(adapter);
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
	
	//LoadingFromFile code adapted from lonely Twitter
	private CounterModel[] loadFromFile() {
        ArrayList<CounterModel> counters = new ArrayList<CounterModel>();
        try {
        		 	
                FileInputStream fis = openFileInput(FILENAME);
                BufferedReader in = new BufferedReader(new InputStreamReader(fis));
                String line = in.readLine();  
                
                CounterModel counter = deserialization(line);    
                //line = ("Counter name: " + counter.getName() + " Counter value: " + counter.getButtonValue());
                
                while(line != null) {
                	counters.add(counter);
                	line = in.readLine();
                	
                	//Add the counter to the CounterMap
                	CounterListModel.add(counter);
                	
                }
                
        } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        		return counters.toArray(new CounterModel[counters.size()]);
		}
	
	private ArrayList<String> getCounterNames(CounterModel[] models){
		
		
		 ArrayList<String> nameList = new ArrayList();
		
		for(CounterModel counters : models){
			nameList.add(counters.getName());
		}
		
		
		return nameList;
	}
	
	
}
