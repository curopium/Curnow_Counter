package ca.ualberta.cs.curnow_counter;



import java.util.Map;

public class CounterMapModel {
	
	private static Map<String, CounterModel> counterMap;

	
	
	public static Map<String, CounterModel> getCounterMap() {
		return counterMap;
	}

	public static void setCounterMap(Map<String, CounterModel> counterMap) {
		CounterMapModel.counterMap = counterMap;
	}

	

}
