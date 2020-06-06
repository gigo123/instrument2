package ua.com.gigasoft.instrument2c.pages.create;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ua.com.gigasoft.instrument2c.mainModel.Location;

@Controller
public class AddLocationController {
	
	@GetMapping("/addlocation")
	public String getAddLocCF(Model model,Location location) {
		model.addAttribute("page", "location");	
		return "addLocation";
	}

	@PostMapping("/addlocation")
    public String addLocation(@Validated Location location, 
	BindingResult bindingResult, Model model) {
		System.out.println(location);
		return "index";
	}
	
}
