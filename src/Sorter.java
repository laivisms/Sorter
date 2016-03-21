
import java.util.Random;
import java.util.Arrays;

/**
 * The Sorter class. Sorts an array of all primitive types, besides char and boolean, 
 * into ascending order. Also sorts comparable types into ascending order. Prints the first 100
 * elements of the sorted array, along with the time it took to sort the array in milliseconds.
 * Uses five methods to sort the array: 
 * 
 * Insertion Sort: works by comparing an element in the array with the previous element, 
 * moving it backwards if the previous element is greater.
 * 
 * Bubble Sort: Works by comparing an element with the previous element, then swapping the two if they
 * are out of order
 * 
 * Merge Sort: Works by using recursion to halve the array multiple times until the array is of size two.
 * it then uses a different function, Merge, to merge those two numbers into ascending order.
 * this smaller, now sorted array can be merged with another, similarly small and sorted array using 
 * Merge (since merge requires that the two arrays which it is merging be in ascending order). This
 * process continues, the sub-arrays getting bigger and bigger, until the first half and second half
 * of the original array are in ascending order, ready to be merged with each other, creating the 
 * full, sorted array. 
 * 
 * Arrays.sort: The sort method contained in the java library. 
 * 
 * Bubble Sort or Merge Sort: Uses Bubble Sort if the array is of size 8 or less, Merge Sort otherwise.
 * 
 * @author Laivi Malamut-Salvaggio
 *
 */
public class Sorter {
	private static int[] unsortedInt;
	
	
	public static void main (String args[]) {
		Random rand = new Random();
		int data[] = new int[10000];
		for(int i = 0; i<10000; i++){
			data[i] = rand.nextInt(1000000);
		}
		
		unsortedInt = data.clone();
		
		System.out.println("Original, unsorted array:");
		print(unsortedInt);
		System.out.println(" ");
		System.out.println(" ");
		
		//Insertion Sort:
		double startTime = System.currentTimeMillis();
		insertionSort(unsortedInt);
		double endTime = System.currentTimeMillis();
		System.out.println("Insertion Sort: ");
		print(unsortedInt);
		System.out.println("");
		System.out.println("That took " + (endTime - startTime) + " milliseconds!");
		System.out.println("");
		unsortedInt = data.clone();
		
		//Bubble Sort:
		startTime = System.currentTimeMillis();
		bubbleSort(unsortedInt);
		endTime = System.currentTimeMillis();
		System.out.println("Bubble Sort: ");
		print(unsortedInt);
		System.out.println("");
		System.out.println("That took " + (endTime - startTime) + " milliseconds!");
		System.out.println("");
		unsortedInt = data.clone();
		
		//Merge Sort:
		startTime = System.currentTimeMillis();
		mergeSort(unsortedInt, 0, unsortedInt.length - 1);
		endTime = System.currentTimeMillis();
		System.out.println("Merge Sort: ");
		print(unsortedInt);
		System.out.println("");
		System.out.println("That took " + (endTime - startTime) + " milliseconds!");
		System.out.println("");
		unsortedInt = data.clone();
		
        // Arrays.sort:
		startTime = System.currentTimeMillis();
		Arrays.sort(unsortedInt);
		endTime = System.currentTimeMillis();
		System.out.println("Array Sort: ");
		print(unsortedInt);
		System.out.println("");
		System.out.println("That took " + (endTime - startTime) + " milliseconds!");
		System.out.println("");
		unsortedInt = data.clone();
		
		//Bubble Sort if <= 8, Merge Sort otherwise:
		if(unsortedInt.length <= 8){
			startTime = System.currentTimeMillis();
			bubbleSort(unsortedInt);
			endTime = System.currentTimeMillis();
		}
		else{
			startTime = System.currentTimeMillis();
			mergeSort(unsortedInt, 0, unsortedInt.length - 1);
			endTime = System.currentTimeMillis();
		}
		System.out.println("Bubble Sort if <=8 and Merge Sort otherwise: ");
		print(unsortedInt);
		System.out.println("");
		System.out.println("That took " + (endTime - startTime) + " milliseconds!");
		System.out.println("");
		
	}
	
	private static void insertionSort(int[] unsorted){
		for (int j = 1; j < unsorted.length; j++){
			int key = unsorted[j]; // the number being sorted
			int i = j-1;
			while(i>=0 && unsorted[i] > key){ // if the previous number is greater than the key
				unsorted[i+1] = unsorted[i]; // then move the previous number up one
				i = i-1;
			}
			unsorted[i+1] = key;
		}
	}
	
