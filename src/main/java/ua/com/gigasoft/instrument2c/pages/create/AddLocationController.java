package ua.com.gigasoft.instrument2c.pages.create;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



import ua.com.gigasoft.instrument2c.mainModel.Location;
import ua.com.gigasoft.instrument2c.support.AddLocationWorker;


@Controller
public class AddLocationController {

	@Autowired 
	private AddLocationWorker check;
	
	@GetMapping("/addlocation")
	public String getAddLocCF(Model model) {
		model.addAttribute("location", new Location());
		model.addAttribute("page", "location");	
		return "addLocation";
	}

	@PostMapping("/addlocation")
    public String addLocation(@Validated @Valid Location location, 
	BindingResult result, Model model) {
		model.addAttribute("page", "location");
		 if (result.hasErrors()) {
			 System.out.println(result.getAllErrors());
	            return "addLocation";
	        }
	//	 AddLocationWorker check =new AddLocationWorker();
		check.addLocation(location);
		return "operation";
	}
	
}
