//HANNAY AL-MOHANNA
#include <stdio.h>
#include <stdlib.h>
#include <linked_list.h>

link ll_add_front(link ptr, float x)
{//ptr ~~ points to head of list
	if(!ptr)
	{
		ptr = (link)malloc(sizeof(*ptr)); 
		//returns a pointer to chunk of memory in heap (of size of pointer)
		ptr->data = x; //assign data
		ptr->next= NULL; //next is null as list is only 1 entry long
		return ptr;
	}else
	{
		link new;
		new = (link)malloc(sizeof(*ptr)); //allocate new memory for a new node
		new->data = x; 
		new->next = ptr; //next node is the previous first node that was pointed to
		return new;	
	}
}

link ll_rev(link ptr)
{
	printf("reversing linked list...\n");
	link start = NULL; //start of our "new" linked list,
	link end; //points to null at end of while loop
	while(ptr != NULL) 
	{
		end = start; //pass off new start to end
		start = ptr; //start of "new" list is the head pointer
		ptr = ptr->next; //advance pointer,
		start->next = end; // after the start is null
		//now start will point to last in ptr, which is beginning of our "new" list
		//current ptr is the next address of the last ptr
	}
	printf("ll_rev to return pointer to node [%f]\n\n",start->data);
	return start;
}

link ll_rev_copy(link ptr)
{
	printf("making reverse copy of linked list...\n");
	link temp = NULL; //will point to a copy of the last entry of parsed linked list
	while(ptr != NULL) 
	{
		temp = ll_add_front(temp, ptr->data);//made a new node with the value of the last node
		ptr = ptr->next; //advance through original list
	}
	printf("head of copied list will be: [%f]\n\n", temp->data );
	return temp; //return start of the copied list
}


void ll_free(link ptr)
{
	if(!ptr) //checks if head is empty
	{
		return;
	}
	ll_free(ptr->next); //else recurse down list
	free(ptr); //and free current head pointer
}

void ll_print(link ptr)
{
	printf("\n");
	if(!ptr) //if there is no pointer, list is empty
	{
		printf("[empty list]\n");
	}
	else
	{
		while(ptr != NULL) //go thru list
		{
			printf("%f", ptr->data);
			printf(" -> ");
			ptr = ptr->next; //advance pointer
			if(ptr == NULL)
			{
				printf("~n"); //to signify terminal end
			}
		}
		printf("\n\n");
	}	
}