	private static void bubbleSort(int[] unsorted){
		for (int i=0; i<unsorted.length; i++) { // iterate over the entire array as many times as there are elements
			for (int j = unsorted.length - 1; j>i; j--){ // start from the end of the array, move downward
				if (unsorted[j]<unsorted[j-1]){
					int a = unsorted[j];
					unsorted[j] = unsorted[j-1];
					unsorted[j-1] = a;
				}
			}
		}
		
	}
	private static void merge(int[] unsorted, int beg, int mid, int end) {
		int leftArrayLength = mid - beg + 1; 
		int rightArrayLength = end - mid;
		int left[] = new int[leftArrayLength + 1];// left sorted array to be merged
		int right[] = new int[rightArrayLength + 1];// right sorted array to be merged
		for (int i = 0; i<leftArrayLength; i++){// copy the lefthand elements into the new left array
			left[i]=unsorted[beg + i];
		}
		for (int j = 0; j<rightArrayLength; j++){// copy the righthand elements into the new right array
			right[j] = unsorted[mid + j + 1];
		}
		left[leftArrayLength] = 2147483647; // sentinel number, reached last index
		right[rightArrayLength] = 2147483647;
		int leftIndex = 0;
		int rightIndex = 0;
		for(int k = beg; k <= end; k++){ // iterate through left and right array, taking greater number
			if (left[leftIndex] <= right[rightIndex]){
				unsorted[k] = left[leftIndex];
				leftIndex++;
			}
			else{
				unsorted[k] = right[rightIndex];
				rightIndex++;
			}
		}
	}
	
	
	private static void mergeSort(int[] unsorted, int beg, int end){
		if (beg < end){
			int mid = 0;
			if (end - beg == 1){ // the array to be sorted is of length 2, make middle == beginning
				mid = beg;
			}
			else if ( (end + beg)%2 == 1){ // array length is odd, subtract one then divide
				mid = (end + beg - 1) / 2;
			}
			else{
				mid = (end + beg) / 2;
			}
			mergeSort(unsorted, beg, mid); //use recursion to make the array continuously smaller
			mergeSort(unsorted, mid+1, end);
			merge(unsorted, beg, mid, end);// sort the smaller arrays, then combine them and sort those as well
		}
		
	}
	private static void print(int[] sorted){
		int p = 0;
		for(int i = 0; i<100; i++) {// print out first 100 elements of array
			if (p<20){
			     System.out.print(sorted[i] + " ");
			     p++;
			}
			else{
				System.out.println(sorted[i] + " "); //print to new line after 20 elements, makes output look nicer
				p = 0;
			}
		}
	}
	private static void insertionSort(byte[] unsorted){
		for (int j = 1; j < unsorted.length; j++){
			byte key = unsorted[j];
			int i = j-1;
			while(i>=0 && unsorted[i] > key){
				unsorted[i+1] = unsorted[i];
				i = i-1;
			}
			unsorted[i+1] = key;
		}
	}
	
	private static void bubbleSort(byte[] unsorted){
		for (int i=0; i<unsorted.length; i++) {
			for (int j = unsorted.length - 1; j>i; j--){
				if (unsorted[j]<unsorted[j-1]){
					byte a = unsorted[j];
					unsorted[j] = unsorted[j-1];
					unsorted[j-1] = a;
				}
			}
		}
		
	}
	private static void merge(byte[] unsorted, int beg, int mid, int end) {
		int leftArrayLength = mid - beg + 1;
		int rightArrayLength = end - mid;
		byte left[] = new byte[leftArrayLength + 1];
		byte right[] = new byte[rightArrayLength + 1];
		for (int i = 0; i<leftArrayLength; i++){
			left[i]=unsorted[beg + i];
		}
		for (int j = 0; j<rightArrayLength; j++){
			right[j] = unsorted[mid + j + 1];
		}
		left[leftArrayLength] = 127;
		right[rightArrayLength] = 127;
		int leftIndex = 0;
		int rightIndex = 0;
		for(int k = beg; k <= end; k++){
			if (left[leftIndex] <= right[rightIndex]){
				unsorted[k] = left[leftIndex];
				leftIndex++;
			}
			else{
				unsorted[k] = right[rightIndex];
				rightIndex++;
			}
		}
	}
	
	
	private static void mergeSort(byte[] unsorted, int beg, int end){
		if (beg < end){
			int mid = 0;
			if (end - beg == 1){
				mid = beg;
			}
			else if ( (end + beg)%2 == 1){
				mid = (end + beg - 1) / 2;
			}
			else{
				mid = (end + beg) / 2;
			}
			mergeSort(unsorted, beg, mid);
			mergeSort(unsorted, mid+1, end);
			merge(unsorted, beg, mid, end);
		}
		
	}
	
