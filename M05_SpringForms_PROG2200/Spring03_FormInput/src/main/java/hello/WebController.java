package hello;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class WebController implements WebMvcConfigurer {

    int failCount, addressCount,totalCounter, successCount,showFormCount;

    //@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }


    /**
     *  Called when hitting "http://localhost:8081/"
     *       *
     * @param personForm
     * @return
     */
    @GetMapping("/")
    public String showForm(PersonForm personForm) {
        totalCounter++;
        showFormCount++;
        System.out.println("times hit showForm: " + showFormCount);
        System.out.println(" Total Web hits: " + totalCounter);



        return "form";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {
        int browserCount;

        if (bindingResult.hasErrors()) {
            failCount++;
            totalCounter++;
            System.out.println("times failed to log in: " + failCount);
            System.out.println("Total Web hits: " + totalCounter);
            return "form";
        }




        //return "redirect:/results";
        successCount++;
        System.out.println("times logged in: " + successCount);
        System.out.println("Total Web hits: " + totalCounter);

        return "results";
    }

    @GetMapping("/address")
    public String address(@RequestParam(name="name", required=false, defaultValue="Tim") String name,
                          Model model) {

        addressCount++;
        totalCounter++;

        System.out.println("times hit address: " + addressCount);
        System.out.println("Total Web hits: " + totalCounter);

        model.addAttribute("houseNum", "743");
        model.addAttribute("street", "Evergreen Terrace");
        model.addAttribute("city", "Springfield");
        model.addAttribute("count", addressCount);
        model.addAttribute("total", totalCounter);


        return "address";
    }

    @GetMapping("/reset")
    public String reset(@RequestParam(name="name", required=false, defaultValue="Tim") String name,
                        Model model) {

        failCount = 0;
        addressCount = 0;
        totalCounter = 0;
        successCount = 0;
        showFormCount = 0;


        model.addAttribute("total", totalCounter);

        return "reset";
    }

}