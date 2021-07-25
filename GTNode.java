public class GTNode<T> { 

   public T data; 
   public LinkedList<GTNode<T>> children;
   
      public GTNode() { 
      data = null; 
      children = new LinkedList<GTNode<T>>(); 
   }

   //Creates a new instance of GTNode
   public GTNode(T val) { 
      data = val; 
      children = new LinkedList<GTNode<T>>(); 
   }
}
