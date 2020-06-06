package ua.com.gigasoft.instrument2c.pages.create;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.com.gigasoft.instrument2c.database.LocationJPADAO;
import ua.com.gigasoft.instrument2c.mainModel.Location;
import ua.com.gigasoft.instrument2c.secondModel.BoxListLocation;

@Controller
public class AddBoxController {
	@Autowired
	private LocationJPADAO locDAO;
	
	@GetMapping("/addBox")
	public String getAddBoxCF(BoxListLocation boxList,Model model) {
		BoxListLocation boxlist = new BoxListLocation();
		Map<Long, String> locationWB = new HashMap<Long, String>();
		List<Location> locList = locDAO.getAllLocatinWB();
		for (Location location : locList) {
			locationWB.put(location.getId(), location.getName());
		}
	
		 model.addAttribute("boxList", boxlist);
		 model.addAttribute("locationWB", locationWB);
		return "addBox";
	}

}
