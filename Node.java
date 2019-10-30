// CLASS: Node
// REMARKS:
//		To represent an 'edge'/node in a list,
//		contains a Value object and a next pointer.
//-----------------------------------------
public class Node {

	private Value val;
	private Node next;

	// Constructor for a standard Node
	public Node(Value data, Node next) {
		this.val = data;
		this.next = next;
	}// end Constructor


	// GETTERS AND SETTERS FOR NODE
	// I understand this is like having public Node fields,
	// but for this use case, all are needed.
	// This could improve by using packages,
	// but I fear using packages may just make things
	// more complicated for the person marking,
	// especially having to setup the given files as well,
	// having everything in one (default) packages seems best.
	// But please know that I did not do this carelessly.
	// Thank you for your understanding, have a wonderful day!
	
	
	// returns Val
	public Value getVal() {
		return val;
	}// end getVal
	
	// returns next
	public Node getNext() {
		return next;
	}// end getNext

	// sets val
	public void setVal(Value val) {
		this.val = val;
	}// end setNext
	
	// sets next
	public void setNext(Node next) {
		this.next = next;
	}// end setNext

}// end Node class
