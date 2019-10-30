// CLASS: ValString
// REMARKS: To represent the String Value type
//-----------------------------------------
public class ValString implements Value {

	private String val;
	
	//ValString constructor
	public ValString(String s) {
		this.val = s;
	}// end constructor

	
	//------------------------------------------------------
	// METHOD: toString
	//
	// PURPOSE: State val as a String.
	// Returns: val as String format.
	//------------------------------------------------------
	@Override
	public String toString() {
		return (this.val);
	}// end toString

	
	//------------------------------------------------------
	// METHOD: equals
	//
	// PURPOSE: ValString comparison tool
	// PARAMETERS: Value (ValString-expected) v -> value of other object
	// Returns: Boolean, true if equal
	//------------------------------------------------------
	@Override
	public boolean equals(Value v) {

		if (v instanceof ValString)
			return (val.equals(((ValString)v).toString()));
		else {
//			System.out.println("ERROR: equals(Value v) -> v parameter is not type ValString but type: "+v.getClass().getName());
			return false;
		}// end else
	}// end equals
	
}// end ValString class
