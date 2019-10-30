// CLASS: IterObject
// REMARKS: What is the purpose of this class?
//		To represent the specific JSONIter for JSONObject
//-----------------------------------------
public class IterObject extends Iterator implements JSONIter { 
	//implements part not needed, just for clarity
	private Node prev;
	
	//IterObject constructor
	public IterObject(LinkedList objList) {
		super(objList);
		this.prev = null;
	}// end constructor
	
	
	//------------------------------------------------------
	// METHOD: getNext
	//
	// PURPOSE: retrieves the key of the next keyValuePair in the list
	// Returns: Value key (expected ValString instance)
	//------------------------------------------------------
	@Override
	public Value getNext() {
		Value key = null;
		
		if (curr != null) {
			key = ((KeyValuePair)curr.getVal()).getKey();
			prev = curr;
			curr = curr.getNext();
		}// end if 
		return key;
	}// end getNext
	
	
	//------------------------------------------------------
	// METHOD: getCurrVal
	//
	// PURPOSE: retrieves the actual value of the current keyValuePair in the list
	// Returns: Value (of any types: 
	//			ValString, ValInteger, ValDouble, ValBoolean, ValArray, ValObject)
	//------------------------------------------------------
	public Value getCurrVal() {
		if (prev != null)
			return ( ((KeyValuePair)prev.getVal()).getVal() );
		else
			return null;
	}// end getCurrVal
	
	
	//------------------------------------------------------
	// METHOD: replaceCurrVal
	//
	// PURPOSE: replaces the current Node's val with v
	// PARAMETERS: Value v -> object to replace inside Node.
	//------------------------------------------------------
	public void replaceCurrVal(Value v) {
		prev.setVal(v);
	}// end replaceCurrVal

}// end IterObject class
