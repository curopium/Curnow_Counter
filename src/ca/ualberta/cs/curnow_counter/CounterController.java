package ca.ualberta.cs.curnow_counter;

import java.util.ArrayList;

public class CounterController implements CounterControllerInterface {
	private CounterListModel counterMapModel;
	
	public CounterController() {
		super();
		counterMapModel = new CounterListModel();
	}
	
	public void addCounter(CounterModel counter) {
		ArrayList<CounterModel> list = CounterListModel.getCounterList();
        list.add(new CounterModel());
	}
}
