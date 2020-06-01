package ua.com.gigasoft.instrument2c.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainPageController {
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String model(Model model) {
		 
        model.addAttribute("message", "message");
 
        return "index";
    }

}
