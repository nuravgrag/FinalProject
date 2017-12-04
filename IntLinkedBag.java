// File: IntLinkedBag.java from the package edu.colorado.linked
// Complete documentation is available from the IntLinkedBag link in:
//   http://www.cs.colorado.edu/~main/docs

//package edu.colorado.collections;
//import edu.colorado.nodes.IntNode; 

/******************************************************************************
* An IntLinkedBag is a collection of int numbers.
*
* @note
*   (1) Beyond Int.MAX_VALUE elements, countOccurrences,
*   size, and grab are wrong.
*   <p>
*   (2) Because of the slow linear algorithms of this class, large bags will have
*   poor performance.
*
* @see
*   <A HREF="../../../../edu/colorado/collections/IntLinkedBag.java">
*   Java Source Code for this class
*   (www.cs.colorado.edu/~main/edu/colorado/collections/IntLinkedBag.java)
*   </A>
*
* @author Michael Main 
*   <A HREF="mailto:main@colorado.edu"> (main@colorado.edu) </A>
*
* @version Feb 10, 2016
*
* @see IntArrayBag
* @see LinkedBag
******************************************************************************/
public class IntLinkedBag implements Cloneable
{

   public static void main(String[] args) {
      int n = 3000;
      long startTime, stopTime, totalTime;
      //Part A IntLinkedBag
      IntLinkedBag lbag = new IntLinkedBag();
      IntLinkedBag addlbag = new IntLinkedBag();
      
      for(n = 1000; n < 6000; n += 1000) {
         addlbag = new IntLinkedBag();
         for(int i = 0; i < n/10; i++) {
            addlbag.add(i);
         }
         System.out.println("N = " + n);
         startTime = System.currentTimeMillis();
         for(int i = 0; i < n; i++) {
            lbag.add(i);
         }
         stopTime = System.currentTimeMillis();
         totalTime = stopTime - startTime;
         System.out.println("Part A IntLinkedBag: N = " + n + ", time = " + totalTime + "msec");

         //Part B IntLinkedBag Remove
         startTime = System.currentTimeMillis();
         lbag.remove(0);
         for(int i = n - 1; i > 0; i--) {
            if(!lbag.remove(i)) {
               System.out.println("Element does not exist: " + i);
            }
         }
         stopTime = System.currentTimeMillis();
         totalTime = stopTime - startTime;
         System.out.println("Part B IntLinkedBag: N = " + n + ", time = " + totalTime + "msec");

         //Part C addall
         for(int i = 0; i < n; i++) {
            lbag.add(i);
         }
         startTime = System.currentTimeMillis();
         lbag.addAll(addlbag);
         stopTime = System.currentTimeMillis();
         totalTime = stopTime - startTime;
         System.out.println("Part C IntLinkedBag: N = " + n + ", time = " + totalTime + "msec");

      }
      lbag = new IntLinkedBag();
      for(int i = 0; i < 10; i++) {
         lbag.add(i);
      }
      addlbag = new IntLinkedBag();
      addlbag.add(4);
      addlbag.add(72);
      addlbag.add(23);
      addlbag.add(56);
      addlbag.add(13);
      addlbag.add(17);
      lbag.addAll(addlbag);
      IntNode trav = lbag.getHead();
      while(trav != null) {
         System.out.println(trav.getData());
         trav = trav.getLink();
      }

   }

   // Invariant of the IntLinkedBag class:
   //   1. The elements in the bag are stored on a linked list.
   //   2. The head reference of the list is in the instance variable 
   //      head.
   //   3. The total number of elements in the list is in the instance 
   //      variable manyNodes.
   private IntNode head;
   private int manyNodes;   


   /**
   * Initialize an empty bag.
   * @postcondition
   *   This bag is empty.
   **/
   public IntLinkedBag( )
   {
      head = null;
      manyNodes = 0;
   }


   //gets an element based on the index
   public IntNode getHead() {
      return head;
   }

   //gets an element based on the index
   public void set(int i, int element) {
      IntNode trav = head;
      for(int j = 0; j < i; j++)
         trav = trav.getLink();

      trav.addNodeAfter(element);
   }
        
 
   /**
   * Add a new element to this bag.
   * @param element
   *   the new element that is being added
   * @postcondition
   *   A new copy of the element has been added to this bag.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory a new IntNode.
   **/
   public void add(int element)
   {
      if(manyNodes == 0) {
         head = new IntNode(element, null);
         manyNodes++;
         //System.out.println("here1");
      }
      else {
         //System.out.println("here2");
         IntNode trav = head;
         while(trav.getLink() != null && trav.getData() < element) {
            trav = trav.getLink();
            //System.out.println("here2");
         }
         trav.addNodeAfter(element);
         //System.out.println("here2");
         manyNodes++;
      }
   }