	private static void print(byte[] sorted){
		int p = 0;
		for(int i = 0; i<100; i++) {
			if (p<20){
			     System.out.print(sorted[i] + " ");
			     p++;
			}
			else{
				System.out.println(sorted[i] + " ");
				p = 0;
			}
		}
	}
	private static void insertionSort(short[] unsorted){
		for (int j = 1; j < unsorted.length; j++){
			short key = unsorted[j];
			int i = j-1;
			while(i>=0 && unsorted[i] > key){
				unsorted[i+1] = unsorted[i];
				i = i-1;
			}
			unsorted[i+1] = key;
		}
	}
	
	private static void bubbleSort(short[] unsorted){
		for (int i=0; i<unsorted.length; i++) {
			for (int j = unsorted.length - 1; j>i; j--){
				if (unsorted[j]<unsorted[j-1]){
					short a = unsorted[j];
					unsorted[j] = unsorted[j-1];
					unsorted[j-1] = a;
				}
			}
		}
		
	}
	private static void merge(short[] unsorted, int beg, int mid, int end) {
		int leftArrayLength = mid - beg + 1;
		int rightArrayLength = end - mid;
		short left[] = new short[leftArrayLength + 1];
		short right[] = new short[rightArrayLength + 1];
		for (int i = 0; i<leftArrayLength; i++){
			left[i]=unsorted[beg + i];
		}
		for (int j = 0; j<rightArrayLength; j++){
			right[j] = unsorted[mid + j + 1];
		}
		left[leftArrayLength] = 32767;
		right[rightArrayLength] = 32767;
		int leftIndex = 0;
		int rightIndex = 0;
		for(int k = beg; k <= end; k++){
			if (left[leftIndex] <= right[rightIndex]){
				unsorted[k] = left[leftIndex];
				leftIndex++;
			}
			else{
				unsorted[k] = right[rightIndex];
				rightIndex++;
			}
		}
	}
	
	
	private static void mergeSort(short[] unsorted, int beg, int end){
		if (beg < end){
			int mid = 0;
			if (end - beg == 1){
				mid = beg;
			}
			else if ( (end + beg)%2 == 1){
				mid = (end + beg - 1) / 2;
			}
			else{
				mid = (end + beg) / 2;
			}
			mergeSort(unsorted, beg, mid);
			mergeSort(unsorted, mid+1, end);
			merge(unsorted, beg, mid, end);
		}
		
	}
	
	private static void print(short[] sorted){
		int p = 0;
		for(int i = 0; i<100; i++) {
			if (p<20){
			     System.out.print(sorted[i] + " ");
			     p++;
			}
			else{
				System.out.println(sorted[i] + " ");
				p = 0;
			}
		}
	}
	private static void insertionSort(long[] unsorted){
		for (int j = 1; j < unsorted.length; j++){
			long key = unsorted[j];
			int i = j-1;
			while(i>=0 && unsorted[i] > key){
				unsorted[i+1] = unsorted[i];
				i = i-1;
			}
			unsorted[i+1] = key;
		}
	}
	
