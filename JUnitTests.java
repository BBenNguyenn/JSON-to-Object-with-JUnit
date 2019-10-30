//-----------------------------------------
// REMARKS: What is the purpose of this program?
//		To parse then store JSON Objects as Java objects
//		using interfaces given. Also be able to access
//		objects by inputing queries.
//-----------------------------------------
import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitTests {

	//====================================================================================================================
	//=====================================================  PART 1  =====================================================
	//====================================================================================================================

	//------------------------------------------------------
	// METHOD: pt1TestEmptyJSON
	// PURPOSE: tests empty JSON with toString() and equals() methods
	//------------------------------------------------------
	@Test
	public void pt1TestEmptyJSON() {

		JSONParser jp = new JSONParser();
		JSONObject obj = jp.read("{ }"); // original testing JSONObject with one empty space
		JSONObject obj2 = jp.read("{  }"); // two empty spaces
		JSONObject obj3 = jp.read("{ \"alpha\" : \"beta\" }"); // non-empty object
		assertEquals("Empty JSON should have just contain curly braces with two spaces", obj2.toString(), obj.toString());
		assertTrue("Empty JSON objects should be equal regardless of empty spaces", obj.equals(obj2));
		assertFalse("Empty JSON object should not be equal to a non-empty JSON object", obj.equals(obj3));
		// reverse order to make sure logic checks out
		assertFalse("Empty JSON object should not be equal to a non-empty JSON object", obj3.equals(obj)); 
	}// end pt1TestEmptyJSON


	//------------------------------------------------------
	// METHOD: pt1TestValString
	// PURPOSE: tests toString() and equals() methods of String JSON type
	//------------------------------------------------------
	@Test
	public void pt1TestValString() {

		JSONParser jp = new JSONParser();
		JSONObject obj = jp.read("{ \"name\" : \"Ben\" }"); // original testing JSONObject
		JSONObject obj2 = jp.read("{  \"name\"  :  \"Ben\"  }"); // extra spaces between elements
		JSONObject obj3 = jp.read("{ \"name\" : \"Another Name\" }"); // wrong value
		JSONObject obj4 = jp.read("{ \"wrongKey\" : \"Ben\" }"); // wrong key

		assertEquals("toString() should string write as expected ", "{ \"name\" : \"Ben\" }", obj.toString());
		assertTrue("equals() should return true", obj.equals(obj));
		assertTrue("equals() should return true", obj.equals(obj2));
		assertFalse("equals() should return false", obj.equals(obj3));
		assertFalse("equals() should return false", obj.equals(obj4));
	}// end pt1TestValString


	//------------------------------------------------------
	// METHOD: pt1TestValInteger
	// PURPOSE: tests toString() and equals() methods of Integer JSON type
	//------------------------------------------------------
	@Test
	public void pt1TestValInteger() {

		JSONParser jp = new JSONParser();
		JSONObject obj = jp.read("{ \"ID\" : 7777777 }"); // original testing JSONObject
		JSONObject obj2 = jp.read("{   \"ID\"   :   007777777   }"); // extra spaces between elements and padded zeros
		JSONObject obj3 = jp.read("{ \"ID\" : 0000000 }"); // wrong value
		JSONObject obj4 = jp.read("{ \"wrongKey\" : 7777777 }"); // wrong key

		assertEquals("toString() should string write as expected ", "{ \"ID\" : 7777777 }", obj.toString());
		assertTrue("equals() should return true", obj.equals(obj));
		assertTrue("equals() should return true", obj.equals(obj2));
		assertFalse("equals() should return false", obj.equals(obj3));
		assertFalse("equals() should return false", obj.equals(obj4));
	}// end pt1TestValInteger


	//------------------------------------------------------
	// METHOD: pt1TestValDouble
	// PURPOSE: tests toString() and equals() methods of Double JSON type
	//------------------------------------------------------
	@Test
	public void pt1TestValDouble() {

		JSONParser jp = new JSONParser();
		JSONObject obj = jp.read("{ \"GPA\" : 1.1 }"); // original testing JSONObject
		JSONObject obj2 = jp.read("{   \"GPA\"   :   01.1000   }"); // extra spaces between elements and padded zeros
		JSONObject obj3 = jp.read("{ \"GPA\" : 1.29 }"); // wrong value
		JSONObject obj4 = jp.read("{ \"wrongKey\" : 1.1 }"); // wrong key

		assertEquals("toString() should string write as expected ", "{ \"GPA\" : 1.1 }", obj.toString());
		assertTrue("equals() should return true", obj.equals(obj));
		assertTrue("equals() should return true", obj.equals(obj2));
		assertFalse("equals() should return false", obj.equals(obj3));
		assertFalse("equals() should return false", obj.equals(obj4));
	}// end pt1TestValDouble


	//------------------------------------------------------
	// METHOD: pt1TestValBoolean
	// PURPOSE: tests toString() and equals() methods of Boolean JSON type
	//------------------------------------------------------
	@Test
	public void pt1TestValBoolean() {

		JSONParser jp = new JSONParser();
		JSONObject obj = jp.read("{ \"isMajestic\" : true }"); // original testing JSONObject
		JSONObject obj2 = jp.read("{   \"isMajestic\"   :   true   }"); // extra spaces between elements
		JSONObject obj3 = jp.read("{ \"isMajestic\" : false }"); // wrong value
		JSONObject obj4 = jp.read("{ \"wrongKey\" : true }"); // wrong key

		assertEquals("toString() should string write as expected ", "{ \"isMajestic\" : true }", obj.toString());
		assertTrue("equals() should return true", obj.equals(obj));
		assertTrue("equals() should return true", obj.equals(obj2));
		assertFalse("equals() should return false", obj.equals(obj3));
		assertFalse("equals() should return false", obj.equals(obj4));
	}// end pt1TestValBoolean


	//------------------------------------------------------
	// METHOD: pt1TestIterObject
	// PURPOSE: tests the object iterator methods hasNext() and getNext()
	//------------------------------------------------------
	@Test
	public void pt1TestIterObject() {

		JSONParser jp = new JSONParser();

		JSONObject emptyObj = jp.read("{ }");
		JSONIter emptyIter = emptyObj.iterator();
		Value key = emptyIter.getNext();
		Value val = emptyObj.getValue(key);

		JSONObject obj = jp.read("{ \"name\" : \"Ben\" , \"ID\" : 7777777 , \"GPA\" : 1.1 , \"isMajestic\" : true }");
		JSONIter iter = obj.iterator();

		//Testing IterObject on an empty object
		assertFalse("hasNext() should return false", emptyIter.hasNext());
		assertEquals("getValue()", null, emptyIter.getNext());

		//Testing IterObject on non-empty object
		key = iter.getNext();
		val = obj.getValue(key);
		assertTrue("hasNext() should return true", iter.hasNext());
		assertEquals("getValue() should return value given", "\"Ben\"", val.toString());
		key = iter.getNext();
		val = obj.getValue(key);
		assertEquals("getValue() should return value given", "7777777", val.toString());
		key = iter.getNext();
		val = obj.getValue(key);
		assertEquals("getValue() should return value given", "1.1", val.toString());
		key = iter.getNext();
		val = obj.getValue(key);
		assertEquals("getValue() should return value given", "true", val.toString());
		assertFalse("hasNext() should return false", iter.hasNext());
	}// end pt1TestIterObject


	//------------------------------------------------------
	// METHOD: pt1TestJSONObject
	// PURPOSE: tests the JSONObject implemented methods 
	//------------------------------------------------------
	@Test
	public void pt1TestJSONObject() {

		JSONParser jp = new JSONParser();
		// original object
		JSONObject obj = jp.read("{ \"name\" : \"Ben\" , \"ID\" : 7777777 , \"GPA\" : 1.1 , \"isMajestic\" : true }");
		// same values but in different order
		JSONObject obj2 = jp.read("{ \"isMajestic\" : true , \"name\" : \"Ben\" , \"ID\" : 7777777 , \"GPA\" : 1.1 }");
		// wrong information, but same key
		JSONObject obj3 = jp.read("{ \"name\" : \"John Doe\" , \"ID\" : 0000000 , \"GPA\" : 3.0 , \"isMajestic\" : false }");
		// wrong keys, but same information
		JSONObject obj4 = jp.read("{ \"nom\" : \"Ben\" , \"id\" : 7777777 , \"gpa\" : 1.1 , \"majestic?\" : true }");
		// original but with duplicates
		JSONObject obj5 = jp.read("{ \"name\" : \"this will get replaced\" , \"ID\" : 7777777 , \"GPA\" : 4.0 , \"isMajestic\" : false"
				+ " , \"name\" : \"Ben\" , \"ID\" : 7777777 , \"GPA\" : 1.1 , \"isMajestic\" : true }");

		assertEquals("toString() should string write as expected ",
				"{ \"name\" : \"Ben\" , \"ID\" : 7777777 , \"GPA\" : 1.1 , \"isMajestic\" : true }", obj.toString());

		// Testing equals cases
		assertTrue("equals() should return true", obj.equals(obj));
		assertTrue("equals() should return true", obj.equals(obj2));
		assertTrue("equals() should return true", obj2.equals(obj)); // reverse order
		assertFalse("equals() should return false", obj.equals(obj3));
		assertFalse("equals() should return false", obj.equals(obj4));

		// This checks if duplicates get added i.e. if obj5 (with duplicates) is same as obj
		assertTrue("equals() should return true", obj.equals(obj5));
	}// end pt1TestJSONObject


	//------------------------------------------------------
	// METHOD: pt1TestIterArray
	// PURPOSE: tests the array iterator methods hasNext() and getNext()
	//------------------------------------------------------
	@Test
	public void pt1TestIterArray() {

		JSONParser jp = new JSONParser();
		JSONObject objEmpArr = jp.read("{ \"arr\" : [ ] }");						 	// I know the use of ValString here is bad since we are
		JSONArray emptyArr = (JSONArray) objEmpArr.getValue(new ValString("\"arr\"")); // suppose to use the interface but not sure how to not use it.																	
		JSONIter emptyIter = emptyArr.iterator(); // array specific iterator created i.e IterArray and not IterObject
		Value val = emptyIter.getNext();

		JSONObject obj = jp.read("{ \"arr\" : [ \"Ben\" , 7777777 , 1.1 , true ] }"); // original array object
		JSONArray arr = (JSONArray) obj.getValue(new ValString("\"arr\""));
		JSONIter iter = arr.iterator(); // array specific iterator created i.e IterArray and not IterObject
		val = iter.getNext();

		//Testing IterArray on empty array
		assertFalse("hasNext() should return false", emptyIter.hasNext());
		assertEquals("getValue()", null, emptyIter.getNext());

		//Testing IterArray on non-empty array
		assertTrue("hasNext() should return true", iter.hasNext());
		assertEquals("getValue() should return value given", "\"Ben\"", val.toString());
		val = iter.getNext();
		assertEquals("getValue() should return value given", "7777777", val.toString());
		val = iter.getNext();
		assertEquals("getValue() should return value given", "1.1", val.toString());
		val = iter.getNext();
		assertEquals("getValue() should return value given", "true", val.toString());
		assertFalse("hasNext() should return false", iter.hasNext());
	}// end pt1TestIterArray


	//------------------------------------------------------
	// METHOD: pt1TestJSONArrayAsObject
	// PURPOSE: tests the JSONArray Value methods as an object
	//------------------------------------------------------
	@Test
	public void pt1TestJSONArrayAsObject() {
		JSONParser jp = new JSONParser();
		JSONObject obj = jp.read("{ \"arr\" : [ \"Ben\" , 7777777 , 1.1 , true ] }"); // original array object
		JSONObject obj2 = jp.read("{ \"arr\" : [ 7777777 , true , 1.1 , \"Ben\" ] }"); // different order
		JSONObject obj3 = jp.read("{ \"array\" : [ \"Ben\" , 7777777 , 1.1 , true ] }"); // different key
		JSONObject obj4 = jp.read("{ \"arr\" : [ 34 , 34 , 34 ] }"); // same values, different array

		assertEquals("toString() should string write as expected ",
				"{ \"arr\" : [ \"Ben\" , 7777777 , 1.1 , true ] }", obj.toString());

		// Testing equals cases
		assertTrue("equals() should return true", obj.equals(obj));
		assertFalse("equals() should return false", obj.equals(obj2));
		assertFalse("equals() should return false", obj.equals(obj3));
		assertFalse("equals() should return false", obj.equals(obj4));
	}// end pt1TestJSONArrayAsObject


	//------------------------------------------------------
	// METHOD: pt1TestJSONArrayAsArray
	// PURPOSE: tests the JSONArray implemented methods as array
	//------------------------------------------------------
	@Test
	public void pt1TestJSONArrayAsArray() {
		JSONParser jp = new JSONParser();
		JSONObject obj = jp.read("{ \"arr\" : [ \"Ben\" , 7777777 , 1.1 , true ] }"); // original array object
		JSONObject obj2 = jp.read("{ \"arr\" : [ 7777777 , true , 1.1 , \"Ben\" ] }"); // different order
		JSONObject obj3 = jp.read("{ \"array\" : [ \"Ben\" , 7777777 , 1.1 , true ] }"); // different key
		JSONObject obj4 = jp.read("{ \"arr\" : [ 34 , 34 , 34 ] }"); // same values, different array

		JSONArray arr =  (JSONArray)  obj.getValue(new ValString("\"arr\""));
		JSONArray arr2 = (JSONArray) obj2.getValue(new ValString("\"arr\""));
		JSONArray arr3 = (JSONArray) obj3.getValue(new ValString("\"arr\""));
		JSONArray arr4 = (JSONArray) obj4.getValue(new ValString("\"arr\""));

		assertEquals("toString() should string write as expected ",
				"[ \"Ben\" , 7777777 , 1.1 , true ]", arr.toString());

		// Testing equals cases
		assertTrue("equals() should return true", arr.equals(arr));
		assertFalse("equals() should return false", arr.equals(arr2));
		assertFalse("equals() should return false", arr.equals(arr3));
		assertFalse("equals() should return false", arr.equals(arr4));
	}// end pt1TestJSONArrayAsArray


	//====================================================================================================================
	//=====================================================  PART 2  =====================================================
	//====================================================================================================================

	//------------------------------------------------------
	// METHOD: pt2TestStringQuery
	// PURPOSE: tests simple String query
	//------------------------------------------------------
	@Test
	public void pt2TestStringQuery() {

		JSONQueryManager qm = JSONFactory.getJSONQueryManager();
		qm.loadJSON("{ \"name\" : \"Ben\" }");

		Value val = qm.getJSONValue("name");
		assertEquals("Query should return expected value", "\"Ben\"", val.toString());
	}// end pt2TestObjectQuery


	//------------------------------------------------------
	// METHOD: pt2TestIntegerQuery
	// PURPOSE: tests simple Integer query
	//------------------------------------------------------
	@Test
	public void pt2TestIntegerQuery() {

		JSONQueryManager qm = JSONFactory.getJSONQueryManager();
		qm.loadJSON("{ \"ID\" : 7777777 }");

		Value val = qm.getJSONValue("ID");
		assertEquals("Query should return expected value", "7777777", val.toString());
	}// end pt2TestIntegerQuery


	//------------------------------------------------------
	// METHOD: pt2TestDoubleQuery
	// PURPOSE: tests simple Double query
	//------------------------------------------------------
	@Test
	public void pt2TestDoubleQuery() {

		JSONQueryManager qm = JSONFactory.getJSONQueryManager();
		qm.loadJSON("{ \"GPA\" : 1.1 }");

		Value val = qm.getJSONValue("GPA");
		assertEquals("Query should return expected value", "1.1", val.toString());
	}// end pt2TestDoubleQuery


	//------------------------------------------------------
	// METHOD: pt2TestBooleanQuery
	// PURPOSE: tests simple Boolean query
	//------------------------------------------------------
	@Test
	public void pt2TestBooleanQuery() {

		JSONQueryManager qm = JSONFactory.getJSONQueryManager();
		qm.loadJSON("{ \"isMajestic\" : true }");

		Value val = qm.getJSONValue("isMajestic");
		assertEquals("Query should return expected value", "true", val.toString());
	}// end pt2TestBooleanQuery


	//------------------------------------------------------
	// METHOD: pt2TestAllPrimValQuery
	// PURPOSE: tests all primitive Values in one object query
	//------------------------------------------------------
	@Test
	public void pt2TestAllPrimValQuery() {

		JSONQueryManager qm = JSONFactory.getJSONQueryManager();
		qm.loadJSON("{ \"name\" : \"Ben\" , \"ID\" : 7777777 , \"GPA\" : 1.1 , \"isMajestic\" : true }");

		Value val = qm.getJSONValue("name");
		assertEquals("Query should return expected value", "\"Ben\"", val.toString());

		val = qm.getJSONValue("ID");
		assertEquals("Query should return expected value", "7777777", val.toString());

		val = qm.getJSONValue("GPA");
		assertEquals("Query should return expected value", "1.1", val.toString());

		val = qm.getJSONValue("isMajestic");
		assertEquals("Query should return expected value", "true", val.toString());
	}// end pt2TestAllPrimValQuery


	//------------------------------------------------------
	// METHOD: pt2TestBasicArrayQuery
	// PURPOSE: tests basic 1D array
	//------------------------------------------------------
	@Test
	public void pt2TestBasicArrayQuery() {

		JSONQueryManager qm = JSONFactory.getJSONQueryManager();
		qm.loadJSON("{ \"arr\" : [ \"Ben\" , 7777777 , 1.1 , true ] }");

		Value val = qm.getJSONValue("arr");
		assertEquals("Query should return expected value", "[ \"Ben\" , 7777777 , 1.1 , true ]", val.toString());

		val = qm.getJSONValue("arr[0]");
		assertEquals("Query should return expected value", "\"Ben\"", val.toString());

		val = qm.getJSONValue("arr[1]");
		assertEquals("Query should return expected value", "7777777", val.toString());

		val = qm.getJSONValue("arr[2]");
		assertEquals("Query should return expected value", "1.1", val.toString());

		val = qm.getJSONValue("arr[3]");
		assertEquals("Query should return expected value", "true", val.toString());

		// out of bounds indices
		val = qm.getJSONValue("arr[-1]");
		assertEquals("Query should return expected value", null, val);

		val = qm.getJSONValue("arr[4]");
		assertEquals("Query should return expected value", null, val);
	}// end pt2TestBasicArrayQuery


	//------------------------------------------------------
	// METHOD: pt2Test2DArrayQuery
	// PURPOSE: tests a 2D array (Note: will work with any nD array)
	//------------------------------------------------------
	@Test
	public void pt2Test2DArrayQuery() {

		JSONQueryManager qm = JSONFactory.getJSONQueryManager();
		qm.loadJSON("{ \"arr\" : [ [ 00 , 01 , 02 ] , [ 10 , 11 , 12 ] , [ 20 , 21 , 22 ] ] }");
		
		Value val = qm.getJSONValue("arr[0][0]");
		assertEquals("Query should return expected value", "0", val.toString());

		val = qm.getJSONValue("arr[0][1]");
		assertEquals("Query should return expected value", "1", val.toString());
		
		val = qm.getJSONValue("arr[2][2]");
		assertEquals("Query should return expected value", "22", val.toString());
		
		val = qm.getJSONValue("arr[1][1]");
		assertEquals("Query should return expected value", "11", val.toString());
		
		val = qm.getJSONValue("arr[-1][0]");
		assertEquals("Query should return expected value", null, val);
		
		val = qm.getJSONValue("arr[3][0]");
		assertEquals("Query should return expected value", null, val);
		
		val = qm.getJSONValue("arr[2][3]");
		assertEquals("Query should return expected value", null, val);
	}// end pt2Test2DArrayQuery


	//------------------------------------------------------
	// METHOD: pt2Test3DArrayQuery
	// PURPOSE: tests a 3D array (Note: will work with any nD array)
	//------------------------------------------------------
	@Test
	public void pt2Test3DArrayQuery() {

		JSONQueryManager qm = JSONFactory.getJSONQueryManager();
		qm.loadJSON("{ \"arr\" : [ "
				+ "[ [ 000 , 001 , 002 ] , [ 010 , 011 , 012 ] , [ 020 , 021 , 022 ] ] , "
				+ "[ [ 100 , 101 , 102 ] , [ 110 , 111 , 112 ] , [ 120 , 121 , 122 ] ] , "
				+ "[ [ 200 , 201 , 202 ] , [ 210 , 211 , 212 ] , [ 220 , 221 , 222 ] ] ] }");
		
		Value val = qm.getJSONValue("arr[0][0][0]");
		assertEquals("Query should return expected value", "0", val.toString());

		val = qm.getJSONValue("arr[0][0][1]");
		assertEquals("Query should return expected value", "1", val.toString());

		val = qm.getJSONValue("arr[2][0][1]");
		assertEquals("Query should return expected value", "201", val.toString());

		val = qm.getJSONValue("arr[2][2][0]");
		assertEquals("Query should return expected value", "220", val.toString());

		val = qm.getJSONValue("arr[1][1][1]");
		assertEquals("Query should return expected value", "111", val.toString());

		val = qm.getJSONValue("arr[0][0][3]");
		assertEquals("Query should return expected value", null, val);

		val = qm.getJSONValue("arr[0][3][0]");
		assertEquals("Query should return expected value", null, val);

		val = qm.getJSONValue("arr[0][-1][0]");
		assertEquals("Query should return expected value", null, val);
	}// end pt2Test3DArrayQuery


	//------------------------------------------------------
	// METHOD: pt2TestBasicObjectQuery
	// PURPOSE: tests nested objects
	//------------------------------------------------------
	@Test
	public void pt2TestBasicObjectQuery() {

		JSONQueryManager qm = JSONFactory.getJSONQueryManager();
		qm.loadJSON("{ \"world\" : { \"name\" : \"EARTH\" , \"people\" : { "
				+ "\"person1\" : { \"name\" : \"Bill\" , \"age\" : 34 } , "
				+ "\"person2\" : { \"name\" : \"Jill\" , \"age\" : 23 } } } }");

		Value val = qm.getJSONValue("world.name");
		assertEquals("Query should return expected value", "\"EARTH\"", val.toString());

		val = qm.getJSONValue("world.people.person1.name");
		assertEquals("Query should return expected value", "\"Bill\"", val.toString());

		val = qm.getJSONValue("world.people.person1.age");
		assertEquals("Query should return expected value", "34", val.toString());

		val = qm.getJSONValue("world.people.person2.name");
		assertEquals("Query should return expected value", "\"Jill\"", val.toString());

		val = qm.getJSONValue("world.people.person2.age");
		assertEquals("Query should return expected value", "23", val.toString());
	}// end pt2TestBasicObjectQuery


	//------------------------------------------------------
	// METHOD: pt2TestCombinationQuery
	// PURPOSE: tests the given example JSON from instructions .pdf
	//------------------------------------------------------
	@Test
	public void pt2TestCombinationQuery() {

		JSONQueryManager qm = JSONFactory.getJSONQueryManager();
		qm.loadJSON("{ \"store\" : { \"book\" : [ "
				+ "{ \"category\" : \"reference\" , \"author\" : \"Nigel Rees\" , \"title\" : \"Sayings of the Century\" , \"price\" : 8.95 } , "
				+ "{ \"category\" : \"fiction\" , \"author\" : \"Evelyn Waugh\" , \"title\" : \"Sword of Honour\" , \"price\" : 12.99 } , "
				+ "{ \"category\" : \"fiction\" , \"author\" : \"Herman Melville\" , \"title\" : \"Moby Dick\" , \"isbn\" : \"0-553-21311-3\" , \"price\" : 8.99 } , ] , "
				+ "\"bicycle\" : { \"color\" : \"red\" , \"price\" : 19.95 } } }");

		Value val = qm.getJSONValue("store.book[0].category");
		assertEquals("Query should return expected value", "\"reference\"", val.toString());

		val = qm.getJSONValue("store.book[1].category");
		assertEquals("Query should return expected value", "\"fiction\"", val.toString());

		val = qm.getJSONValue("store.book[2].title");
		assertEquals("Query should return expected value", "\"Moby Dick\"", val.toString());

		val = qm.getJSONValue("store.bicycle.price");
		assertEquals("Query should return expected value", "19.95", val.toString());
	}// end pt2TestBasicArrayQuery

}// end JUnitTest class
