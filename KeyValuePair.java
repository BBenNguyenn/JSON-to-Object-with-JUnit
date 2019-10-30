// CLASS: KeyValuePair
// REMARKS: What is the purpose of this class?
//		To represent a single KeyValuePair object which
//		is what a JSONObject consists list of.
//-----------------------------------------
public class KeyValuePair implements Value {

	private ValString key;
	private Value val;

	//KeyValuePair constructor
	public KeyValuePair(ValString k, Value v) {
		this.key = k;
		this.val = v;
	}// end constructor

	
	//------------------------------------------------------
	// METHOD: toString
	//
	// PURPOSE: State key as a String instead of ValString type.
	// Returns: key in String format.
	//------------------------------------------------------
	@Override
	public String toString() {
		return (this.key.toString());
	}// end toString

	
	//------------------------------------------------------
	// METHOD: equals
	//
	// PURPOSE: KeyValuePair comparison tool
	// PARAMETERS: Value (KeyValuePair-expected) v -> value of other object
	// Returns: Boolean, true if equal
	//------------------------------------------------------
	@Override
	public boolean equals(Value v) {

		if (v instanceof KeyValuePair)
			return ( this.key.equals(((KeyValuePair)v).getKey()) 
				  && this.val.equals(((KeyValuePair)v).getVal()) );
		else {
			System.out.println("ERROR: equals(Value v) -> v parameter is not type KeyValuePair");
			return false;
		}// end else
	}// end equals


	//GETTERS for key and val
	public ValString getKey() { return this.key; }
	public Value getVal() { return this.val; }

}// end KeyValuePair class
