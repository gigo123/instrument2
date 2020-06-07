package ua.com.gigasoft.instrument2c.pages.create;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ua.com.gigasoft.instrument2c.database.LocationJPADAO;
import ua.com.gigasoft.instrument2c.mainModel.Location;
import ua.com.gigasoft.instrument2c.secondModel.BoxListLocation;
import ua.com.gigasoft.instrument2c.support.addPageWorker;

@Controller
public class AddBoxController {
	@Autowired
	private LocationJPADAO locDAO;
	
	@Autowired
	private  addPageWorker check;
	
	@GetMapping("/addBox")
	public String getAddBoxCF(BoxListLocation boxList,Model model) {
		model.addAttribute("page", "addbox");
		return "addBox";
	}
	
	@PostMapping("/addBox")
    public String addBox(@Valid BoxListLocation boxList,BindingResult result, Model model) {
		model.addAttribute("page", "addbox");
		 if (result.hasErrors()) {
			 System.out.println(result.getAllErrors());
	            return "addBox";
	        }
		 check.addBoxWork(boxList);
		
		 
		return "operation";
	}

	
	@ModelAttribute("locationWB")
	public Map<Long, String> getLocationWB() {
		Map<Long, String> locationWB = new HashMap<Long, String>();
		List<Location> locList = locDAO.getAllLocatinWB();
		for (Location location : locList) {
			locationWB.put(location.getId(), location.getName());
		}
		return locationWB;
	}
}
