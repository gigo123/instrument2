package ua.com.gigasoft.instrument2c.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.com.gigasoft.instrument2c.secondModel.BoxListLocation;

@Controller
public class DocPageController {
	@GetMapping("/doc")
	public String getAddBoxCF(BoxListLocation boxList,Model model) {
		model.addAttribute("page", "doc");
		return "doc";
	}

}
