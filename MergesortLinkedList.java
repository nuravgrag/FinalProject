// File: MergesortLinkedList.java
// A Java application to illustrate the use of a merge sort
// Additional javadoc documentation is available at:
//   http://www.cs.colorado.edu/~main/docs/MergesortLinkedList.html
 
/******************************************************************************
* The <CODE>MergesortLinkedList</CODE> Java application illustrates a merge sort.
*
* <p><b>Java Source Code for this class:</b>
*   <A HREF="../applications/MergesortLinkedList.java">
*   http://www.cs.colorado.edu/~main/applications/MergesortLinkedList.java
*   </A>
*
* @author Michael Main 
*   <A HREF="mailto:main@colorado.edu"> (main@colorado.edu) </A>
*
* @version Feb 10, 2016
******************************************************************************/
public class MergesortLinkedList
{
   /**
   * The main method illustrates the use of a merge sort to sort a 
   * small array.
   * @param args
   *   not used in this implementation
   **/
  /* public static void main(String[ ] args)
   {
      final String BLANKS = "  "; // A String of two blanks
      int i;                      // Array index

      int[ ] data = {80, 10, 50, 70, 60, 90, 20, 30, 40, 0 };

      // Print the array before sorting:
      System.out.println("Here is the entire original array:");
      for (i = 0; i < data.length; i++)
        System.out.print("[" + i + "]" + "\t");
      System.out.println( );
      for (i = 0; i < data.length; i++)
        System.out.print(data[i] + "\t");
      System.out.println( );

      // Sort the numbers, and print the result with two blanks after each number.
      MergesortLinkedList(data, 0, data.size());
      System.out.println("The numbers are now:");
      for (i = 0; i < data.length; i++)
        System.out.print("[" + i + "]" + "\t");
      System.out.println( );
      for (i = 0; i < data.length; i++)
        System.out.print(data[i] + "\t");
      System.out.println( );
   } */
   
   
   /**
   * Sort an array of integers from smallest to largest, using a merge sort
   * algorithm.
   * @param data
   *   the array to be sorted
   * @param first
   *   the start index for the portion of the array that will be sorted
   * @param n
   *   the number of elements to sort
   * <b>Precondition:</b>
   *   <CODE>data[first]</CODE> through <CODE>data[first+n-1]</CODE> are valid
   *   parts of the array.
   * <b>Postcondition:</b>
   *   If <CODE>n</CODE> is zero or negative then no work is done. Otherwise, 
   *   the elements of <CODE>data</CODE> have been rearranged so that 
   *   <CODE>data[first] &lt;= data[first+1] &lt;= ... &lt;= data[first+n-1]</CODE>.
   * @exception ArrayIndexOutOfBoundsException
   *   Indicates that <CODE>first+n-1</CODE> is an index beyond the end of the
   *   array.
   * */
   public static void MergesortLinkedList(IntLinkedBag data, int first, int n)
   {
      int n1; // Size of the first half of the array
      int n2; // Size of the second half of the array

      if (n > 1)
      {
         // Compute sizes of the two halves
         n1 = n / 2;
         n2 = n - n1;

         //print divides
         //printDivide(data, first, n);
         MergesortLinkedList(data, first, n1);      // Sort data[first] through data[first+n1-1]
         MergesortLinkedList(data, first + n1, n2); // Sort data[first+n1] to the end

         // Merge the two sorted halves.
         merge(data, first, n1, n2);
      }
   } 
  
   private static void merge(IntLinkedBag data, int first, int n1, int n2)
   // Precondition: data has at least n1 + n2 components starting at data[first]. The first 
   // n1 elements (from data[first] to data[first + n1 - 1] are sorted from smallest 
   // to largest, and the last n2 (from data[first + n1] to data[first + n1 + n2 - 1]) are also
   // sorted from smallest to largest. 
   // Postcondition: Starting at data[first], n1 + n2 elements of data
   // have been rearranged to be sorted from smallest to largest.
   // Note: An OutOfMemoryError can be thrown if there is insufficient
   // memory for an array of n1+n2 ints.
   {
      IntLinkedBag temp = new IntLinkedBag(); // Allocate the temporary array
      int copied  = 0; // Number of elements copied from data to temp
      int copied1 = 0; // Number copied from the first half of data
      int copied2 = 0; // Number copied from the second half of data
      int i;           // Array index to copy from temp back into data

      // Merge elements, copying from two halves of data to the temporary array.
      while ((copied1 < n1) && (copied2 < n2))
      {
         if (data.get(first + copied1) > data.get(first + n1 + copied2))
            temp.set(copied++, data.get(first + (copied1++)));
         else
            temp.set(copied++, data.get(first + n1 + (copied2++)));
      }

      // Copy any remaining entries in the left and right subarrays.
      while (copied1 < n1)
         temp.set(copied++, data.get(first + (copied1++)));
      while (copied2 < n2)
         temp.set(copied++, data.get(first + n1 + (copied2++)));

      // Copy from temp back to the data array.
      for (i = 0; i < n1+n2; i++)
         data.set(first + i, temp.get(i));

     // printMerge(data, first, n1, n2);

   }

   

   private static void printDivide(int[ ] data, int first, int n) {
      // Compute sizes of the two halves
      int n1 = n / 2;
      int n2 = n - n1;
      //int j = first;
      System.out.println("Dividing...");
      for(int i = first; i < first + n1; i++) {
         System.out.print("[" + i + "]\t");
      }
      System.out.print("|\t");
      for(int i = first + n1; i < first + n1 + n2; i++) {
         System.out.print("[" + i + "]\t");
      }
      System.out.println();
      for(int i = first; i < first + n1; i++) {
         System.out.print(data[i] + "\t");
      }
      System.out.print("|\t");
      for(int i = first + n1; i < first + n1 + n2; i++) {
        System.out.print(data[i] + "\t");
      }
      System.out.println();

   }

   private static void printMerge(int[ ] data, int first, int n1, int n2) {
      System.out.println("Merged...");
      for(int j = first; j < first + n1 + n2; j++) {
         System.out.print("[" + j + "]\t");
      }
      System.out.println();
      for(int j = first; j < first + n1 + n2; j++) {
        System.out.print(data[j] + "\t");
      }
      System.out.println();
   }
   
}


