package ca.ualberta.cs.curnow_counter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CounterListModel {
	
	private static ArrayList<CounterModel> counterList;
	private static final String FILENAME2 = "file2.sav";

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
		//checks to see if name is in the list, it if is, copy the values over
		if(model != null) {
			for (CounterModel counter :counterList) {
					System.out.println("delete");
					System.out.println(model.getName().toString());
				if((model.getName()).toString().equals("DeleteMe")){
					counterList.remove(counter);
					System.out.println("Truedelete");
					return;
				}
				if((model.getName()).equals(counter.getName())){
					counter.setButtonValue(model.getButtonValue());
					counter.setTimestamp(model.getTimestamp());
					return;
				}
			}
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
		return json;
	}
	
	public CounterListModel getdeserialization(String text) {
		Gson gson = new Gson();
		//http://kodejava.org/how-do-i-convert-collections-into-json/
		
		Type type = new TypeToken<ArrayList<CounterModel>>(){}.getType();
		List<CounterModel> new_list = gson.fromJson(text, type);
		CounterListModel new_array = new CounterListModel();
		for (CounterModel counter :new_list) {
			new_array.add(counter);
		}
		return new_array;
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
	
	public static CounterModel getCounterFromName(String name){
		for (CounterModel counter :counterList) {
			if(name == counter.getName()){
				return counter;
			}
		}
		return null;
	}
	
	public void saveListToFile(Context ctx ){
		try {
			String text = this.getserialization();	
			FileOutputStream fos = ctx.openFileOutput(FILENAME2, Context.MODE_PRIVATE);
			fos.write(new String(text).getBytes());
			fos.close();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
