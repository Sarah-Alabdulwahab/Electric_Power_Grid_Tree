public class PowerGridUtils {

	// Return the IDs of all elements in the power grid pg in pre-order.
   public static Queue<Integer> collectPreorder(GT<PGElem> pg){
      Queue<Integer> q = new LinkedQueue<Integer>();
      if(!pg.empty()){
         pg.findRoot();
         q = collectPreorder(pg, q);
      }  
      return q;
   }
   
   private static Queue<Integer> collectPreorder(GT<PGElem> gt, Queue<Integer> q){
      int num = gt.nbChildren();
      q.enqueue(gt.retrieve().getId());
      for(int i = 0 ; i < num ; i++){
         gt.findChild(i);
         q = collectPreorder(gt, q);
         gt.findParent();
      }
      return q;
   }

	// Searches the power grid pg for the element with ID id. 
   // If found, it is made current and true is returned, otherwise 
   // false is returned.
   public static boolean find(GT<PGElem> pg, int id){
      if(pg.empty())
         return false;
      pg.findRoot();
      return findID(pg, id);
   }
      
   public static boolean findID(GT<PGElem> pg, int id){
      if(pg.retrieve().getId() == id)
         return true;
      int num = pg.nbChildren();
      boolean found = false;
      for(int i = 0 ; i < num ; i++){
         pg.findChild(i);
         found = findID(pg, id);
         if(found)
            return found;
         pg.findParent();
      }
      return found;
   }

	// Add the generator element gen to the power grid pg.
   // This can only be done if the grid is empty. 
   // If successful, the method returns true. If there is already a 
   // generator, or gen is not of type Generator, false is returned.
   public static boolean addGenerator(GT<PGElem> pg, PGElem gen){
      if(!pg.empty() || gen.getType() != ElemType.Generator)
         return false;
      pg.insert(gen);
      return true;
   }

	// Attaches pgn to the element id and returns true if successful.
   // Note that a consumer can only be attached to a transmitter,
   // and no element can be be attached to it.
   // The tree must not contain more than one generator located at the 
   // root. If id does not exist, or there is already an element with 
   // the same ID as pgn, pgn is not attached, and the method retrurns false.
   public static boolean attach(GT<PGElem> pg, PGElem pgn, int id){
      if(pg.empty())
         return false;
      if(pgn.getType() == ElemType.Generator)
         return false;
      if(find(pg, pgn.getId()))
         return false;
      if(!find(pg, id))
         return false;
      // current is at proper id
      if(pg.retrieve().getType() == ElemType.Consumer)
         return false;
      if(pgn.getType() == ElemType.Consumer && pg.retrieve().getType() == ElemType.Generator)
         return false;
      //System.out.println("pg type: "+pg.retrieve().getType()+" id: "+pg.retrieve().getId());
      //System.out.println("pgn type: "+pgn.getType()+" id: "+pgn.getId());
      return pg.insert(pgn);
   }

	// Removes element by ID, all corresponding subtree is removed.
   // If removed, true is returned, false is returned otherwise.
   public static boolean remove(GT<PGElem> pg, int id){
      if(!find(pg, id))
         return false;
      pg.remove();
      return true;
   }

	// Computes total power that is consumed by an element (and all its subtree).
   // If id is incorrect, -1 is returned.
   public static double totalPower(GT<PGElem> pg, int id){
      if(!find(pg, id))
         return -1;
      Queue<Integer> q = new LinkedQueue<Integer>();
      q = collectPreorder(pg, q);
      double pow = 0;
      int len= q.length();
      for(int i = 0; i < len ; i++){
         find(pg, q.serve());
         if(pg.retrieve().getType() == ElemType.Consumer)
            pow+= pg.retrieve().getPower();
      }
      return pow;
   }

	// Checks if the power grid contains an overload. 
   // The method returns the ID of the first element preorder that has 
   // an overload and -1 if there is no overload.
   public static int findOverload(GT<PGElem> pg){
      Queue<Integer> q = new LinkedQueue<Integer>();
      q = collectPreorder(pg);
      int len= q.length();
      double total = 0;
      int ID;
      for(int i = 0; i < len ; i++){
         ID = q.serve();
         total = totalPower(pg, ID);
         find(pg, ID);//return current
         //System.out.println("checking id "+ ID+" with power: "+pg.retrieve().getPower()+" total: "+total);
         if(total > pg.retrieve().getPower())
            return ID;
         }
      return -1;
   }
}
