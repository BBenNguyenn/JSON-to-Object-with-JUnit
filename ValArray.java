// CLASS: ValArray
// REMARKS: What is the purpose of this class?
//		To represent the JSONArray Value type
//-----------------------------------------
public class ValArray implements JSONArray {

	private LinkedList arrayValues;

	//ValArray constructor
	public ValArray() {
		arrayValues = new LinkedList();
	}// end constructor

	
	//------------------------------------------------------
	// METHOD: toString
	//
	// PURPOSE: State arrayValues list as a String.
	// Returns: arrayValues as String format.
	//------------------------------------------------------
	@Override
	public String toString() {
		
		String retStr = "[ ";
		IterArray iter = (IterArray) this.iterator();

		while (iter.hasNext()) {
			retStr += iter.getNext().toString();
			if (iter.hasNext())
				retStr += " , ";
		}// end while
		retStr += " ]";
		return retStr;
	}// end toString

	
	//------------------------------------------------------
	// METHOD: equals
	//
	// PURPOSE: ValArray comparison tool, checks for same items in same order
	// PARAMETERS: Value (ValArray-expected) v -> value of other object
	// Returns: Boolean, true if equal
	//------------------------------------------------------
	@Override
	public boolean equals(Value v) {
		
		IterArray thisIter = null;
		IterArray thatIter = null;
		Value thisVal = null;
		Value thatVal = null;
		boolean sameVal = true;
		
		if (v instanceof ValArray) {
			thisIter = (IterArray) this.iterator();
			thatIter = (IterArray) ((ValArray) v).iterator();
			
			while (thisIter.hasNext() && sameVal) {
				
				thisVal = thisIter.getNext();
				thatVal = thatIter.getNext();
				
				sameVal = thisVal.equals(thatVal);
			}// end while
			
			return sameVal;
		}
		else {
//			System.out.println("ERROR: equals(Value v) -> v parameter is not type ValArray but type: "+v.getClass().getName());
			return false;
		}// end else
	}// end equals

	
	//------------------------------------------------------
	// METHOD: addValue
	//
	// PURPOSE: inserts a Value into arrayValues LinkedList 
	// PARAMETERS: Value v -> anything that implements Value
	//------------------------------------------------------
	@Override
	public void addValue(Value v) {
		arrayValues.insert(v);
	}// end addValue

	
	//------------------------------------------------------
	// METHOD: iterator
	//
	// PURPOSE: create a new instance of JSONArray specific iterator
	// Returns: JSONIter the array specific iterator: IterArray
	//------------------------------------------------------
	@Override
	public JSONIter iterator() {
		return ( new IterArray(this.arrayValues) );
	}// end iterator

}// end ValArray class
