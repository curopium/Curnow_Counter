package ca.ualberta.cs.curnow_counter;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;


public class CounterListModel {
	
	private static ArrayList<CounterModel> counterList;

	public CounterListModel() {
         super();
         counterList = new ArrayList<CounterModel>();
	}
	
	public static ArrayList<CounterModel> getCounterList() {
		return counterList;
	}

	public static void setCounterList(ArrayList<CounterModel> counterList) {
		CounterListModel.counterList = counterList;
	}

	public static void add(CounterModel model){
		if(model != null) {
			counterList.add(model);
		}
	}
	
	public static int getSize(){
		return counterList.size();
	}
	

	public String getserialization() {
		
		Gson gson = new Gson();
		
		List<CounterModel> counters = new ArrayList<CounterModel>();
		
		for (CounterModel counter :counterList) {
			counters.add(counter);
		}
		
		String json = gson.toJson(counters);
		
		System.out.println(json);
		
		return json;
	}
	
	public static void clearList(){
		counterList.clear();
	}

	public static ArrayList<String> getNameList(){
		ArrayList<String> nameList = new ArrayList<String>();
		
		for (CounterModel counter :counterList) {
			nameList.add(counter.getName());
		}
		
		
		return nameList;
	}
}
