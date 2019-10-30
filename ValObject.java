// CLASS: ValObject
// REMARKS: What is the purpose of this class?
//		To represent the JSONObject Value type
//-----------------------------------------
public class ValObject implements JSONObject {
	
	private LinkedList keyValPairs;
	
	// ValObject constructor
	public ValObject() {
		keyValPairs = new LinkedList();
	}// end constructor
	
	
	//------------------------------------------------------
	// METHOD: toString
	//
	// PURPOSE: State keyValPairs list as a String.
	// Returns: keyValPairs as String format.
	//------------------------------------------------------
	@Override
	public String toString() {
		
		String retStr = "{ ";
		IterObject iter = (IterObject) this.iterator();
		
		while (iter.hasNext()) {
			
			retStr += iter.getNext().toString();
			retStr += " : ";
			retStr += iter.getCurrVal().toString();
			
			if (iter.hasNext())
				retStr += " , ";
		}// end while
		
		retStr += " }";
		return retStr;
	}// end toString

	
	//------------------------------------------------------
	// METHOD: equals
	//
	// PURPOSE: ValObject comparison tool, 
	//			checks for same items (order does not matter)
	// PARAMETERS: Value (ValObject-expected) v -> value of other object
	// Returns: Boolean, true if equal
	//------------------------------------------------------
	@Override
	public boolean equals(Value v) {
		
		IterObject iter = null;
		Value thisKey = null;
		Value thisVal = null;
		Value thatVal = null;
		boolean isValid = false;
		
		if (v instanceof ValObject) {
			
			iter = (IterObject) this.iterator();
			
			// SPECIAL CASE: comparing two empty objects
			if (!iter.hasNext() && ((ValObject)v).isEmpty())
				isValid = true;
			
			else if (!iter.hasNext() && !((ValObject)v).isEmpty())
				isValid = false;
			
			else { // NORMAL-CASE
				isValid = true;
				while (iter.hasNext() && isValid) {
					
					thisKey = iter.getNext();
					thisVal = iter.getCurrVal();
					thatVal = ((ValObject)v).getValue(thisKey);
					
					if (thatVal == null || !thisVal.equals(thatVal)) 
						isValid = false;
				}// end while
			}// end else
		}
		else {
//			System.out.println("ERROR: equals(Value v) -> v parameter is not type ValObject");
			isValid = false;
		}// end else
		
		return isValid;
	}// end equals

	
	//------------------------------------------------------
	// METHOD: addKeyValue
	//
	// PURPOSE: inserts a Value into keyValPairs LinkedList, replacing duplicates
	// PARAMETERS:  Value key -> (ValString-expected)
	//				Value v -> anything that implements Value
	//------------------------------------------------------
	@Override
	public void addKeyValue(Value key, Value v) {
		
		IterObject iter = null;
		boolean duplicate = false;
		Value newPair = null;
		
		if (key instanceof ValString) {
			
			newPair = new KeyValuePair( (ValString)key, v);
			iter = (IterObject) this.iterator();
			
			while (iter.hasNext() && !duplicate) 
				duplicate = key.equals(iter.getNext());
		
			if (duplicate) 
				iter.replaceCurrVal(newPair);
			else 
				keyValPairs.insert(newPair);
		}
		else
			System.out.println("ERROR: key of object adding is not a ValString.");
	}// end addKeyValue
	
	
	//------------------------------------------------------
	// METHOD: getValue
	//
	// PURPOSE: Searches via object iterator to return Value
	//			corresponding to given key
	// PARAMETERS: Value (ValString-expected) key -> key of object
	// Returns: Value corresponding to given key
	//------------------------------------------------------
	@Override
	public Value getValue(Value key) {
		
		Boolean found = false;
		IterObject iter = (IterObject) this.iterator();
		Value keyInList = null;

		while (!found && iter.hasNext()) {
			
			keyInList = iter.getNext(); 

			if (key.equals(keyInList))
				found = true;
			else
				found = false;
		}// end while
	
		if (found)
			return ( iter.getCurrVal() );
		else {
//			System.out.println("ERROR: Key: \""+key.toString()+"\" not found.");
			return null;
		}// end else
	}// end getValue

	
	//------------------------------------------------------
	// METHOD: iterator
	//
	// PURPOSE: create a new instance of JSONObject specific iterator
	// Returns: JSONIter the array specific iterator: IterObject
	//------------------------------------------------------
	@Override
	public JSONIter iterator() {
		return (new IterObject(this.keyValPairs));
	}
	
	
	//------------------------------------------------------
	// METHOD: isEmpty
	//
	// PURPOSE: determines if keyValPairs list is empty via getFront()
	// Returns: Boolean, true if keyValPairs list is empty
	//------------------------------------------------------
	public boolean isEmpty() {
		return (keyValPairs.getFront() == null);
	}// isEmpty
	
}// end ValObject class
