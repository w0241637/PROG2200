package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class GreetingController {

    /**
     * Called when hitting "http://localhost:8081/greeting"
     * Called when hitting "http://localhost:8081/greeting2"
     * *
     *
     * @param model
     * @return
     */

    @GetMapping("/override")
    public String override(Model model, HttpSession session) {

        // Create a greeting, and store it as a session variable
        Greeting SessionGreeting = (Greeting) session.getAttribute("sessionGreeting");

        if (SessionGreeting == null) {
            SessionGreeting = new Greeting();
            session.setAttribute("sessionGreeting", SessionGreeting);
        }

        // Get client IP.  Don't use localhost, use your IP instead
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String ip = request.getRemoteAddr();

        model.addAttribute("greeting", SessionGreeting);
        return "override";    }

    @PostMapping("/override")
    public String override2(Model model, @ModelAttribute Greeting greeting, HttpSession session) {


        Greeting SessionGreeting = (Greeting) session.getAttribute("sessionGreeting");

            return "override";
        }







    @GetMapping("/status")
    public String status(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "status";
    }


    @GetMapping("/cheater")
    public String cheater(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "cheater";
    }


@GetMapping("/reset")
public String reset(Model model, HttpSession session) {


    Greeting SessionGreeting = (Greeting) session.getAttribute("sessionGreeting");


            System.out.println("after reset Form Session Greeting=" + SessionGreeting.toString());

SessionGreeting.resetAll();

    System.out.println("after reset Form Session Greeting=" + SessionGreeting.toString());


    // Get client IP.  Don't use localhost, use your IP instead
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
            .getRequest();
    String ip = request.getRemoteAddr();
    System.out.println("###### Form Session Greeting=" + SessionGreeting.toString() + " IP=" + ip);

//    model.addAttribute("greeting", SessionGreeting);
    return "reset";
}





    Random rand = new Random(); //instance of random class
    long numToGuessGlobal = rand.nextInt(101);








    // try #2 ... with session attributes
    @GetMapping("/greeting2")
    public String greetingForm2(Model model, HttpSession session) {

        // Create a greeting, and store it as a session variable
        Greeting SessionGreeting = (Greeting) session.getAttribute("sessionGreeting");

        if (SessionGreeting == null) {
            SessionGreeting = new Greeting();
            SessionGreeting.setId(777);
//            SessionGreeting.setContent("abc");



            SessionGreeting.setNumToGuess(numToGuessGlobal);
            SessionGreeting.setRandNum();

            System.out.println("session getRandNum(): "+SessionGreeting.getRandNum());


//            SessionGreeting.setUserGuess(userGuess);



            System.out.println("num to guess home:"+SessionGreeting.getNumToGuess() + "user guess:"+ SessionGreeting.getUserGuess());

            session.setAttribute("sessionGreeting", SessionGreeting);
            System.out.println("getMapping###### Created Session Greeting=" + SessionGreeting.toString());
        }


        // Get client IP.  Don't use localhost, use your IP instead
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String ip = request.getRemoteAddr();
        System.out.println("###### Form Session Greeting=" + SessionGreeting.toString() + " IP=" + ip);

        model.addAttribute("greeting", SessionGreeting);
        return "greeting2";
    }


    @PostMapping("/greeting2")
    public String greetingSubmit2(Model model, @ModelAttribute Greeting greeting, HttpSession session) {


        Greeting SessionGreeting = (Greeting) session.getAttribute("sessionGreeting");



        // Copy submitted form into the session attribute...just playing around
//        session.setAttribute("sessionGreeting", greeting);

        System.out.println("2###### Form Greeting=" + greeting.toString());
        System.out.println("3###### Form Session Greeting=" + SessionGreeting.toString());





//        if (greeting.getUserGuess()== SessionGreeting.getNumToGuess()){
            if (greeting.getUserGuess()== SessionGreeting.getRandNum()){

                String theWinner = greeting.getContent();
                greeting.setLastWinner(theWinner);
                SessionGreeting.setLastWinner(theWinner);


                System.out.println("yup" + theWinner);
                System.out.println("after win  greeting=" + greeting.toString());
                System.out.println("after win sessiongreeting" + SessionGreeting.toString());

                return "winner";
        } else {
//            long x = greeting.getTotalGuesses();
//            x++;
//            greeting.setTotalGuesses(x);
//            System.out.println("nope guess count" + greeting.getTotalGuesses());

            long y = SessionGreeting.getTotalGuesses();
            ++y;
            SessionGreeting.setTotalGuesses(y);
            System.out.println("nope guess count" + SessionGreeting.getTotalGuesses());



            System.out.println("numtoguess fail:" + greeting.getNumToGuess() + "user guess:" + greeting.getUserGuess());
            return "result2";
        }
    }

}

