package hello;


import java.util.*;

/**
 * The "Model" (or data)
 *
 * A POJO: Plain Old Java Object
 *  The fields and getters/setters MUST be named to match
 *  note field names: id and context
 *  also note getter names: getId and getContext
 *
 *  This naming must match or the framework doesn't find the getters/setters
 *
 * The constructor doesn't have to set the fields, but if it doesn't, we
 * need named setters.
 *
 * Generally, a we want:
 *   - fields (whatever data is needed)
 *   - empty constructor (no parameters)
 *   - constructor (fields set using parameters)
 *   - getters/setters
 *   - toString() method (at least for debug, or log purposes)
 *
 * ...and these are found and used automatically when needed.
 */
public class Greeting {


    private static int randomSecond;
//    private static int int_random;
    // These names of fields must match the getters/setters below
    private final long id;
    private final String content;
    public static int random_number;
    static int total;


    Random rand = new Random(); //instance of random class
            int int_random = rand.nextInt(101);


    public Greeting(long id, String content, int total) {
        this.id = id;
        this.content = content;
        this.random_number = int_random;
        Greeting.total = total;
    }

    // Spring Framework finds this getter by matching the field name
    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getRandom_number(){
        return random_number;
    }

    public int getTotal() {
        return total;
    }



}