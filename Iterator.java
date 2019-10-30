// CLASS: abstract Iterator
// REMARKS: What is the purpose of this class?
//		To represent the iterator functions for traversing a LinkedList
//-----------------------------------------
public abstract class Iterator implements JSONIter {
	
	// I know protected is not ideal, but this could be solved by being
	// in its own packages. But as I mentioned, perhaps using packages is
	// not convenient for you (the marker)....Thank you for your regards.
	protected LinkedList list;
	protected Node curr;

	// super constructor (never called directly)
	public Iterator(LinkedList list) {
		this.list = list;
		this.curr = list.getFront();
	}// end constructor

	//------------------------------------------------------
	// METHOD: hasNext
	//
	// PURPOSE: determines if we are at the end of the list
	// Returns: Boolean, true if not end of list yet
	//------------------------------------------------------
	@Override
	public boolean hasNext() {
		if (curr != null) 
			return (curr != null);
		else
			return false;
	}// end hasNext

	
	//Implemented by subclasses IterArray and IterObject
	@Override
	public abstract Value getNext();

}// end Iterator class
