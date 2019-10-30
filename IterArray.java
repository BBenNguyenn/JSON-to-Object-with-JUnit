// CLASS: IterArray
// REMARKS: What is the purpose of this class?
//		To represent the specific JSONIter for JSONArray
//-----------------------------------------
public class IterArray extends Iterator implements JSONIter {
	//implements part not needed, just for clarity
	//IterArray constructor
	public IterArray(LinkedList valList) {
		super(valList);
	}// end constructor

	
	//------------------------------------------------------
	// METHOD: getNext
	//
	// PURPOSE: retrieves the actual value of the current Value in the list
	// Returns: Value (of any types: 
	//			ValString, ValInteger, ValDouble, ValBoolean, ValArray, ValObject)
	//------------------------------------------------------
	@Override
	public Value getNext() {
		Value key = null;
		
		if (curr != null) {
			key = curr.getVal();
			curr = curr.getNext();
		}// end if 
		return key;
	}// end getNext

}// end IterArray class
