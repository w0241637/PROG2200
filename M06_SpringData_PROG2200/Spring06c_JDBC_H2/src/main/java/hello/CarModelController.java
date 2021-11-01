package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;


/**
 * Called when hitting "http://localhost:8081/car"   ... Doesn't work, var not saved
 * Called when hitting "http://localhost:8081/car2"  ... Works! use session variables!
 * Called when hitting "http://localhost:8081/dump"
 *
 * All println's were changed to log.info().
 **/
@Controller
public class CarModelController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    DB_Handler db_Handler;

    /**
     * @param model
     * @return
     */
//    @GetMapping("/car")
//    public String carForm(Model model) {
//        model.addAttribute("carModel", new CarModel());
//        return "car";
//    }
//
//    @PostMapping("/car")
//    public String carSubmit(@ModelAttribute CarModel carModel) {
//        log.info("###### Form Greeting=" + carModel.toString());
//        return "result";
//    }

    @GetMapping("/dump")
    public String dumpDB(Model model) {

        List<CarModel> carModels = db_Handler.findAll();

        model.addAttribute("carModels", carModels);

        return "dump";
    }

    // try #2 ... with session attributes
//    @GetMapping("/car2")
//    public String carForm2(Model model, HttpSession session) {
//
//        // Get client IP.  Don't use localhost, use your IP instead
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//                .getRequest();
//        String ip = request.getRemoteAddr();
//        log.info("###### IP=" + ip);
//
//
//        // Create a greeting, and store it as a session variable
//        CarModel SessionCarModel = (CarModel) session.getAttribute("sessionCarModel");
//
//        log.info("entered controller");
//
//        if (SessionCarModel == null) {
//            SessionCarModel = new CarModel();
//            SessionCarModel.setId(777);
//            SessionCarModel.setWinLose("Inserted by controller");
//            session.setAttribute("sessionCarModel", SessionCarModel);
//            log.info("###### Created Session sessionCarModel=" + SessionCarModel.toString());
//        }
//
//        log.info("entered controller"+ SessionCarModel.toString());
//        log.info("###### Form Session sessionCarModel=" + SessionCarModel.toString());
//
//        model.addAttribute("sessionCarModel", SessionCarModel);
//        return "car2";
//    }
//
//    @PostMapping("/car2")
//    public String carSubmit2(Model model, @ModelAttribute CarModel carModel, HttpSession session) {
//
//        log.info("###### Form carModel greeting 2=" + carModel.toString());
//
//        // Copy submitted form into the session attribute...just playing around
//        session.setAttribute("sessionCarModel", carModel);
//
//        log.info("###### Form carModel greeting 2 (after)=" + carModel.toString());
//
//
//        //////////////////////////////
//        // Add new record to car DB
//        db_Handler.save(carModel);
//
//        return "result2";
//    }





    @GetMapping("/cheater")
    public String cheater(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "cheater";
    }


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
        CarModel timCarModel = (CarModel) session.getAttribute("sessionCarModel");

        if (timCarModel == null) {
            timCarModel = new CarModel();
            timCarModel.setId(777);
            timCarModel.setWinLose("Inserted by controller");
            session.setAttribute("sessionCarModel", timCarModel);
            log.info("###### Created Session sessionCarModel=" + timCarModel.toString());
        }



//        session.setAttribute("sessionCarModel", carModel);


        if (SessionGreeting == null) {
            SessionGreeting = new Greeting();
            SessionGreeting.setId(777);
            SessionGreeting.setContent("abc");



//            SessionGreeting.setNumToGuess(numToGuessGlobal);
            SessionGreeting.setRandNum();
            System.out.println("session getRandNum(): "+SessionGreeting.getRandNum());




            System.out.println("num to guess home:"+SessionGreeting.getNumToGuess() + "user guess:"+ SessionGreeting.getUserGuess());

            session.setAttribute("sessionGreeting", SessionGreeting);
            System.out.println("getMapping###### Created Session Greeting=" + SessionGreeting.toString());
        }


        // Get client IP.  Don't use localhost, use your IP instead
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String ip = request.getRemoteAddr();
        System.out.println("###### Form Session Greeting=" + SessionGreeting.toString() + " IP=" + ip);


        model.addAttribute("sessionCarModel", timCarModel);
        model.addAttribute("greeting", SessionGreeting);
        return "greeting2";
    }




    @PostMapping("/greeting2")
    public String greetingSubmit2(Model model, @ModelAttribute Greeting greeting, HttpSession session) {


        Greeting SessionGreeting = (Greeting) session.getAttribute("sessionGreeting");
        CarModel timCarModel = (CarModel) session.getAttribute("sessionCarModel");


//        Greeting SessionGreeting = (Greeting) session.getAttribute("sessionGreeting");

        System.out.println("2###### Form Greeting=" + greeting.toString());
        System.out.println("3###### Form Session Greeting=" + SessionGreeting.toString());


        timCarModel.setId(0);


        if (greeting.getUserGuess()== SessionGreeting.getRandNum()){

            System.out.println("yup");



            timCarModel.setWinLose("win");

            timCarModel.toString();
            db_Handler.save(timCarModel);


            return "winner";
        } else {

            int y = SessionGreeting.getTotalGuesses();
            ++y;
            SessionGreeting.setTotalGuesses(y);
            System.out.println("nope guess count" + SessionGreeting.getTotalGuesses());



            System.out.println("numtoguess fail:" + greeting.getNumToGuess() + "user guess:" + greeting.getUserGuess());


//            greeting.getTotalGuesses();
//            greeting.getId();
//            greeting.getRandNum();
//            greeting.getUserGuess();


            timCarModel.setWinLose("lose");
            timCarModel.setNumToGuess(SessionGreeting.getRandNum());
            timCarModel.setnumOfGuesses(SessionGreeting.getTotalGuesses());
//            timCarModel.setId(0);



            timCarModel.toString();
            db_Handler.save(timCarModel);


            return "result03";
        }
    }


}