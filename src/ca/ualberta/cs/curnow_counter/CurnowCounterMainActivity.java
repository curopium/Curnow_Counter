package ca.ualberta.cs.curnow_counter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class CurnowCounterMainActivity extends Activity {

	public final static String EXTRA_COUNTER = "ca.ualberta.cs.curnow_counter.MESSAGE";
	private static final String FILENAME2 = "file2.sav";
	//private static CounterController counterController = new CounterController();
	public ListView counterListView; 
    
	//used to call CounterModel functions
    private CounterModel emptyCounter = new CounterModel();
	private Context context = CurnowCounterMainActivity.this;
	static CounterListModel counterList = new CounterListModel();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_curnow__counter);

		counterListView = (ListView) findViewById(R.id.counterList);
		counterList = counterList.loadListFromFile(context);
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
		
	}
    @Override
    //Loads up the list from memory, adds the most recently changed counter
    //then waits for user to select a counter
    protected void onStart() {
            super.onStart();            
    		CounterModel lastCounter= emptyCounter.loadFromFile(context);
    		if(lastCounter != null){
    			counterList.addCounter(lastCounter);
    		}
            counterList.sort();
            counterList.saveListToFile(context);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, counterList.getNameList());
            counterListView.setAdapter(adapter);
        	counterListView.setOnItemClickListener(new OnItemClickListener() {    
        	    @Override
        	    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {     	    	
        	    	CounterModel foundCounter = new CounterModel();
        	    	//looks for the counter you clicked, needs to split the string to get name
        	    	String listItemString = (counterListView.getItemAtPosition(position)).toString();
        	    	String[] splitString = listItemString.split(":", 2);
        	    	foundCounter = counterList.getCounterFromName(splitString[0]);
        	    	//creates new intent with button
        	    	Intent intent = new Intent(CurnowCounterMainActivity.this, CounterActivity.class);	
        			String counterString = foundCounter.serialization();
        			intent.putExtra(EXTRA_COUNTER, counterString);
        			startActivity(intent);
        	    }
        	});
    }

	//activates when you push the create button
    //called by a button in the activity_curnow__counter.xml
	public void createCounter(View view) {
		//Gets the string from CreateButton EditText
		EditText buttonTitle = (EditText) findViewById(R.id.CreateButtonEditText);
		String message = buttonTitle.getText().toString();
		//only create button if string length is > 0
		if((message.length() > 0) && (message.indexOf(":") == -1) ) {
			//Creates a new counter (and serializes it) with the message from CreatButton
			//then passes it through a newly created intent
			CounterModel new_counter = new CounterModel(message);		
			Intent intent = new Intent(this, CounterActivity.class);	
			String counterString = new_counter.serialization();
			intent.putExtra(EXTRA_COUNTER, counterString);
			startActivity(intent);
		}
	}
	
	//Clear the Counter list and all visible counters
	//called by a button in the activity_curnow__counter.xml
	public void clearCounters(View view){
		counterList.clearList();
		try {
			String text = counterList.serialization();
			FileOutputStream fos = openFileOutput(FILENAME2, Context.MODE_PRIVATE);
			fos.write(new String(text).getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//resets adapter
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, counterList.getNameList());
        counterListView.setAdapter(adapter);
	}
}