	private static void bubbleSort(long[] unsorted){
		for (int i=0; i<unsorted.length; i++) {
			for (int j = unsorted.length - 1; j>i; j--){
				if (unsorted[j]<unsorted[j-1]){
					long a = unsorted[j];
					unsorted[j] = unsorted[j-1];
					unsorted[j-1] = a;
				}
			}
		}
		
	}
	private static void merge(long[] unsorted, int beg, int mid, int end) {
		int leftArrayLength = mid - beg + 1;
		int rightArrayLength = end - mid;
		long left[] = new long[leftArrayLength + 1];
		long right[] = new long[rightArrayLength + 1];
		for (int i = 0; i<leftArrayLength; i++){
			left[i]=unsorted[beg + i];
		}
		for (int j = 0; j<rightArrayLength; j++){
			right[j] = unsorted[mid + j + 1];
		}
		left[leftArrayLength] = (2^63)-1;
		right[rightArrayLength] = (2^63)-1;
		int leftIndex = 0;
		int rightIndex = 0;
		for(int k = beg; k <= end; k++){
			if (left[leftIndex] <= right[rightIndex]){
				unsorted[k] = left[leftIndex];
				leftIndex++;
			}
			else{
				unsorted[k] = right[rightIndex];
				rightIndex++;
			}
		}
	}
	
	
	private static void mergeSort(long[] unsorted, int beg, int end){
		int mid = 0;
		if (beg < end){
			if (end - beg == 1){
				mid = beg;
			}
			else if ( (end + beg)%2 == 1){
				mid = (end + beg - 1) / 2;
			}
			else{
				mid = (end + beg) / 2;
			}
			mergeSort(unsorted, beg, mid);
			mergeSort(unsorted, mid+1, end);
			merge(unsorted, beg, mid, end);
		}
		
	}
	private static void print(long[] sorted){
		int p = 0;
		for(int i = 0; i<100; i++) {
			if (p<20){
			     System.out.print(sorted[i] + " ");
			     p++;
			}
			else{
				System.out.println(sorted[i] + " ");
				p = 0;
			}
		}
	}
	private static void insertionSort(float[] unsorted){
		for (int j = 1; j < unsorted.length; j++){
			float key = unsorted[j];
			int i = j-1;
			while(i>=0 && unsorted[i] > key){
				unsorted[i+1] = unsorted[i];
				i = i-1;
			}
			unsorted[i+1] = key;
		}
	}
	
	private static void bubbleSort(float[] unsorted){
		for (int i=0; i<unsorted.length; i++) {
			for (int j = unsorted.length - 1; j>i; j--){
				if (unsorted[j]<unsorted[j-1]){
					float a = unsorted[j];
					unsorted[j] = unsorted[j-1];
					unsorted[j-1] = a;
				}
			}
		}
		
	}
	private static void merge(float[] unsorted, int beg, int mid, int end) {
		int leftArrayLength = mid - beg + 1;
		int rightArrayLength = end - mid;
		float left[] = new float[leftArrayLength + 1];
		float right[] = new float[rightArrayLength + 1];
		for (int i = 0; i<leftArrayLength; i++){
			left[i]=unsorted[beg + i];
		}
		for (int j = 0; j<rightArrayLength; j++){
			right[j] = unsorted[mid + j + 1];
		}
		left[leftArrayLength] = Float.MAX_VALUE;
		right[rightArrayLength] = Float.MAX_VALUE;
		int leftIndex = 0;
		int rightIndex = 0;
		for(int k = beg; k <= end; k++){
			if (left[leftIndex] <= right[rightIndex]){
				unsorted[k] = left[leftIndex];
				leftIndex++;
			}
			else{
				unsorted[k] = right[rightIndex];
				rightIndex++;
			}
		}
	}
	
	
	private static void mergeSort(float[] unsorted, int beg, int end){
		if (beg < end){
			int mid = 0;
			if (end - beg == 1){
				mid = beg;
			}
			else if ( (end + beg)%2 == 1){
				mid = (end + beg - 1) / 2;
			}
			else{
				mid = (end + beg) / 2;
			}
			mergeSort(unsorted, beg, mid);
			mergeSort(unsorted, mid+1, end);
			merge(unsorted, beg, mid, end);
		}
		
	}
	private static void print(float[] sorted){
		int p = 0;
		for(int i = 0; i<100; i++) {
			if (p<20){
			     System.out.print(sorted[i] + " ");
			     p++;
			}
			else{
				System.out.println(sorted[i] + " ");
				p = 0;
			}
		}
	}
	private static void insertionSort(double[] unsorted){
		for (int j = 1; j < unsorted.length; j++){
			double key = unsorted[j];
			int i = j-1;
			while(i>=0 && unsorted[i] > key){
				unsorted[i+1] = unsorted[i];
				i = i-1;
			}
			unsorted[i+1] = key;
		}
	}
	
