package ua.com.gigasoft.instrument2c.pages.create;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import ua.com.gigasoft.instrument2c.secondModel.BoxListLocation;

@Controller

public class AddBoxController {
	@GetMapping("/addBox")
	public String getAddBoxCF(BoxListLocation boxList) {

		return "addBox";
	}

}
