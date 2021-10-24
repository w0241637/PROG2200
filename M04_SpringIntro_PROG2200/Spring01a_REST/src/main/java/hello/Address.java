package hello;

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
public class Address {

    // These names of fields must match the getters/setters below
    private final long id;
    private final long houseNumber;
    private final String street;
    private final String city;
    static int total;

    public Address(long id,long houseNumber, String street, String city, int total) {
        this.id = id;
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        Address.total = total;

    }

    // Spring Framework finds this getter by matching the field name
    public long getId() {
        return id;
    }

    public long getHouseNumber() {
        return houseNumber;
    }

    public String getStreet() {
        return street;
    }
    public String getCity() {
        return city;
    }
    public int getTotal() {
        return total;
    }


}