   /**
   * Add the contents of another bag to this bag.
   * @param addend
   *   a bag whose contents will be added to this bag
   * @precondition
   *   The parameter, addend, is not null.
   * @postcondition
   *   The elements from addend have been added to this bag.
   * @exception NullPointerException
   *   Indicates that addend is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory to increase the size of the bag.
   **/
   public void addAll(IntLinkedBag addend)
   {

      IntNode[ ] copyInfo;
      
      // The precondition indicates that addend is not null. If it is null,
      // then a NullPointerException is thrown here.
      if (addend.manyNodes > 0)
      {
         copyInfo = IntNode.listCopyWithTail(addend.head);
         copyInfo[1].setLink(head); // Link the tail of copy to my own head... 
         head = copyInfo[0];        // and set my own head to the head of the copy.
         manyNodes += addend.manyNodes;
      }

      head = mergeSort(head);

   }

     

     
   /**
   * Generate a copy of this bag.
   * @return
   *   The return value is a copy of this bag. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to an IntLinkedBag before it can be used.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public Object clone( )
   {  // Clone a nIntLinkedBag object.
      IntLinkedBag answer;
      
      try
      {
         answer = (IntLinkedBag) super.clone( );
      }
      catch (CloneNotSupportedException e)
      {  // This exception should not occur. But if it does, it would probably
         // indicate a programming error that made super.clone unavailable.
         // The most common error would be forgetting the "Implements Cloneable"
         // clause at the start of this class.
         throw new RuntimeException
         ("This class does not implement Cloneable");
      }
      
      answer.head = IntNode.listCopy(head);
      
      return answer;
   }
   

   /**
   * Accessor method to count the number of occurrences of a particular element
   * in this bag.
   * @param target
   *   the element that needs to be counted
   * @return
   *   the number of times that target occurs in this bag
   **/
   public int countOccurrences(int target)
   {
      int answer;
      IntNode cursor;

      answer = 0;
      cursor = IntNode.listSearch(head, target);
      while (cursor != null)
      {  // Each time that cursor is not null, we have another occurrence of
         // target, so we add one to answer and then move cursor to the next
         // occurrence of the target.
         answer++;
         cursor = cursor.getLink( );
         cursor = IntNode.listSearch(cursor, target);
      }
      return answer;
   }

    
   /**
   * Accessor method to retrieve a random element from this bag.
   * @precondition
   *   This bag is not empty.
   * @return
   *   a randomly selected element from this bag
   * @exception IllegalStateException
   *   Indicates that the bag is empty.
   **/
   public int grab( )
   {
      int i;
      IntNode cursor;
      
      if (manyNodes == 0)
         throw new IllegalStateException("Bag size is zero");
         
      i =  (int)(Math.random( ) * manyNodes) + 1;
      cursor = IntNode.listPosition(head, i);
      return cursor.getData( );
   }
   
             
   /**
   * Remove one copy of a specified element from this bag.
   * @param target
   *   the element to remove from the bag
   * @return
   *   If target was found in the bag, then one copy of
   *   target has been removed and the method returns true. 
   *   Otherwise the bag remains unchanged and the method returns false. 
   **/
   public boolean remove(int target)
   {
      IntNode targetNode; // The node that contains the target

      targetNode = IntNode.listSearch(head, target);
      if (targetNode == null)
         // The target was not found, so nothing is removed.
         return false;
      else
      {  // The target was found at targetNode. So copy the head data to targetNode
         // and then remove the extra copy of the head data.
         targetNode.setData(head.getData( ));
         head = head.getLink( );
         manyNodes--;
         return true;
      }
   }
    
      
   /**
   * Determine the number of elements in this bag.
   * @return
   *   the number of elements in this bag
   **/                           
   public int size( )
   {
      return manyNodes;
   }

   private IntNode mergeSort(IntNode h) 
    {
        // Base case : if head is null
      //System.out.println("here");
        if (h == null || h.getLink() == null)
        {
            return h;
        }
        //System.out.println("here2");
        // get the middle of the list
        IntNode middle = getMiddle(h);
        IntNode nextofmiddle = middle.getLink();
 
        // set the next of middle node to null
        middle.setLink(null);
 
        // Apply mergeSort on left list
        IntNode left = mergeSort(h);
 
        // Apply mergeSort on right list
        IntNode right = mergeSort(nextofmiddle);
 
        // Merge the left and right lists
        IntNode sortedlist = sortedMerge(left, right);
        return sortedlist;
    }
 
    // Utility function to get the middle of the linked list
    private IntNode getMiddle(IntNode h) 
    {
        //Base case
        if (h == null)
            return h;
        IntNode fastptr = h.getLink();
        IntNode slowptr = h;
         
        // Move fastptr by two and slow ptr by one
        // Finally slowptr will point to middle node
        while (fastptr != null)
        {
            fastptr = fastptr.getLink();
            if(fastptr!=null)
            {
                slowptr = slowptr.getLink();
                fastptr=fastptr.getLink();
            }
        }
        return slowptr;
    }

    private IntNode sortedMerge(IntNode a, IntNode b) 
    {
        IntNode result = null;
        /* Base cases */
        if (a == null)
            return b;
        if (b == null)
            return a;
 
        /* Pick either a or b, and recur */
        if (a.getData() <= b.getData()) 
        {
            result = a;
            result.setLink(sortedMerge(a.getLink(), b));
        } 
        else
        {
            result = b;
            result.setLink(sortedMerge(a, b.getLink()));
        }
        return result;
 
    }
      
}
           
