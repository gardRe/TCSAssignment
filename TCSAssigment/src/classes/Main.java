package classes;

import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//Gets the JSONArray from json-file
		JSONObject obj = Utils.getJSONObjectFromFile("/actors.json");
		JSONArray jsonArray = obj.getJSONArray("actors");
		int id = 0;
	
		//Task 1
		Utils.printFirstNameAndId(jsonArray);
		
		//Task 2
		Utils.ascendingOrderByAge(jsonArray);
		
	
		try {
			System.out.println("Please enter actorId:");
			id = scanner.nextInt();
		}catch(Exception e) {}
		
			//Task 4 & Task 5
		if(Utils.isValidId(id) && Utils.isIdFound(jsonArray, id)) {
				
				//Task 3
			Utils.getActorById(jsonArray, id);
		
		}
	
	}
	
}
