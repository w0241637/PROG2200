package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import java.util.concurrent.atomic.AtomicLong;

/**
 * The Controller
 *
 *  Note that you won't find code that instantiates this class.  This
 *  class is automatically found in the class path as part of the project,
 *  and when the Framework sees the annotation, it creates the object, and sets
 *  up the mapping.  The framework knows this controller is used for REST, and so:
 *    - maps to a URL as configured (last part of URL is configured here)
 *    - converts outgoing objects to JSON format (note Greeting object is returned, not a string)
 *
 *  So this code can focus on business logic only...hence, it's the "controller".
 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final AtomicLong counter02 = new AtomicLong();
    public int total01,total02;
    public int totalCounter;

    /**
     * Called when hitting "http://localhost:8081/greeting"
     *
     * @param name Name defaults to "World" if not specified
     * @param session Include session variables in the method
     * @return JSON object of template with name inserted as in {"id":1,"content":"Hello, World!"}
     */
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name, HttpSession session) {

        // If session integer not yet initialized, then init, must use an object
        Integer SessionInt = (Integer) session.getAttribute("sessionInt");
        if (SessionInt == null) {
            SessionInt = new Integer(0);
            session.setAttribute("sessionInt", SessionInt);
        }

        // Modify session integer
        SessionInt = SessionInt + 1;
        session.setAttribute("sessionInt", SessionInt);

        // You can only see the session variable in the log (unless you use it in JSON)
        System.out.println("###### Result Session SessionInt=" + SessionInt.toString());

        total01++;
        totalCounter++;
        System.out.println(totalCounter);

        return new Greeting(counter.incrementAndGet(),
                String.format(template, name),totalCounter);
        /** Common string method, but templates like Thymeleaf can be powerful
         *  This example project doesn't have a "View" component, but if it did, it
         *  would be separate from the controller.  Technically, the string template above
         *  is the "View".
         */

    }

    @RequestMapping("/")
    public Greeting slash(@RequestParam(value = "name", defaultValue = "World") String name, HttpSession session) {

        // If session integer not yet initialized, then init, must use an object
        Integer SessionInt = (Integer) session.getAttribute("sessionInt");
        if (SessionInt == null) {
            SessionInt = new Integer(0);
            session.setAttribute("sessionInt", SessionInt);
        }

        // Modify session integer
        SessionInt = SessionInt + 1;
        session.setAttribute("sessionInt", SessionInt);

        // You can only see the session variable in the log (unless you use it in JSON)
        System.out.println("###### Result Session SessionInt=" + SessionInt.toString());

        total01++;
        totalCounter++;
        System.out.println(totalCounter);

        return new Greeting(counter.incrementAndGet(),
                String.format(template, name),totalCounter);
        /** Common string method, but templates like Thymeleaf can be powerful
         *  This example project doesn't have a "View" component, but if it did, it
         *  would be separate from the controller.  Technically, the string template above
         *  is the "View".
         */
    }

    @RequestMapping("/address")
    public Address address(@RequestParam(value = "name", defaultValue = "World") String name, HttpSession session) {

        String defaultAddress = "house number, street, city";

        // If session integer not yet initialized, then init, must use an object
        Integer SessionInt = (Integer) session.getAttribute("sessionInt");
        if (SessionInt == null) {
            SessionInt = new Integer(0);
            session.setAttribute("sessionInt", SessionInt);
        }

        // Modify session integer
        SessionInt = SessionInt + 1;
        session.setAttribute("sessionInt", SessionInt);

        // You can only see the session variable in the log (unless you use it in JSON)
        System.out.println("###### Result Session SessionInt=" + SessionInt.toString());

        totalCounter++;
        total02++;
        int x = Address.total;

        System.out.println(totalCounter);

        return new Address(counter02.incrementAndGet(),123,"Mailman Estates", "Timville",totalCounter);

        /** Common string method, but templates like Thymeleaf can be powerful
         *  This example project doesn't have a "View" component, but if it did, it
         *  would be separate from the controller.  Technically, the string template above
         *  is the "View".
         */
    }

    @RequestMapping("/reset")
    public Address reset(@RequestParam(value = "name", defaultValue = "World") String name, HttpSession session) {

        String defaultAddress = "house number, street, city";

        // If session integer not yet initialized, then init, must use an object
        Integer SessionInt = (Integer) session.getAttribute("sessionInt");
        if (SessionInt == null) {
            SessionInt = new Integer(0);
            session.setAttribute("sessionInt", SessionInt);
        }

        // Modify session integer
        SessionInt = 0;
        session.setAttribute("sessionInt", SessionInt);

        // You can only see the session variable in the log (unless you use it in JSON)
        System.out.println("###### Result Session SessionInt=" + SessionInt.toString());

        totalCounter = 0;
        total02++;
        int x = Address.total;

        System.out.println(totalCounter);

        return new Address(counter02.incrementAndGet(),123,"Mailman Estates", "Timville",totalCounter);

        /** Common string method, but templates like Thymeleaf can be powerful
         *  This example project doesn't have a "View" component, but if it did, it
         *  would be separate from the controller.  Technically, the string template above
         *  is the "View".
         */
    }
}