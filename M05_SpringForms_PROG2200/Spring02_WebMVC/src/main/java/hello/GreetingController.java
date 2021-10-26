package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class GreetingController {

    public int totalCounter, counter1,counter2;



    /**
     * Called when hitting "http://localhost:8081/greeting1"
     *                     "http://localhost:8081/greeting2"
     *                     "http://localhost:8081/greeting2?name=RussyPoo"
     *                     "http://localhost:8081/greeting3"
     *
     * Swaps return string into HTML so that ...
     *    <p th:text="'Hello, ' + ${name} + '!'" />
     *          ... becomes ...
     *    <p th:text="'Hello, ' + "String returned from here" + '!'" />
     *
     * @param name Name defaults to "World" if not specified
     * @param model
     * @return String name of view HTML file to use, parameters are replaced.
     */




    @GetMapping("/greeting1")
    public String greeting1(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);

        // return string is used to find HTML template to use
        // finds greeting1.html in the templates
        return "greeting1";
    }

    @GetMapping("/greeting2")
    public String greeting2(@RequestParam(name="name", required=false, defaultValue="Russ") String name,
                            @RequestParam(name="magicNum", required=false, defaultValue="5726") String magicNum,
                            HttpSession session, Model model) {

        System.out.println("###### Session =" + session.toString());

        model.addAttribute("name", name);
        model.addAttribute("magicNum", magicNum);

        System.out.println("###### Session =" + model.toString());

        return "greeting2";
    }

    @GetMapping("/greeting3")
    public String greeting3(@RequestParam(name="name", required=false, defaultValue="Russ") String name,
                            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("phone", "333-9191");
        model.addAttribute("email", "russy.poo@nscc.ca");
        return "greeting3";
    }



    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="Tim") String name,
                           Model model) {

        counter1++;
        totalCounter++;

        model.addAttribute("colour", "Blue!!");
        model.addAttribute("count", counter1);
        model.addAttribute("total", totalCounter);


        return "greeting";
    }

    @GetMapping("/address")
    public String address(@RequestParam(name="name", required=false, defaultValue="Tim") String name,
                           Model model) {

        counter2++;
        totalCounter++;
        model.addAttribute("houseNum", "743");
        model.addAttribute("street", "Evergreen Terrace");
        model.addAttribute("city", "Springfield");
        model.addAttribute("count", counter2);
        model.addAttribute("total", totalCounter);


        return "address";
    }

    @GetMapping("/reset")
    public String reset(@RequestParam(name="name", required=false, defaultValue="Tim") String name,
                          Model model) {

        totalCounter = 0;

        model.addAttribute("houseNum", "743");
        model.addAttribute("street", "Evergreen Terrace");
        model.addAttribute("city", "Springfield");
        model.addAttribute("count", counter2);
        model.addAttribute("total", totalCounter);


        return "reset";
    }

}