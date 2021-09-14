// Hashmap02.java
// Write a Java program to store the shape, size, colour, and position of a graphic object in a hashMap.
// Use an enumeration for the shape and colour.
// Make a collection of these graphic objects randomly populated, and then print out the string version (toString) of the objects.
// (that is, randomly generate shape, size, colour, and positions to populate your collection)

package try01;

import java.util.HashMap;
import java.util.Random;

enum SHAPE {
    RHOMBUS, PARALLELOGRAM, TRAPEZOID,
}

enum COLOUR {
    VERMILION, CERULEAN, MAROON
}

public class HashMap02 {

//    private static Enum RHOMBUS, PARALLELOGRAM, TRAPEZOID, RED, BLUE, YELLOW;

//public COLOUR randShape() {
//    Random rand = new Random();
//    int test = rand.nextInt(3);
//    System.out.println(test);
//
//    if (test == 0){
//        return COLOUR.MAROON; }
//
//    else if (test == 1){
//        return COLOUR.VERMILION; }
//
//    else{
//        return COLOUR.CERULEAN; }
//}

    public static void main(String[] args) {

        Random rand = new Random();
        HashMap<String, Object> thing = new HashMap<String, Object>();

//        thing.put("Shape", SHAPE.PARALLELOGRAM);
        switch (rand.nextInt(3)){
            case 0: thing.put("Shape", SHAPE.PARALLELOGRAM);
                break;
            case 1: thing.put("Shape", SHAPE.TRAPEZOID);
                break;
            case 2: thing.put("Shape", SHAPE.RHOMBUS);
        }

        switch (rand.nextInt(3)){
            case 0: thing.put("Colour", COLOUR.CERULEAN);
                break;
            case 1: thing.put("Colour", COLOUR.MAROON);
                break;
            case 2: thing.put("Colour", COLOUR.VERMILION);
        }
        thing.put("Size", rand.nextInt(10));
        thing.put("Position (x)", rand.nextInt(100));
        thing.put("Position (y)", rand.nextInt(100));
        System.out.println(thing.toString());


        HashMap<String, Object> thing2 = new HashMap<String, Object>();
        switch (rand.nextInt(3)){
            case 0: thing2.put("Shape", SHAPE.PARALLELOGRAM);
                break;
            case 1: thing2.put("Shape", SHAPE.TRAPEZOID);
                break;
            case 2: thing2.put("Shape", SHAPE.RHOMBUS);
        }

        switch (rand.nextInt(3)){
            case 0: thing2.put("Colour", COLOUR.CERULEAN);
                break;
            case 1: thing2.put("Colour", COLOUR.MAROON);
                break;
            case 2: thing2.put("Colour", COLOUR.VERMILION);
        }
        thing2.put("Size", rand.nextInt(10));
        thing2.put("Position (x)", rand.nextInt(100));
        thing2.put("Position (y)", rand.nextInt(100));
        System.out.println(thing2.toString());
    }

}
