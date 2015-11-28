public class Node {
	public String animal_name;
	public Node next;
	public Node prev;

	public Node(String animal_name)
	{ //constructor

		
		this.animal_name = animal_name;
		this.next = next;
		this.prev = prev;
	}

	
	public String toString()
	{
		return animal_name;
	}
}
