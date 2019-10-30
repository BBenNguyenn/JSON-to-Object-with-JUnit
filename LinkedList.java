// CLASS: LinkedList
// REMARKS: What is the purpose of this class?
//		To form a singly-linked list with a
//		simple head Node pointer and an addFront
//		and search methods.
//-----------------------------------------
public class LinkedList {

	private Node front;
	private Node end;


	// Constructor for standard linked-list with both end pointers.
	public LinkedList() {
		front = null;
		end = null;
	}// end constructor


	//------------------------------------------------------
	// METHOD: insert
	//
	// PURPOSE: Adds a Value object to the end of the list.
	// PARAMETERS: Value data -> Value object to add to list
	//------------------------------------------------------
	public void insert( Value val ) {

		Node newNode = new Node(val, null);

		if (front == null) {
			front = newNode;
			end = front;
		}
		else if (end != null) {
			end.setNext(newNode);
			end = newNode;
		}
		else
			System.out.println("ERROR: front is null but end is not.");
	}// end insert

	
	//GETTER for front, used to determine if list is empty
	public Node getFront() {
		return this.front;
	}// end getFront

}// end List class
