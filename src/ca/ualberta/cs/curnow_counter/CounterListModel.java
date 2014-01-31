package ca.ualberta.cs.curnow_counter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CounterListModel {
	
	private static ArrayList<CounterModel> counterList;
	private static final String FILENAME2 = "file2.sav";

	//creates an empty CounterListModel
	public CounterListModel() {
         super();
         counterList = new ArrayList<CounterModel>();
	}
	
	//Returns the Arraylist
	public static ArrayList<CounterModel> getCounterList() {
		return counterList;
	}
	
	public void delete (String name) {
		//System.out.println(name);
		counterList.remove(this.getCounterFromName(name));
		//System.out.println(this.getNameList());
	}

	//Sets the Arraylist
	public static void setCounterList(ArrayList<CounterModel> counterList) {
		CounterListModel.counterList = counterList;
	}

	//Adds in a counter, will scan over the entire list to check if the counter
	//is all-ready in the list. If it is in the list, it will just overwrite
	//that counter instead of creating a copy of it.
	public static void addCounter(CounterModel model){
		if(model.getName().toString().equals("DeleteMe")) {
			return;
		}
		if(model != null ) {
			for (CounterModel counter :counterList) {
				if((model.getName()).equals(counter.getName())){
					counter.setButtonValue(model.getButtonValue());
					counter.setTimestamp(model.getTimestamp());
					return;
				}
			}
				counterList.add(model);
			}
	}
	
	//returns the number of counters
	public int getSize(){
		return counterList.size();
	}
	
	//sorts the ArrayList based upon button value (ascending)
	public void sort(){
		Collections.sort(counterList, new Comparator<CounterModel>() {
			@Override public int compare(CounterModel one, CounterModel two) {
				return one.getButtonValue() - two.getButtonValue();
			}
		});
		
	}
	
	//Converts ArralyList of Objects to a Gson String
	public String serialization() {
		Gson gson = new Gson();
		List<CounterModel> counters = new ArrayList<CounterModel>();
		for (CounterModel counter :counterList) {
			counters.add(counter);
		}
		String json = gson.toJson(counters);
		return json;
	}
	
	//Converts a Gson CounterListmodelString string to a CounterListModel object
	public CounterListModel deserialization(String text) {
		Gson gson = new Gson();
		Type type = new TypeToken<ArrayList<CounterModel>>(){}.getType();
		List<CounterModel> new_list = gson.fromJson(text, type);
		CounterListModel new_array = new CounterListModel();
		for (CounterModel counter :new_list) {
			CounterListModel.addCounter(counter);
		}
		return new_array;
	}
	
	//Empties out all the Counters
	public void clearList(){
		counterList.clear();
	}

	//Returns an ArrayList of strings of every user-created CounterModel
	public ArrayList<String> getNameList(){
		ArrayList<String> nameList = new ArrayList<String>();
		for (CounterModel counter :counterList) {
			//Do not print counter to delete
			if(!(counter.getName()).toString().equals("DeleteMe"))
			{
				if(!(counter.getName()).toString().equals("DummyCounter"))
				{
			
				String stringLine = counter.getName() + ": " + Integer.toString(counter.getButtonValue()); 
				nameList.add(stringLine);
				}
			}
		}
		return nameList;
	}
	
	//Finds a specific CounterModel from a CounterModel's name
	public CounterModel getCounterFromName(String name){
		for (CounterModel counter :counterList) {
			if(name.equals(counter.getName())){
				return counter;
			}
		}
		return null;
	}
	
	//Saves Gson serialized version of CounterListModel
	//into a file
	public void saveListToFile(Context ctx ){
		if (counterList.isEmpty()) {
			return;
		}
		try {
			String text = this.serialization();	
			FileOutputStream fos = ctx.openFileOutput(FILENAME2, Context.MODE_PRIVATE);
			fos.write(new String(text).getBytes());
			fos.close();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Loads from a file a Gson Serialized CounterListModel
	//and returns a CounterListObject converted from the
	//de-serialized string
	public CounterListModel loadListFromFile(Context ctx) {
        try { 	
                FileInputStream fis = ctx.openFileInput(FILENAME2);
                BufferedReader in = new BufferedReader(new InputStreamReader(fis));
                String line = in.readLine();   
                return this.deserialization(line);    
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        	return null;
		}
}
