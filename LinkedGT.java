public class LinkedGT<T> implements GT<T>{

   private GTNode<T> root, GTcurr;

   public LinkedGT() { 
      root = GTcurr = null; 
   }

   public boolean empty(){
      return root == null;
   }

   public boolean full(){
      return false;
   }

   public T retrieve(){
      return GTcurr.data;
   }

   public void update(T e){
      GTcurr.data = e;
   }

	// If the tree is empty e is inserted as root.
   // If the tree is not empty, e is added as a child of the current node.
   // The new node is made current and true is returned.
   public boolean insert(T e){
      if(empty())
         GTcurr = root = new GTNode<T>(e); 
      else{
         GTcurr.children.insert(new GTNode<T>(e));
         GTcurr = GTcurr.children.getCurrent().data;
      }
      return true;
   }

	// Return the number of children of the current node.
   public int nbChildren(){
      return GTcurr.children.getSize();
   }

	// Put current on the i-th child of the current node (starting from 0),
   // if it exists, and return true. If the child does not exist,
   // current is not changed and the method returns false.
   public boolean findChild(int i){
      if( i<0 || nbChildren() <= i )
         return false;
      //System.out.print("**Find "+ i +" th ==>");
      GTcurr.children.findFirst(); 
      //System.out.print(" " + GTcurr.children.getCurrent().data.data);
      for(int j = 0; j < i ; j++){
         //System.out.print(" j** " +j);
         GTcurr.children.findNext();}
      GTcurr = GTcurr.children.getCurrent().data;
      //System.out.println();
      return true;
   }

	// Put current on the parent of the current node. If the parent does
   // not exist, current does not change and false is returned.
   public boolean findParent(){
      if(GTcurr == root) 
         return false;
      GTcurr = findParent(root);      
      return true;
   }

   public GTNode<T> findParent(GTNode<T> gtn){
      gtn.children.findFirst();
      for(int i = 0; i < gtn.children.getSize(); i++){
         if( gtn.children.getCurrent().data == GTcurr )
            return gtn;
         gtn.children.findNext();
      }//end for 1
      GTNode<T> temp = null;
      gtn.children.findFirst();
      for(int i = 0; i < gtn.children.getSize(); i++){
         temp= findParent(gtn.children.getCurrent().data);
         if( temp == null )
            gtn.children.findNext();
         else 
            break;
      }//end for 2
      return temp;
   }

	// Put current on the root. If the tree is empty nothing happens.
   public void findRoot(){
      if(!empty())
         GTcurr = root;
   }

	// Remove the current subtree. The parent of current, if it exists, 
   // becomes the new current.
   public void remove(){
      if(GTcurr == root)
         GTcurr = root = null;  
      else { 
         GTNode<T> temp = GTcurr;
         findParent();
         GTcurr.children.findFirst(); 
         for(int i = 0; i < GTcurr.children.getSize(); i++){
            if(GTcurr.children.getCurrent().data == temp) //finding child to be removed
               break;
            GTcurr.children.findNext();      
         }//end for
         GTcurr.children.remove();
      }
   }
   
   public void traverse(){
      traverse(root);   
   }
   
   private void traverse(GTNode<T> gtn){
      if(gtn == null)
         return;     
      System.out.println(gtn.data);
      gtn.children.findFirst();
      for(int i = 0; i < gtn.children.getSize(); i++){
         traverse(gtn.children.getCurrent().data);
         gtn.children.findNext();
      } 
   }
   
}
