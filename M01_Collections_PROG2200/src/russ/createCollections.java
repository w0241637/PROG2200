package russ;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class createCollections {

    public static void printArray(String[] strs) {
        // print out the array
        for (int i = 0; i < strs.length; i++) {
            System.out.print(" " + strs[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // Abstract Set class has 3 implementations:
        //  -> HashSet
        //  -> TreeSet
        //  -> LinkedHashSet       
        // http://docs.oracle.com/javase/tutorial/collections/interfaces/set.html

        // Declaration
        // Set<String> s99 = new Set<String>();     // Set is abstract, can't make one
        Set<String> S_Set_as_Hash = new HashSet<String>();    // Object access is "Set" level, but object is a HashSet
        Set<String> S_Set_as_Tree = new TreeSet<String>();
        Set<String> S_Set_as_Link = new LinkedHashSet<String>();
        HashSet<String> S_Hash_as_Hash = new HashSet<String>();// Object access is HashSet,   
        TreeSet<String> S_Tree_as_Tree = new TreeSet<String>();
        LinkedHashSet<String> S_Link_as_Link = new LinkedHashSet<String>();

        S_Set_as_Hash.add("Hello");
        S_Set_as_Hash.add("there");
        System.out.println("Set size=" + S_Set_as_Hash.size() + "  s1=" + S_Set_as_Hash);

        S_Set_as_Tree.add("Hello3");
        S_Set_as_Tree.add("there3");
        System.out.println("Set size=" + S_Set_as_Tree.size() + "  s3=" + S_Set_as_Tree);

        S_Set_as_Link.add("Hello");
        S_Set_as_Link.add("there");
        System.out.println("Set size=" + S_Set_as_Link.size() + "  s4=" + S_Set_as_Link);

        S_Hash_as_Hash.add("Hello");
        S_Hash_as_Hash.add("there");
        System.out.println("Set size=" + S_Hash_as_Hash.size() + "  s1=" + S_Hash_as_Hash);

        S_Tree_as_Tree.add("Hello2");
        S_Tree_as_Tree.add("there2");
        System.out.println("Set size=" + S_Tree_as_Tree.size() + "  s2=" + S_Tree_as_Tree);

        S_Link_as_Link.add("Hello2");
        S_Link_as_Link.add("there2");
        System.out.println("Set size=" + S_Link_as_Link.size() + "  s2=" + S_Link_as_Link);

        // Access "Set" methods in any Set
        System.out.println("Sets equal?" + S_Set_as_Hash.containsAll(S_Tree_as_Tree));  // containsAll in Set
        System.out.println("Sets equal?" + S_Hash_as_Hash.containsAll(S_Set_as_Tree));
        System.out.println("Sets equal?" + S_Hash_as_Hash.containsAll(S_Set_as_Link));

        { // Scope
            // Access unique method in subclass, need subclass
            String a = new String(S_Tree_as_Tree.first());  // new object
            String b = S_Tree_as_Tree.first();              // point to existing object
            //String c = S_Set_as_Tree.first();         // No such thing as "first()" within "Set"
            System.out.println("a=" + a + "    b=" + b);
        }

        { // Scope
            // .... cast to subclass
            String a = new String(S_Tree_as_Tree.first());  // new object
            String b = S_Tree_as_Tree.first();              // point to existing object
            TreeSet casted = (TreeSet) S_Set_as_Tree;    // cast from "Set" to "TreeSet"
            String c = (String) casted.first();         // now get String, need cast for String
            //String e =  (Double) casted.first();         // now get String, need cast for String
            String d = (String) ((TreeSet) S_Set_as_Tree).first();   // 

            System.out.println("a=" + a + "    b=" + b + "    c=" + c + "    d=" + d);
        }

        { //scope
            // Check what class an object is (before casting and making something bad happen)
            if (S_Set_as_Tree instanceof HashSet) {
                System.out.println(" S_Set_as_Tree is an instance of HashSet");
            }
            if (S_Set_as_Tree instanceof TreeSet) {
                System.out.println(" S_Set_as_Tree is an instance of TreeSet");
            }
            if (S_Set_as_Tree instanceof LinkedHashSet) {
                System.out.println(" S_Set_as_Tree is an instance of LinkedHashSet");
            }
        }
    }
}
