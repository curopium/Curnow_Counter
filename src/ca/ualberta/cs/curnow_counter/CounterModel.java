package ca.ualberta.cs.curnow_counter;

import java.util.Date;

import android.widget.Button;
import android.widget.TextView;

public class CounterModel {

	private Button button;
	private Date timestamp;
	private TextView text;
	private int button_value;
	private String name;
	
	public CounterModel() {
		super();
		button_value = 0;
		
		timestamp = new Date();
	}


	public Button getButton() {
		return button;
	}


	public void setButton(Button button) {
		this.button = button;
	}


	public Date getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


	public TextView getText() {
		return text;
	}


	public void setText(TextView text) {
		this.text = text;
	}


	public int getButton_value() {
		return button_value;
	}


	public void setButton_value(int button_value) {
		this.button_value = button_value;
	}



	
	
	
}
