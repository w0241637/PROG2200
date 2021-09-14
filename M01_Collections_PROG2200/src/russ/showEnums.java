package russ;

public class showEnums {


	 public enum word {
	     i, came, saw, left
	 };

	 public static void main(String[] args) {


	     word w = word.i;
	     System.out.println("w is " + w);
	     System.out.println("w.toString = " + w.toString());
	     System.out.println("w.ordinal = " + w.ordinal());

	     System.out.println("word.valueOf = " + word.valueOf("saw"));

	     System.out.println("word.values = " + word.values());
	     System.out.println("number of words = " + word.values().length);
	     System.out.println("word value # [2] = " + word.values()[2]);
	     
	     // Get the 3rd enum in the list of enums
	     word w3 = word.values()[2];
	     System.out.println(" The 3rd value of enum = " + w3);

	     for (word w2 : word.values()) {
	         System.out.print(" " + w2);
	     }

	     switch (w){
	         case i: System.out.println("case i");
	             break;
	         case came: System.out.println("case came");
	             break;
	         case saw: System.out.println("case saw");
	             break;
	         case left: System.out.println("case left");
	             break;
	     }
	            
	     System.out.println("=======================");
	     String[] sample1 = {"i", "came", "i", "saw", "i", "left"};
	     for (int i = 0; i < sample1.length; i++) {
	         System.out.print(" " + sample1[i]);
	     }
	     System.out.println();

	 }
	}

