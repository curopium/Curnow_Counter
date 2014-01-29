package ca.ualberta.cs.curnow_counter;


import java.util.Map;

public class CounterController implements CounterControllerInterface {

	private CounterMapModel counterMapModel;
	
	public CounterController() {
		super();
		counterMapModel = new CounterMapModel();
	}
	
	public void addCounter(CounterModel counter) {
		
		//System.out.println("hello");
		
		Map<String, CounterModel> map = counterMapModel.getCounterMap();
		
        map.put(counter.getName(),counter);
        
	}
	
}
