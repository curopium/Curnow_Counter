package ca.ualberta.cs.curnow_counter;

import java.util.Date;

import com.google.gson.Gson;

import android.widget.Button;
import android.widget.TextView;

public class CounterModel {

	
	private Date timestamp;
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


}
