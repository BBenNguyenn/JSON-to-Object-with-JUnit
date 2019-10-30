// CLASS: QueryManager
// REMARKS: What is the purpose of this class?
//		To represent a Query Manager to make queries of JSON objects
//-----------------------------------------
import java.util.Scanner;

public class QueryManager implements JSONQueryManager {

	private JSONObject object;
	private JSONParser parser;

	//QueryManager constructor
	public QueryManager() {
		object = null;
		parser = new JSONParser();
	}// end constructor

	
	//------------------------------------------------------
	// METHOD: loadJSON
	//
	// PURPOSE: parses the given JSON string and set it as working object
	// PARAMETERS: String JSON -> JSON object in String format
	//------------------------------------------------------
	@Override
	public void loadJSON(String JSON) {
		object = parser.read(JSON);
	}// end loadJSON
	
	
	//------------------------------------------------------
	// METHOD: getJSONValue
	//
	// PURPOSE: Retrieves a value from JSON String loaded from a query string
	// PARAMETERS: String query -> query given in the form "key1.key2. ..."
	// Returns: Value (of any types: 
	//			ValString, ValInteger, ValDouble, ValBoolean, ValArray, ValObject)
	//------------------------------------------------------
	@Override
	@SuppressWarnings("resource")
	public Value getJSONValue(String query) throws IllegalStateException {
		
		Scanner reader = null;
		Value retVal = null;
		JSONObject currObj = null;
		ValString key = null;

		if (object != null) {
			
			currObj = object;
			reader = new Scanner(query).useDelimiter("\\.");
			
			while (reader.hasNext()) {
				
				key = new ValString("\""+(reader.next())+"\"");
				
				if (key.toString().contains("[")) 
					retVal = getArrayJSONValue(key, currObj);
				else 
					retVal = currObj.getValue(key);
				
				if (retVal instanceof JSONObject)
					currObj = (JSONObject) retVal;
			}// end while

			reader.close();
			return retVal;
		}
		else 
			throw new IllegalStateException
			("JSON not loaded via loadJSON() yet and or object loaded is null.");
	}// end getJSONValue
	
	
	
	//------------------------------------------------------
	// HELPER METHOD: getArrayJSONValue
	//
	// PURPOSE: Handles the case with querying an array
	//
	// PARAMETERS: ValString key -> the key of the array object
	//			   JSONObject currObj -> working object being obtained
	//
	// Returns: Value (of any types: 
	//			ValString, ValInteger, ValDouble, ValBoolean, ValArray, ValObject)
	//------------------------------------------------------
	@SuppressWarnings("resource")
	public Value getArrayJSONValue(ValString key, JSONObject currObj) {
		
		Value retVal = null;
		Scanner arrReader = null;
		String arrKeyStr = null;
		IterArray arrIter = null;
		
		// removes all brackets to have uniform delimiter which is a space
		arrKeyStr = key.toString().replace("][", " ");
		arrKeyStr = arrKeyStr.replace("]", " ");
		arrKeyStr = arrKeyStr.replace("[", " ");
		arrReader = new Scanner(arrKeyStr);
		
		key = new ValString((arrReader.next())+"\"");
		retVal = currObj.getValue(key);
		
		while (retVal instanceof JSONArray && arrReader.hasNextInt()) {
			arrIter = (IterArray) ((JSONArray) retVal).iterator();
			
			int i = arrReader.nextInt();
			
			//if array index is invalid, i.e. less than 0, then just return null
			if (i < 0)
				return null;
			
			while (i >= 0) {
				retVal = arrIter.getNext();
				i--;
			}// end while
			
			//if array index is invalid, i.e. greater than array length, the just return null
			if (i > 0)
				return null;
		}// end while
		
		//prints a warning for the case that a nested array has too many element get indices.
//		if (arrReader.hasNextInt() && !(retVal instanceof JSONArray))
//			System.out.println("WARNING: extra array key noted, only valid element returned.");

		return retVal;
	}// end getArrayJSONValue

}// end QueryManager class
