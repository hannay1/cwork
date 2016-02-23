//HANNAY AL-MOHANNA
#ifndef __LINK_HEDR // checks if header file is in use, any name willl do but each file has a different name
#define __LINK_HEDR// if it is, then #ifndef fails and headers aren't defined again

typedef struct link_node ll_node; //typedef of ll_node, which is a link_node struct
typedef ll_node *link; //typedef of a pointer of ll_node

struct link_node
{
	float data; //value of node
	link next; //pointer to next node
};

link ll_add_front(link ptr, float x); //dont have to include names in function, only types
link ll_rev(link ptr); 
link ll_rev_copy(link ptr);
void ll_free(link ptr);
void ll_print(link ptr);

#endif