	private static void bubbleSort(double[] unsorted){
		for (int i=0; i<unsorted.length; i++) {
			for (int j = unsorted.length - 1; j>i; j--){
				if (unsorted[j]<unsorted[j-1]){
					double a = unsorted[j];
					unsorted[j] = unsorted[j-1];
					unsorted[j-1] = a;
				}
			}
		}
		
	}
	private static void merge(double[] unsorted, int beg, int mid, int end) {
		int leftArrayLength = mid - beg + 1;
		int rightArrayLength = end - mid;
		double left[] = new double[leftArrayLength + 1];
		double right[] = new double[rightArrayLength + 1];
		for (int i = 0; i<leftArrayLength; i++){
			left[i]=unsorted[beg + i];
		}
		for (int j = 0; j<rightArrayLength; j++){
			right[j] = unsorted[mid + j + 1];
		}
		left[leftArrayLength] = Double.MAX_VALUE;
		right[rightArrayLength] = Double.MAX_VALUE;
		int leftIndex = 0;
		int rightIndex = 0;
		for(int k = beg; k <= end; k++){
			if (left[leftIndex] <= right[rightIndex]){
				unsorted[k] = left[leftIndex];
				leftIndex++;
			}
			else{
				unsorted[k] = right[rightIndex];
				rightIndex++;
			}
		}
	}
	
	
	private static void mergeSort(double[] unsorted, int beg, int end){
		if (beg < end){
			int mid = 0;
			if (end - beg == 1){
				mid = beg;
			}
			else if ( (end + beg)%2 == 1){
				mid = (end + beg - 1) / 2;
			}
			else{
				mid = (end + beg) / 2;
			}
			mergeSort(unsorted, beg, mid);
			mergeSort(unsorted, mid+1, end);
			merge(unsorted, beg, mid, end);
		}
		
	}
	
	private static void print(double[] sorted){
		int p = 0;
		for(int i = 0; i<100; i++) {
			if (p<20){
			     System.out.print(sorted[i] + " ");
			     p++;
			}
			else{
				System.out.println(sorted[i] + " ");
				p = 0;
			}
		}
	}
	private static void insertionSort(Comparable[] unsorted){
		for (int j = 1; j < unsorted.length; j++){
			Comparable key = unsorted[j];
			int i = j-1;
			while(i>=0 && unsorted[i].compareTo(key) > 0){
				unsorted[i+1] = unsorted[i];
				i--;
			}
			unsorted[i+1] = key;
		}
	}
	
	private static void bubbleSort(Comparable[] unsorted){
		for (int i=0; i<unsorted.length; i++) {
			for (int j = unsorted.length - 1; j>i; j--){
				if (unsorted[j].compareTo(unsorted[j-1]) < 0){
					Comparable<Object> a = unsorted[j];
					unsorted[j] = unsorted[j-1];
					unsorted[j-1] = a;
				}
			}
		}
		
	}
	private static void merge(Comparable[] unsorted, int beg, int mid, int end) {
		int leftArrayLength = mid - beg + 1;
		int rightArrayLength = end - mid;
		Comparable left[] = new Comparable[leftArrayLength + 1];
		Comparable right[] = new Comparable[rightArrayLength + 1];
		for (int i = 0; i<leftArrayLength; i++){
			left[i]=unsorted[beg + i];
		}
		for (int j = 0; j<rightArrayLength; j++){
			right[j] = unsorted[mid + j + 1];
		}
		left[leftArrayLength] = null;
		right[rightArrayLength] = null;
		int leftIndex = 0;
		int rightIndex = 0;
		for(int k = beg; k <= end; k++){
			if (left[leftIndex] == null){
				unsorted[k] = right[rightIndex];
				rightIndex++;
			}
			else if(right[rightIndex] == null){
				unsorted[k] = left[leftIndex];
				leftIndex++;
			}
			else if (left[leftIndex].compareTo(right[rightIndex]) <= 0){
				unsorted[k] = left[leftIndex];
				leftIndex++;
			}
			else{
				unsorted[k] = right[rightIndex];
				rightIndex++;
			}
		}
	}
	
	
	private static void mergeSort(Comparable[] unsorted, int beg, int end){
		if (beg < end){
			int mid = 0;
			if (end - beg == 1){
				mid = beg;
			}
			else if ( (end + beg)%2 == 1){
				mid = (end + beg - 1) / 2;
			}
			else{
				mid = (end + beg) / 2;
			}
			mergeSort(unsorted, beg, mid);
			mergeSort(unsorted, mid+1, end);
			merge(unsorted, beg, mid, end);
		}
		
	}
	private static void print(Comparable[] sorted){
		int p = 0;
		for(int i = 0; i<100; i++) {
			if (p<20){
			     System.out.print(sorted[i] + " ");
			     p++;
			}
			else{
				System.out.println(sorted[i] + " ");
				p = 0;
			}
		}
	}
}

