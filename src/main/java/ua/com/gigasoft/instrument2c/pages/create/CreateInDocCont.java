package ua.com.gigasoft.instrument2c.pages.create;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ua.com.gigasoft.instrument2c.database.LocationJPADAO;
import ua.com.gigasoft.instrument2c.mainModel.Location;
import ua.com.gigasoft.instrument2c.secondModel.BoxListLocation;
import ua.com.gigasoft.instrument2c.secondModel.ExDocWEB;
import ua.com.gigasoft.instrument2c.secondModel.ExDocWEBList;

@Controller
public class CreateInDocCont {
	
@Autowired
	ExDocWEBList exDocWEBList;
	
	@Autowired
	private LocationJPADAO locDAO;
	
	@GetMapping("/createInDoc")
	public String getInDocCF(Model model) {

	//ExDocWEB doc = new ExDocWEB();
	//	 exDocWEBList = new ExDocWEBList();
	//	List<ExDocWEB> docList = new ArrayList<ExDocWEB>();
	//	docList.add(doc);
//	exDocWEBList.setDocList(docList);
		model.addAttribute("docList", exDocWEBList);
		return "createInDoc";
	}
	@ModelAttribute("locationList")
	public Map<Long, String> getLocationWB() {
		Map<Long, String> locationWB = new HashMap<Long, String>();
		List<Location> locList = locDAO.getAllLocatin();
		for (Location location : locList) {
			locationWB.put(location.getId(), location.getName());
		}
		return locationWB;
	}

}
