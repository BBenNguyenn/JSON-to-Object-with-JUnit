// CLASS: ValBoolean
// REMARKS: To represent the Boolean Value type
//-----------------------------------------
public class ValBoolean implements Value {
	
	private boolean val;
	
	//ValBoolean constructor
	public ValBoolean(boolean b) {
		this.val = b;
	}// end constructor
	
	
	//------------------------------------------------------
	// METHOD: toString
	//
	// PURPOSE: State val as a String.
	// Returns: val as String format.
	//------------------------------------------------------
	@Override
	public String toString() {
		return (String.valueOf(val));
	}// end toString
	
	
	//------------------------------------------------------
	// METHOD: equals
	//
	// PURPOSE: ValBoolean comparison tool
	// PARAMETERS: Value (ValBoolean-expected) v -> value of other object
	// Returns: Boolean, true if equal
	//------------------------------------------------------
	@Override
	public boolean equals(Value v) {
		
		if (v instanceof ValBoolean)
			return ( val == Boolean.parseBoolean(v.toString()) );
		else {
//			System.out.println("ERROR: equals(Value v) -> v parameter is not type ValBoolean");
			return false;
		}// end else
	}// end equals
	
}// end ValBoolean class
