
public class Controller
{
	//list all names (display)
	//find name (find)
	//insert name at position 
	//insert name at beginning
	//insert name at end

	//TO DO
	//Fint next
	//find prev
	public static void main(String[] args) {

		
		LinkedList theLinkedList = new LinkedList();
		
		// Insert Link and add a reference to the book Link added just prior
		// to the field next
		
		theLinkedList.insert_node_first("dog");
		theLinkedList.insert_node_first("cat");
		theLinkedList.insert_node_first("'boon");
		theLinkedList.insert_node_first("leeemur");
		
		theLinkedList.insert_node_last("last");
		
		//theLinkedList.display();
		
		System.out.println("Value of first in LinkedList " + theLinkedList.node1 + "\n");
		System.out.println("Value of last in LinkedList " + theLinkedList.node2 + "\n");
		
		// Removes the last Link entered
		
		//theLinkedList.removeFirst();
		
		//theLinkedList.display();
		
		//theLinkedList.insert_node_last("fishh");

		//theLinkedList.display();



		System.out.println(theLinkedList.find("dog") + " Was Found");
		
		//theLinkedList.removeLink("cat");
		
		
		//theLinkedList.display();

		theLinkedList.insert_at_pos("test", "dog");
		theLinkedList.insert_at_pos("test", "leeeee");


		//theLinkedList.display();

		theLinkedList.toLeft("cat");
		theLinkedList.toLeft("test");
		theLinkedList.toLeft("dog");
		theLinkedList.toLeft("leeemur");
		theLinkedList.toLeft("last");

		theLinkedList.toRight("test");
		theLinkedList.toRight("cat");
		theLinkedList.toRight("last");
		theLinkedList.toRight("leeemur");



		theLinkedList.display();


		
	}

}

