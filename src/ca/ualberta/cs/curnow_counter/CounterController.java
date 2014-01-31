package ca.ualberta.cs.curnow_counter;

import java.util.ArrayList;

public class CounterController implements CounterControllerInterface {
	//Suppressed warning because CounterController is used (in constructor)
	@SuppressWarnings("unused")
	private CounterListModel counterMapModel;
	
	public CounterController() {
		super();
		counterMapModel = new CounterListModel();
	}
	
	//Implement addCounter
	public void addCounter(CounterModel counter) {
		ArrayList<CounterModel> list = CounterListModel.getCounterList();
        list.add(new CounterModel());
	}
}
