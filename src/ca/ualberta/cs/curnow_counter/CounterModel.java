package ca.ualberta.cs.curnow_counter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

public class CounterModel {
	private final String FILENAME = "file.sav";
	private Date timestamp = new Date();
	private int buttonValue;
	private String name;
	public CounterModel() {
		super();
		buttonValue = 0;
	}
	
	public CounterModel(String title) {
		super();
		buttonValue = 0;
		name = title;
	}

	public CounterModel(int button_value) {
		super();
		this.buttonValue = button_value;
	}

	public CounterModel(int button_value, String name) {
		super();
		this.buttonValue = button_value;
		this.name = name;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


	public int getButtonValue() {
		return buttonValue;
	}

	public void setButtonValue(int buttonValue) {
		this.buttonValue = buttonValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public String serialization() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}
	
	public CounterModel deserialization(String text) {
		Gson gson = new Gson();
		CounterModel new_model = gson.fromJson(text, CounterModel.class);
		return new_model;
	}
	
	//SaveingToFile code adapted from lonely Twitter
	public void saveToFile(String text, Context ctx){
		try {
			FileOutputStream fos = ctx.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.write(new String(text).getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//LoadingFromFile code adapted from lonely Twitter
	public CounterModel loadFromFile(Context ctx) {
        ArrayList<CounterModel> counters = new ArrayList<CounterModel>();
        try { 	
                FileInputStream fis = ctx.openFileInput(FILENAME);
                BufferedReader in = new BufferedReader(new InputStreamReader(fis));
                String line = in.readLine();  
                CounterModel counter = new CounterModel();
                counter = counter.deserialization(line);    
                while(line != null) {
                	counters.add(counter);
                	line = in.readLine();
                	//Add the counter to the CounterMap
                	return counter;
                }       
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        return null;
		}
}
