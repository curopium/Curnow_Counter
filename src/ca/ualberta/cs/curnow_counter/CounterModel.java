package ca.ualberta.cs.curnow_counter;

import java.util.Date;

import com.google.gson.Gson;

import android.widget.Button;
import android.widget.TextView;

public class CounterModel {

	
	private Date timestamp;
	private int button_value;
	private String name;
	
	public CounterModel() {
		super();
		button_value = 0;
	}
	
	public CounterModel(String title) {
		super();
		button_value = 0;
		name = title;
	}

	public CounterModel(int button_value) {
		super();
		this.button_value = button_value;
	}

	public CounterModel(int button_value, String name) {
		super();
		this.button_value = button_value;
		this.name = name;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getButton_value() {
		return button_value;
	}

	public void setButton_value(int button_value) {
		this.button_value = button_value;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
