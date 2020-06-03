package ua.com.gigasoft.instrument2c.pages.create;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.com.gigasoft.instrument2c.secondModel.BoxListLocation;

@Controller
@RequestMapping("/addBox")
public class AddBoxController {
	@RequestMapping(method = RequestMethod.GET)
	
	public String getAddBoxCF(BoxListLocation boxList) {

		return "index";
	}

}
