package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import classes.Utils;

public class UtilsTest {
	
	JSONObject obj = Utils.getJSONObjectFromFile("/actors.json");
	JSONArray jsonArray = obj.getJSONArray("actors");

	/**
	 * Checks if the method returns all the actors with firstname and id
	 */
	@Test
	public void testPrintFirstNameAndId() {
		JSONArray expectedArray = new JSONArray();
		JSONObject actor1 = new JSONObject();
		actor1.put("firstName", "Tyrion");
		actor1.put("id", 1005);
		
		JSONObject actor2 = new JSONObject();
		actor2.put("firstName", "Eddard");
		actor2.put("id", 1006);
		
		JSONObject actor3 = new JSONObject();
		actor3.put("firstName", "Daenerys");
		actor3.put("id", 1078);
		
		JSONObject actor4 = new JSONObject();
		actor4.put("firstName", "Jon");
		actor4.put("id", 7893);
		
		expectedArray.put(actor1);
		expectedArray.put(actor2);
		expectedArray.put(actor3);
		expectedArray.put(actor4);
		
		JSONAssert.assertEquals(expectedArray, Utils.printFirstNameAndId(jsonArray), true);
		
	}

	/**
	 * Checks if all the actors are in ascending order by age
	 */
	@Test
	public void testAscendingOrderByAge() {
		
		JSONArray expectedArray = new JSONArray();
		
		JSONObject actor1 = new JSONObject();
		actor1.put("firstName", "Tyrion");
		actor1.put("lastName", "Lannister");
		actor1.put("gender", "male");
		actor1.put("id", 1005);
		actor1.put("age", 28);
		
		JSONObject actor2 = new JSONObject();
		actor2.put("firstName", "Jon");
		actor2.put("lastName", "Snow");
		actor2.put("gender", "male");
		actor2.put("id", 7893);
		actor2.put("age", 26);
		
		JSONObject actor3 = new JSONObject();
		actor3.put("firstName", "Daenerys");
		actor3.put("lastName", "Targaryen");
		actor3.put("gender", "female");
		actor3.put("id", 1078);
		actor3.put("age", 22);
		
		JSONObject actor4 = new JSONObject();
		actor4.put("firstName", "Eddard");
		actor4.put("lastName", "Stark");
		actor4.put("gender", "male");
		actor4.put("id", 1006);
		actor4.put("age", 50);
		
		expectedArray.put(actor3);
		expectedArray.put(actor2);
		expectedArray.put(actor1);
		expectedArray.put(actor4);
		
		JSONAssert.assertEquals(expectedArray, Utils.ascendingOrderByAge(jsonArray), true);
	}

	/**
	 * Get the actor from the JSONArray based on id
	 */
	@Test
	public void testGetActorById() {
		int id = 1005;
		
		JSONObject expectedActor = new JSONObject();
		expectedActor.put("firstName", "Tyrion");
		expectedActor.put("lastName", "Lannister");
		expectedActor.put("gender", "male");
		expectedActor.put("id", 1005);
		expectedActor.put("age", 28);
		
		JSONAssert.assertEquals(expectedActor, Utils.getActorById(jsonArray,id), true);
		
	}
	/**
	 * Test if the Id is valid (4 digits). Should be false
	 */
	@Test
	public void NotValidId() {
		int id = 23423;
		assertFalse(Utils.isValidId(id));
	}
	/**
	 * Test if the id is valid. Should be true
	 */
	@Test
	public void ValidId() {
		int id = 2343;
		assertTrue(Utils.isValidId(id));
	}
	/**
	 * Check if the id exists in the JSONArray. Should be false 
	 */
	@Test
	public void IdNotFound() {
		int id = 2324;
		
		assertFalse(Utils.isIdFound(jsonArray, id));
		
	}
	/**
	 * Check if the id exists in the JSONArray. Should be true
	 */
	@Test
	public void IdFound() {
		int id = 1078;
		
		assertTrue(Utils.isIdFound(jsonArray, id));
		
	}

}
