public class NonRecursiveMergeSort {
 
 // Print Array
    public static void printArray(int[] array){
        for(int i : array) {
            System.out.printf("%d ", i);
        }
        System.out.printf("n");
    }
 
 // Bottom-up merge sort
 public static void mergeSort(int[] array) {
  if(array.length < 2) {
   // We consider the array already sorted, no change is done
   return;
  }
  // The size of the sub-arrays . Constantly changing .
  int step = 1;
  // startL - start index for left sub-array
  // startR - start index for the right sub-array
  
  int startL, startR;
 
  while(step < array.length) {
   startL = 0;
   startR = step;
   while(startR + step <= array.length) {
    mergeArrays(array, startL, startL + step, startR, startR + step);
    // System.out.printf("startL=%d, stopL=%d, startR=%d, stopR=%dn",
     // startL, startL + step, startR, startR + step);
    startL = startR + step;
    startR = startL + step;
   }
   // System.out.printf("- - - with step = %dn", step);
   if(startR < array.length) {
    mergeArrays(array, startL, startL + step, startR, array.length);
    // System.out.printf("* startL=%d, stopL=%d, startR=%d, stopR=%dn",
     // startL, startL + step, startR, array.length);
   }
   step *= 2;
  }
 }
 
 // Merge to already sorted blocks
 public static void mergeArrays(int[] array, int startL, int stopL,
  int startR, int stopR) {
  // Additional arrays needed for merging
  int[] right = new int[stopR - startR + 1];
  int[] left = new int[stopL - startL + 1];
 
  // Copy the elements to the additional arrays
  for(int i = 0, k = startR; i < (right.length - 1); ++i, ++k) {
   right[i] = array[k];
  }
  for(int i = 0, k = startL; i < (left.length - 1); ++i, ++k) {
   left[i] = array[k];
  }
 
  // Adding sentinel values
  right[right.length-1] = Integer.MAX_VALUE;
  left[left.length-1] = Integer.MAX_VALUE;
 
  // Merging the two sorted arrays into the initial one
  for(int k = startL, m = 0, n = 0; k < stopR; ++k) {
   if(left[m] <= right[n]) {
    array[k] = left[m];
    m++;
   }
   else {
    array[k] = right[n];
    n++;
   }
  }
 }
 
 public static void main(String[] args) {
  // Beacuse of the chosen Sentinel the array
  // should contain values smaller than Integer.MAX_VALUE .
  int[] array = new int[] { 5, 2, 1, 12, 2, 10, 4, 13, 5};
  mergeSort(array);
  printArray(array);
 }
}