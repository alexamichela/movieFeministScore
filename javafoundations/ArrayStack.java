//********************************************************************
//  ArrayStack.java       Java Foundations
//
//  Represents an array implementation of a stack. The bottom of
//  the stack is kept at array index 0.
//********************************************************************

package javafoundations;

import javafoundations.exceptions.*;

public class ArrayStack<T> implements Stack<T>
{
   private final int DEFAULT_CAPACITY = 10;
   private int count;
   private T[] stack;

   //-----------------------------------------------------------------
   //  Creates an empty stack using the default capacity.
   //-----------------------------------------------------------------
   public ArrayStack()
   {
      count = 0;
      stack = (T[]) (new Object[DEFAULT_CAPACITY]);
   }

   //-----------------------------------------------------------------
   //  Adds the specified element to the top of this stack, expanding
   //  the capacity of the stack array if necessary.
   //-----------------------------------------------------------------
   public void push (T element)
   {
      if (count == stack.length)
         expandCapacity();

      stack[count] = element;
      count++;
   }

   //-----------------------------------------------------------------
   //  Returns a string representation of this stack.
   //-----------------------------------------------------------------
   public String toString()
   {
      String result = "<top of stack>\n";

      for (int index=count-1; index >= 0; index--)
         result += stack[index] + "\n";

      return result + "<bottom of stack>";
   }

   //-----------------------------------------------------------------
   //  Creates a new array to store the contents of this stack with
   //  twice the capacity of the old one.
   //-----------------------------------------------------------------
   private void expandCapacity()
   {
      T[] larger = (T[])(new Object[stack.length*2]);

      for (int index=0; index < stack.length; index++)
         larger[index] = stack[index];

      stack = larger;
   }

   //-----------------------------------------------------------------
   //  The following methods are left as Programming Projects.
   //-----------------------------------------------------------------
   public T pop () throws EmptyCollectionException 
	{
		if(count == 0)
			throw new EmptyCollectionException("Pop operation failed. Stack is empty.");
			
		T temp = stack[count - 1];
		count--;
		return temp;
	}
   public T peek () throws EmptyCollectionException 
	{
		if(count == 0)
			throw new EmptyCollectionException("Peek operation failed. Stack is empty.");
			
		return stack[count - 1];
	}
   public boolean isEmpty() 
	{
		return count == 0;
	}
   public int size() 
	{
		return count;
	}
}
