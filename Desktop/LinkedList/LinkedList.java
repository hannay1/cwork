public class LinkedList
{
	public Node node1; //first link added to list
	public Node node2; //last link added to list

	public LinkedList()
	{
		node1 = null; //start with an empty list
	}

	public boolean isEmpty()
	{
		return(node1 == null); //"is first node empty?" 
	}


	///INSERT IN FIRST POSITION
	public void insert_node_first(String animal_name)
	{
		Node newNode = new Node(animal_name); //make new node

		if(isEmpty())
		{
			node2 = newNode;
		}else
		{
			node1.prev = newNode;
		}

		

		newNode.next = node1; //next node to the new node is the first link...
		node1 = newNode; //the first node in the list is now the new node (converse of above)

		// so the next node to the new node is the first link,
		//the first link is now the new node
	}

	// INSERT IN LAST POSITION
	public void insert_node_last(String animal_name)
	{
		Node newNode = new Node(animal_name);//make a new node

		if(isEmpty()) //if the first node IS empty..
		{
			node1 = newNode; //the first node is the new node
		}
		else
		{
			node2.next = newNode; ///the next node after the last node added is the new node...
			newNode.prev = node2; //...and the previous node from the newNode is the last added node (converse of above)
		}
		node2 = newNode;
	}

	//INSERT NAME AFTER SOME NAME
	public boolean insert_at_pos(String animal_name, String other_name) 
	{ //INSERT AT NTH POS IN DOUBLY LINKED LIST
		Node newNode = new Node(animal_name); //newNode is a new Node that takes animal names
		Node p = node1; //p = current node is the first node in the list (temp)


		if (isEmpty()) //if the first node in the list is empty
		{
			node2 = newNode;//last node is the new node
		node1 = newNode; //first node is the new node;

		}

		while( !(p.animal_name).equals(other_name)) //while the animal name is not the same as the supplied name
		{
			p = p.next; //current node is the next node
			if(p == null) //if end of list, return false
			{
				return false;
			}
		}

		if(p == node2) //if p = the last node in the list
		{
			newNode.next = null; //the ref for newNode will be the end of the list (newNode is last link)
			node2 = newNode; //the last link is the newNode;
		}
		else
		{
			newNode.next = p.next; //the next ref for newNode is the node after the current node...
			p.next.prev = newNode; //...the previous ref of the next node to the current node is the new node
			// current node is new node, p dissapears
		}
		newNode.prev = p; //previous ref for newNode is the current marker...
		p.next = newNode; //and the next ref of the current node is the new node (converse of above)
		return true; //node was added after some node
	}

	public Node removeFirst()
	{
		Node nodeRef = node1; //node is the first node in the list...

		if(!isEmpty()) ///if firstnode isnt empty
		{
			node1 = node1.next; //the first node is the next node, and the first dissapears poof
		}
		else
		{
			System.out.println("linked list is empty");
		}

		return nodeRef;

	}

	public void display()
	{
		Node theNode = node1; //node is the first node

		while(theNode != null) //while the first node isnt null
		{
			
			System.out.println("animal_name is " + theNode.animal_name); 

			System.out.println("Next Link: " + theNode.next + "\n");
			theNode = theNode.next;
			
		}

	}

	public Node find(String animal_name)
	{ //find some node given some name
		Node theNode = node1; //first node

		if(!isEmpty())//go throuh list..if first node is not empty...
		{
			while(!(theNode.animal_name.equals(animal_name))) //while the animal name of the first node is not the supplied animal name... 
			{
				if(theNode.next == null) //if the node after the first node is null (end of list)
				{
					System.out.println("could not find the name in the list");
					return null; //node is empty
				}
				else
				{
					theNode = theNode.next;//the first node is the next node (i.e we keep looking)...

				}

			}
	
		}
		else //if we couldnt find the name in any of the nodes...
		{
			System.out.println("empty linked list");
		}
		return theNode; //return the found (or null) node
	}

	
	public Node removeLink(String animal_name)
	{ //to remove, assign 
		Node currentNode = node1; //current node is the first node
		Node prevNode = node1; //previous node is also the first node 
		//therefore we are starting at the first node....

		while(!(currentNode.animal_name.equals(animal_name))) //while the animal name of the first node is not the supplied animal name...
		{
			if(currentNode.next == null) //if the next ref of the current node is the end of the list, 
			{
				return null; // remove nothing
			}
			else
			{
				prevNode = currentNode; //previous node is the current node
				currentNode = currentNode.next; //current node is the next node to the previous current node (skipping over the name we want to delete)
			}
		}


		if(currentNode == node1) //if the current node is the first node
		{
			node1 = node1.next; //the first node is the next
		}
		else //else if the current node is not the first node...
		{
			prevNode.next = currentNode.next; // the next previous and the next next will be the next node (move down the list)
			
		}
		return currentNode;
	}




	public Node toLeft(String animal_name)
	{

		Node c = find(animal_name); //current node
		Node n = find(animal_name); //next node
		
		if(c.next != null)
		{

			c = n;
			n = n.next;
			System.out.println("next node is " + n.animal_name + " and current node is " + c.animal_name);
			return n;

		}else
		{
			System.out.println("no next node");
		}
			
		return null;
	}

	

	public Node toRight(String animal_name)
	{

		Node c = find(animal_name); //current node
		Node p = find(animal_name); //prev node
		
		if(c.prev != null)
		{
			//System.out.println("prev is not null");

			c = p;
			p = p.prev;
			System.out.println("prev node is " + p.animal_name + " and current node is " + c.animal_name);
			return p;

		}else
		{
			System.out.println("no previous node");

		}
			
		return null;
	}




	




}