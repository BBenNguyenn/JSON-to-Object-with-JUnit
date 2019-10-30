// CLASS: ValDouble
// REMARKS: To represent the Double Value type
//-----------------------------------------
public class ValDouble implements Value {
	
	private double val;
	
	//ValDouble constructor
	public ValDouble(double d) {
		this.val = d;
	}// end constructor
	
	
	//------------------------------------------------------
	// METHOD: toString
	//
	// PURPOSE: State val as a String.
	// Returns: val as String format.
	//------------------------------------------------------
	@Override
	public String toString() {
		return (Double.toString(this.val));
	}// end toString
	
	
	//------------------------------------------------------
	// METHOD: equals
	//
	// PURPOSE: ValDouble comparison tool
	// PARAMETERS: Value (ValDouble-expected) v -> value of other object
	// Returns: Boolean, true if equal
	//------------------------------------------------------
	@Override
	public boolean equals(Value v) {
		
		if (v instanceof ValDouble)
			return ( val == Double.parseDouble(v.toString()) );
		else {
//			System.out.println("ERROR: equals(Value v) -> v parameter is not type ValDouble");
			return false;
		}// end else
	}// end equals
	
}// end class ValDouble
