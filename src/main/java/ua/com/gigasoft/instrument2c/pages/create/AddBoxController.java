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
import org.springframework.web.bind.annotation.PostMapping;

import ua.com.gigasoft.instrument2c.database.LocationJPADAO;
import ua.com.gigasoft.instrument2c.mainModel.Location;
import ua.com.gigasoft.instrument2c.secondModel.BoxListLocation;

@Controller
public class AddBoxController {
	@Autowired
	private LocationJPADAO locDAO;
	
	@GetMapping("/addBox")
	public String getAddBoxCF(BoxListLocation boxList,Model model) {
		Map<Long, String> locationWB = new HashMap<Long, String>();
		List<Location> locList = locDAO.getAllLocatinWB();
		for (Location location : locList) {
			locationWB.put(location.getId(), location.getName());
		}
	
		 model.addAttribute("locationWB", locationWB);
		return "addBox";
	}
	
	@PostMapping("/addBox")
    public String addBox(@Valid BoxListLocation boxList,BindingResult result, Model model) {
		System.out.println(boxList);
		
		return "index";
	}

}
