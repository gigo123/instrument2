package ua.com.gigasoft.instrument2c.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello")
public class MainPageController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView returnString() {
		ModelAndView model = new ModelAndView("WEB-INF/jsp/views/MainView");
		model.addObject("page", "main");
		return model;
	}

}
