# Sorter
The Sorter class. 
Sorts an array of any primitive type, besides char and boolean, into ascending order. can also sort an array of comparable types into ascending order. Prints the first 100
elements of the sorted array, along with the time it took to sort the array in milliseconds. For example:

	Merge Sort:
	1 2 3 4 5 6 7 7 7 8 9 10 11 11 13 15 18 23 27
	That took 1.0 milliseconds!


The class uses five different methods to sort the array: 

Insertion Sort: works by comparing an element in the array with the previous element, 
	moving it backwards if the previous element is greater.

Bubble Sort: Works by comparing an element with the previous element, then swapping the two if they
	are out of order

Merge Sort: Works by using recursion to halve the array multiple times until the array is of size two.
	it then uses a different function, Merge, to merge those two numbers into ascending order.
	this smaller, now sorted array can be merged with another, similarly small and sorted array using 
	Merge (since merge requires that the two arrays which it is merging be in ascending order). This
	process continues, the sub-arrays getting bigger and bigger, until the first half and second half
	of the original array are in ascending order, ready to be merged with each other, creating the 
	full, sorted array.

Arrays.sort: The sort method contained in the java library. 

Bubble Sort or Merge Sort: Uses Bubble Sort if the array is of size 8 or less, Merge Sort otherwise.

Author: Laivi Malamut-Salvaggio
Version: 1.0
Date: 10/18/2015
