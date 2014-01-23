package ca.ualberta.cs.curnow_counter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class CurnowCounterMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_curnow__counter);
		
		 
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


	
}
