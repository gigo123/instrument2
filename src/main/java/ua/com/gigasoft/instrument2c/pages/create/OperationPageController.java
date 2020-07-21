package ua.com.gigasoft.instrument2c.pages.create;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.com.gigasoft.instrument2c.secondModel.BoxListLocation;
@Controller
public class OperationPageController {
	@GetMapping("/operation")
	public String getAddBoxCF(BoxListLocation boxList,Model model) {
		model.addAttribute("page", "operation");
		return "operation";
	}
}
