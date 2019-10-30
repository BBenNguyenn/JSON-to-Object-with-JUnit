// CLASS: ValInteger
// REMARKS: To represent the Integer Value type
//-----------------------------------------
public class ValInteger implements Value {
	
	private int val;
	
	//ValInteger constructor
	public ValInteger(Integer i) {
		this.val = i;
	}// end constructor
	
	
	//------------------------------------------------------
	// METHOD: toString
	//
	// PURPOSE: State val as a String.
	// Returns: val as String format.
	//------------------------------------------------------
	@Override
	public String toString() {
		return (Integer.toString(this.val));
	}// end toString
	

	//------------------------------------------------------
	// METHOD: equals
	//
	// PURPOSE: ValInteger comparison tool
	// PARAMETERS: Value (ValInteger-expected) v -> value of other object
	// Returns: Boolean, true if equal
	//------------------------------------------------------
	@Override
	public boolean equals(Value v) {
		
		if (v instanceof ValInteger)
			return ( val == Integer.parseInt(v.toString()) );
		else {
//			System.out.println("ERROR: equals(Value v) -> v parameter is not type ValInteger");
			return false;
		}// end else
	}// end equals
	
}// end ValInteger class
