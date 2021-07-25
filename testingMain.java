public class testingMain{

   static      LinkedGT<Integer> lgt= new LinkedGT<Integer>();

   public static void build ()
   {
   /*
                    1
                    |
            |------ |------- |
            2       3        4
            |                |
            |             |-----|
            5             6     7
   */
      lgt.insert(1);
      lgt.insert(2);
      lgt.insert(5);
   
      lgt.findRoot();
      lgt.insert(3);
   
      lgt.findRoot();
      lgt.insert(4);
   
      lgt.insert(6);
      lgt.findParent();
      lgt.insert(7);
   
   }


   public static void buildStage2 ()
   {
   /*
                    1
                    |
            |------ |--------- |
            2       3          4
            |                  |
            |               |-----|
            5               6     7
   |---|----|----|   |--|---|         
   8   9   10   11   12 13  14
   */
   
      lgt.findParent(); //4
      lgt.findChild(0); //6
      lgt.insert(12);
      lgt.findParent();
      lgt.insert(13);
      lgt.findParent();
      lgt.insert(14);
       
      lgt.findRoot();
      lgt.findChild(0);//2
      lgt.findChild(0);//5
      lgt.insert(8);
      lgt.findParent();
      lgt.insert(9);
      lgt.findParent();
      lgt.insert(10);
      lgt.findParent();
      lgt.insert(11);
   
   }



   public static void main(String[]args){
   
      build();
      buildStage2();
      //lgt.traverse();
      lgt.findRoot();
      //System.out.println(lgt.GTcurr.data);
      
      //lgt.findChild(0);
      //System.out.println(lgt.GTcurr.data + " 1st child of root");
   
      lgt.findChild(1);
      //System.out.println(lgt.GTcurr.data + " 2nd child of root");
   
      lgt.findParent();
      lgt.findChild(0);
      //System.out.println(lgt.GTcurr.data + " 1st child of root");
   
   
   ////////
      lgt.findParent();
      lgt.findChild(2);
      //System.out.println(lgt.GTcurr.data + " 3rd child of root");
   
      if(lgt.findChild(1))
         //System.out.println(lgt.GTcurr.data + " 2nd child of 4");
      //else
         System.out.println("invalid");
     
   ///////
   
      System.out.println(" ------ ");
   
      //System.out.println(pre(lgt));
      
      print(pre(lgt));
   
   
      //LinkedGT<Integer> lgt= new LinkedGT<Integer>();
   
   /*
      lgt.insert(1);
      lgt.insert(2);
      lgt.findParent();
      lgt.insert(3);
      lgt.insert(6);
      lgt.findParent();
      lgt.insert(7);
      
      lgt.findParent();
                  System.out.println(" num of children 2? " + lgt.nbChildren());
      lgt.findParent();
      lgt.insert(4);
            lgt.findParent();
                  System.out.println(" num of children 3? " + lgt.nbChildren());
      lgt.insert(5);
      lgt.insert(8);
      lgt.traverse();
      
      System.out.println(" ------ ");
      
      lgt.findRoot();
      lgt.findChild(2);
           System.out.println(lgt.GTcurr.data + " should be 4. the 3rd child of root");
   
     lgt.findChild(3);
           System.out.println(lgt.GTcurr.data + " should be 5. the 4th child of root");
   
     lgt.findChild(0);
           System.out.println(lgt.GTcurr.data + " should be 2. the 1st child of root");
   
      
      pre(lgt);
      
      */
      /*System.out.println(" going up 3 times ");
      
      if(lgt.findParent())
      System.out.println(lgt.GTcurr.data);
      
      if(lgt.findParent())
      System.out.println(lgt.GTcurr.data);
      
      if(lgt.findParent())
      System.out.println(lgt.GTcurr.data);
      
      System.out.println(" inserting here ");
      lgt.insert(45);
      
      System.out.println(" parent should be 40 ");
      if(lgt.findParent())
         System.out.println(lgt.GTcurr.data);
      else
         System.out.println("the root");
       
      
      
      System.out.println(" traverse ");
      lgt.traverse();
      */
   
   }
  
  /*
   public static String pre(GT<Integer> gt){
      String p = "";
      if(!gt.empty())
      {
         gt.findRoot();
         //p+= (" " + gt.retrieve());      
         p = pre(gt,p);
      }  
      return p;
   }
   
   public static String pre(GT<Integer> gt, String p){
      int num = gt.nbChildren();
      p+= (" " + gt.retrieve());
      
       System.out.println(gt.retrieve() + "\t p: " + p);
       //System.out.println(gt.retrieve());
       for(int i = 0 ; i < num ; i++){
         gt.findChild(i);
        // System.out.println(gt.retrieve());
         //p = (" " + 
         p=pre(gt,p);
         gt.findParent();
      }
      return p;
   }
   */
   public static Queue<Integer> pre(GT<Integer> gt){
      Queue<Integer> q = new LinkedQueue<Integer>();
      if(!gt.empty())
      {
         gt.findRoot();
         q = pre(gt, q);
      }  
      return q;
   }
   
   private static Queue<Integer> pre(GT<Integer> gt, Queue<Integer> q){
      int num = gt.nbChildren();
      q.enqueue(gt.retrieve());
      for(int i = 0 ; i < num ; i++){
         gt.findChild(i);
         q = pre(gt, q);
         gt.findParent();
      }
      return q;
   }
   
   private static void print(Queue<Integer> q) {

		System.out.println("------------------");
		if (q == null) {
			System.out.println("null");
		} else if (q.length() == 0) {
			System.out.println("empty");
			return;
		} else {
			for (int i = 0; i < q.length(); i++) {
				Integer e = q.serve();
				q.enqueue(e);
				System.out.println(e);
			}
		}
		System.out.println("------------------");
	}


 
 
 /*
 works for one level only
   public static String pre(GT<Integer> gt){
      gt.findRoot();
      String p = " ";
      p+= (" " + gt.retrieve());
      int num= gt.nbChildren();
      
      for(int i = 0 ; i < num ; i++){
         gt.findChild(i);
         //System.out.println(gt.retrieve());
         p += (" " + gt.retrieve());
         gt.findParent();
      }
      return p;
   }
   */

}