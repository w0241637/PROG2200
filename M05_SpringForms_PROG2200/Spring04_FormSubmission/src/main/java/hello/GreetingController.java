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
//    @GetMapping("/greeting")
//    public String greetingForm(Model model) {
//        model.addAttribute("greeting", new Greeting());
//        return "greeting";
//    }
//
//    @PostMapping("/greeting")
//    public String greetingSubmit(@ModelAttribute Greeting greeting) {
//        System.out.println("###### Form Greeting=" + greeting.toString());
//        return "result";
//    }

    @GetMapping("/cheater")
    public String cheater(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "cheater";
    }

//        Greeting SessionGreeting = (Greeting) session.getAttribute("sessionGreeting");
//        Greeting greeting = (Greeting) session.getAttribute("greeting");

//        @GetMapping("/reset")
//        public String reset(Model model, HttpSession session) {
//            model.addAttribute("reset", new Greeting());
//
//                    Greeting SessionGreeting = (Greeting) session.getAttribute("sessionGreeting");
//                    Greeting greeting = (Greeting) session.getAttribute("greeting");
//
//            System.out.println("before reset Form Greeting=" + greeting.toString());
//            System.out.println("before reset Form Session Greeting=" + SessionGreeting.toString());
//
//            greeting.resetAll();
//            SessionGreeting.resetAll();
//
//            System.out.println("after reset Form Greeting=" + greeting.toString());
//            System.out.println("after reset Form Session Greeting=" + SessionGreeting.toString());
//
//            return "reset";
//        }
@GetMapping("/reset")
public String reset(Model model, HttpSession session) {

    // Create a greeting, and store it as a session variable
//    Greeting resetGreeting = (Greeting) session.getAttribute("reset");

    Greeting SessionGreeting = (Greeting) session.getAttribute("sessionGreeting");
//    Greeting greeting = (Greeting) session.getAttribute("greeting");


//            System.out.println("after reset Form Greeting=" + greeting.toString());
            System.out.println("after reset Form Session Greeting=" + SessionGreeting.toString());

SessionGreeting.resetAll();
//SessionGreeting.setRandNum();

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
            SessionGreeting.setContent("abc");



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

                System.out.println("yup");
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


//        long x = numToGuess;
//        long y = greeting.getUserGuess();
//
//        z = greeting.getTotalGuesses();
//
//        if (x == y){
//            System.out.println("yup");
//            System.out.println("numtoguess correct:"+greeting.getNumToGuess() + "user guess:"+ greeting.getUserGuess());
//        } else{
//            System.out.println("z: "+z);
//            z++;
//            greeting.setTotalGuesses(z);
//        }


            System.out.println("numtoguess fail:" + greeting.getNumToGuess() + "user guess:" + greeting.getUserGuess());
            return "result2";
        }
    }




//    @GetMapping("/guess")
//    public String guessForm(Model model, HttpSession session) {
//
//        // Create a greeting, and store it as a session variable
//        Greeting SessionGuess = (Greeting) session.getAttribute("sessionGuess");
//
//        if (SessionGuess == null) {
//            SessionGuess = new Greeting();
//            SessionGuess.setId(777);
//            SessionGuess.setContent("Inserted by controller guess");
//            SessionGuess.setNumToGuess(88);
//            SessionGuess.setUserGuess(0);
//            session.setAttribute("sessionGuess", SessionGuess);
//            System.out.println("###### Created Session Greeting=" + SessionGuess.toString());
//        }
//
//        // Get client IP.  Don't use localhost, use your IP instead
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//                .getRequest();
//        String ip = request.getRemoteAddr();
//        System.out.println("###### Form Session Greeting=" + SessionGuess.toString() + " IP=" + ip);
//
//        model.addAttribute("greeting", SessionGuess);
//        return "guess";
//    }
//
//    @PostMapping("/guess")
//    public String guessSubmit(Model model, @ModelAttribute Greeting greeting, HttpSession session) {
//
//        System.out.println("###### Form Greeting=" + greeting.toString());
//
//        // Copy submitted form into the session attribute...just playing around
//        session.setAttribute("sessionGuess", greeting);
//
//        System.out.println("###### Form Session Greeting=" + greeting.toString());
//
//        return "guessresult";
//    }


}

//model.attribute is being stored across all sessions




//    // try #2 ... with session attributes
//    @GetMapping("/greeting2")
//    public String greetingForm2(Model model, HttpSession session) {
//
//        // Create a greeting, and store it as a session variable
//        Greeting SessionGreeting = (Greeting) session.getAttribute("sessionGreeting");
//
//        if (SessionGreeting == null) {
//            SessionGreeting = new Greeting();
//            SessionGreeting.setId(777);
//            SessionGreeting.setContent("Inserted by controller line 53");
//            SessionGreeting.setNumToGuess(88);
//            SessionGreeting.setUserGuess(0);
//            session.setAttribute("sessionGreeting", SessionGreeting);
//            System.out.println("###### Created Session Greeting=" + SessionGreeting.toString());
//        }
//
//        // Get client IP.  Don't use localhost, use your IP instead
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//                .getRequest();
//        String ip = request.getRemoteAddr();
//        System.out.println("###### Form Session Greeting=" + SessionGreeting.toString() + " IP=" + ip);
//
//        model.addAttribute("greeting", SessionGreeting);
//        return "greeting2";
//    }
//
//    @PostMapping("/greeting2")
//    public String greetingSubmit2(Model model, @ModelAttribute Greeting greeting, HttpSession session) {
//
//        System.out.println("###### Form Greeting=" + greeting.toString());
//
//        // Copy submitted form into the session attribute...just playing around
//        session.setAttribute("sessionGreeting", greeting);
//
//        System.out.println("###### Form Session Greeting=" + greeting.toString());
//
//        return "result2";
//    }