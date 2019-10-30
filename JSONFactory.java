// CLASS: JSONFactory
// REMARKS: What is the purpose of this class?
//		To create student specific objects for JSONParser
//-----------------------------------------
public class JSONFactory {
	
	//------------------------------------------------------
	// METHOD: getJSONObject
	//
	// PURPOSE: Creates a new JSONObject
	// Returns: specific JSONObject -> ValObject
	//------------------------------------------------------
	public static JSONObject getJSONObject() {
		return (new ValObject());
	}// end getJSONObject

	
	//------------------------------------------------------
	// METHOD: getJSONArray
	//
	// PURPOSE: Creates a new JSONArray
	// Returns: specific JSONArray -> ValArray
	//------------------------------------------------------
	public static JSONArray getJSONArray() {
		return (new ValArray());
	}// end getJSONArray

	
	//------------------------------------------------------
	// METHOD: getJSONValue
	//
	// PURPOSE: Creates a new Value depending on given type
	//
	// PARAMETERS: ValueEnum v -> Value type indicator
	//			   Object o -> actual data of the type specified
	//
	// Returns: specific Value ->
	//	 		ValString or ValInteger or ValDouble or ValBoolean
	//------------------------------------------------------
	public static Value getJSONValue(ValueEnum v, Object o) {

		if (v.equals(ValueEnum.STRING))
			return (new ValString(String.valueOf(o)));

		else if (v.equals(ValueEnum.INT)) 
			return ( new ValInteger( (Integer)o) );

		else if (v.equals(ValueEnum.DOUBLE))
			return ( new ValDouble( (Double)o ) );

		else if (v.equals(ValueEnum.BOOL))
			return ( new ValBoolean( (Boolean)o ) );

		else {
			System.out.println("TYPE ERROR in getJSONValue of JSONFactory");
			return null;
		}// end else
	}// end getJSONValue

	
	//------------------------------------------------------
	// METHOD: getJSONQueryManager
	//
	// PURPOSE: Creates a new JSONQueryManager
	// Returns: specific JSONQueryManager -> QueryManager
	//------------------------------------------------------
	public static JSONQueryManager getJSONQueryManager() {
		return (new QueryManager());
	}// end getJSONQueryManager

}// end JSONFactory class
