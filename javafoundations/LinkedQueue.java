//********************************************************************
//  LinkedQueue.java       Java Foundations
//
//  Represents a linked implementation of a queue.
//********************************************************************

package javafoundations;

import javafoundations.exceptions.*;

public class LinkedQueue<T> implements Queue<T>
{
   private int count;
   private LinearNode<T> front, rear;

   //-----------------------------------------------------------------
   //  Creates an empty queue.
   //-----------------------------------------------------------------
   public LinkedQueue()
   {
      count = 0;
      front = rear = null;
   }

   //-----------------------------------------------------------------
   //  Adds the specified element to the rear of this queue.
   //-----------------------------------------------------------------
   public void enqueue (T element)
   {
      LinearNode<T> node = new LinearNode<T>(element);

      if (count == 0)
         front = node;
      else
         rear.setNext(node);

      rear = node;
      count++;
   }

   //-----------------------------------------------------------------
   //  The following methods are left as Programming Projects.
   //-----------------------------------------------------------------
    public T dequeue () throws EmptyCollectionException { 
    
    if (count==0) throw new EmptyCollectionException("Dequeue operation failed." +
                                                    "The queue is empty.");
    T result = front.getElement();

    front = front.getNext();
   
    count--;
    
    return result; 
    
    }
   
  public T first () throws EmptyCollectionException {
  
    if (count==0) throw new EmptyCollectionException("First operation failed." +
                                                   "The queue is empty.");
    return front.getElement();
  
  }
   

    public boolean isEmpty() { 
    
      if (count==0) return true;
    
      return false;
   
    }
   
    public int size() { 
    
      return count;
      
    }
   
    public String toString() { 
      
      String result = "FRONT OF QUEUE\n";
   
      LinearNode<T> current = front;
   
      if (count==0) result+=("No one currently in the queue.\n");
   
      while (current!=null) {
    
     
      result+= current.getElement() + "\n";
     
      current=current.getNext();
     
   }
   
   return result + "BACK OF QUEUE";
    
    }

}
