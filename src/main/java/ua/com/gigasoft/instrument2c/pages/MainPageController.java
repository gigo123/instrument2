package ua.com.gigasoft.instrument2c.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
	@GetMapping("/" )
	public String model(Model model) {
		 
        model.addAttribute("message", "message");
 
        return "index";
    }

}
