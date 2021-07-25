public class LinkedList<T>{
   private Node<T> head;
   private Node<T> current;
   private int size;
	
   public LinkedList() {
      head = current = null;
      size = 0;
   }
	
   public boolean empty() {
      return head == null;
   }
	
   public boolean full() {
      return false;
   }
	
   public void findFirst() {
      current= head;
   }
	
   public void findNext() {
      current= current.next;
   }
	
   public boolean last() {
      return current.next == null;		
   }
	
   public T retrieve() {
      return current.data;
   }
	
   public void update(T e) {
      current.data= e;
   }
	
   public void insert(T e) {
      Node<T> temp;
      if(empty()) 
         current= head= new Node<T> (e);
      else {
         //if(current == null) System.out.println("Problem");
         // place current at end of list
         current = head;
         while(!last())
            findNext();
         temp= current.next;
         current.next= new Node<T> (e);
         current= current.next;
         current.next= temp;
      }
      size++;
   }
	
   public void remove() {
      if(empty())
         return;
      if(current == head)
         head= head.next;
      else {
         Node<T> temp= head;
         while(temp.next != current) 
            temp= temp.next;
         temp.next= current.next;
      }
      if(current.next == null)
         current= head;
      else
         current= current.next;
   size--;
   }
   
   public Node<T> getCurrent(){
      return current;
   }
   
   public void setCurrent(Node<T> val){
      current = val;
   }
   
   public int getSize(){
      return size;
   }
}