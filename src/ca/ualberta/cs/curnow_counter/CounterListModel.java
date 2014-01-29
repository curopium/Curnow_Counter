package ca.ualberta.cs.curnow_counter;



import java.util.ArrayList;


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
		counterList.add(model);
	}
	
	public static int getSize(){
		return counterList.size();
	}

	public static ArrayList<String> getNameList(){
		ArrayList<String> nameList = new ArrayList<String>();
		
		for (CounterModel counter :counterList) {
			nameList.add(counter.getName());
		}
		
		
		return nameList;
	}
}
