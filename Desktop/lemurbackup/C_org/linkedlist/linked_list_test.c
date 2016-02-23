#include <stdlib.h>
#include <stdio.h>
#include <linked_list.h>


int test1()
{
	link ptr = NULL; //initially pointer is null as list is empty
	link reverse_copy = NULL;
	ptr = ll_add_front(ptr, 5.0);
	ptr = ll_add_front(ptr, 0.2);
	ptr = ll_add_front(ptr, 3.1);
	ll_print(ptr);
	ptr = ll_rev(ptr);
	ll_print(ptr);
	ptr = ll_rev(ptr);
	printf("list is now back in original order...\n");
	ll_print(ptr);
	reverse_copy = ll_rev_copy(ptr);
	printf("reversed copy of linked list:\n");
	ll_print(reverse_copy);
	ll_free(ptr); //free memory after we're done with linked list
	ll_free(reverse_copy); //ditto for reverse copy
	return 0;
}

int test2()
{
	link ptr = NULL; //initially pointer is null as list is empty
	link reverse_copy = NULL;
	ptr = ll_add_front(ptr, 8.0);
	ptr = ll_add_front(ptr, 9.2);
	ptr = ll_add_front(ptr, 5.7);
	ptr = ll_add_front(ptr, 8.9);
	ptr = ll_add_front(ptr, 7.1);
	ptr = ll_add_front(ptr, 4.3);
	ll_print(ptr);
	ptr = ll_rev(ptr);
	ll_print(ptr);
	ptr = ll_rev(ptr);
	printf("list is now back in original order...\n");
	ll_print(ptr);
	reverse_copy = ll_rev_copy(ptr);
	printf("reversed copy of linked list:\n");
	ll_print(reverse_copy);
	ll_free(ptr); //free memory after we're done with linked list
	ll_free(reverse_copy); //ditto for reverse copy
	//printf("%p", ptr->data); ///prints a pointer
	return 0;
}

int main(int argc, char **argv)
{
	printf("***************TEST 1***************");
	test1();
	printf("***************TEST 2***************");
	test2();
	return 0;
}



/*

TO COMPILE linked_list.o: make compile
TO COMPILE & RUN test file: make test
TO CLEAN UP COMPILED FILES: make clean
*/