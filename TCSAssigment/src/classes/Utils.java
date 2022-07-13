package classes;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class Utils {
	
	public static String getJSONStringFromFile(String path) {
		Scanner scanner;
		InputStream in = FileHandler.inputStreamFromFile(path);
		scanner = new Scanner(in);
		String json = scanner.useDelimiter("//Z").next();
		scanner.close();
		return json;
		
	}
	
	public static JSONObject getJSONObjectFromFile(String path) {
		return new JSONObject(getJSONStringFromFile(path));
	}
	
	
	
	/**
	 * Task 1 - Print out the firstname and Id of each actors in JSON format
	 * @param complete jsonArray of all the actors and details
	 * @return JSONArray with firstname and id of each actors
	 */
		public static JSONArray printFirstNameAndId(JSONArray jsonArray) {
			
			if(jsonArray.isEmpty()) {
				return null;
			}
			
			JSONArray newJsonArray = new JSONArray();
				//Adds the new jSONObject into the JSONArray
			for(int i = 0; i<jsonArray.length(); i++) {
				String name = jsonArray.getJSONObject(i).getString("firstName");
				int id = jsonArray.getJSONObject(i).getInt("id");
				JSONObject js = new JSONObject();
				js.put("firstName", name);
				js.put("id", id);
				newJsonArray.put(js);
			}
			
			System.out.println("Task 1");
			System.out.println(newJsonArray.toString(4));
			
			return newJsonArray;
		}
		
		
		/**
		 * Task 2 - Print out all actors in ascending order based on their age in JSON format
		 * @param complete jsonArray with all the actors and details
		 * @return JSONArray with all the actors in ascending order
		 */
		public static JSONArray ascendingOrderByAge(JSONArray jsonArray) {
			
			List<JSONObject> sortedList = new ArrayList<JSONObject>();
			JSONArray sortedArray = new JSONArray();
			
			for(int i = 0; i<jsonArray.length(); i++) {
				sortedList.add(jsonArray.getJSONObject(i));
			}
			
			//Sorts the list based on the age-attribute
			Collections.sort(sortedList, new Comparator<JSONObject>() {
				int value = 0;
				public int compare(JSONObject a, JSONObject b) {
					int age1 = 0;
			        int age2 = 0;
					
					try {
						age1 =  a.getInt("age");
						age2 =  b.getInt("age");
					}catch(Exception e) {
						e.printStackTrace();
					}
					
					if(age1 > age2) {
						value = 1;
					}
					else if (age1 == age2) {
						value = 0;
					}
					else {
						value = -1;
					}
					
				return value;
					
				}});
			//Once the list is sorted by age, the JSONObjects are added into a new sorted JSONArray
			for(int i = 0; i<sortedList.size(); i++) {
				sortedArray.put(sortedList.get(i));
			}
			
				System.out.println("Task 2");
				System.out.println(sortedArray.toString(4));
			
			return sortedArray;
			
		}
		
		/**
		 * Task 3 - Print an actor details based on given id in JSON format
		 * @param complete jsonArray of all the actors
		 * @param input id from the user to get a specific actor
		 * @return JSONObject of the actor given by the id
		 */
		public static JSONObject getActorById(JSONArray jsonArray, int id) {
			JSONObject actor = new JSONObject();
		//Assuming each id is unique, it will get the correct actor
		for(int i = 0; i<jsonArray.length(); i++) {
			if(jsonArray.getJSONObject(i).getInt("id") == id) {
				actor = jsonArray.getJSONObject(i);
			}
		}
		
		System.out.println("Task 3");
		System.out.println(actor.toString(4));
		
		return actor;
	}
	
		/**
		 * Task 4 - Check if id is valid. If false, send error message 
		 * @param input id from the user
		 * @return boolean 
		 */
		public static boolean isValidId(int id) {
			boolean isValid = false;
			//Assuming a valid Id has 4 digits 
			if(id > 999 && id < 10000) {
				isValid = true;
			}else {
				JSONObject errormessage = new JSONObject();
				errormessage.put("reason", "id is invalid");
				System.out.println("Task 4");
				System.out.println(errormessage.toString(4));
			}
			
			return isValid;
		}
		
		/**
		 * Task 5 - Check if id is found. If false, send error message
		 * @param complete jsonArray of all the actors
		 * @param input id from user
		 * @return boolean
		 */
		public static boolean isIdFound(JSONArray jsonArray, int id) {
			boolean isFound = false;
			for(int i = 0; i<jsonArray.length(); i++) {
				if(jsonArray.getJSONObject(i).getInt("id") == id) {
					isFound = true;
				}
			}
			//If the actor is not found, an errormessage will be sent to the user 
				if(!isFound) {
					JSONObject errorMessage = new JSONObject();
					errorMessage.put("reason", "actor with id " + id + " is not found");
					System.out.println("Task 5");
					System.out.println(errorMessage.toString(4));
				}
			
			return isFound;
		
}
}
