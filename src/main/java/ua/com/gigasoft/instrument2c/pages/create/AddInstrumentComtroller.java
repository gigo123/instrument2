package ua.com.gigasoft.instrument2c.pages.create;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ua.com.gigasoft.instrument2c.mainModel.Instrument;

@Controller
public class AddInstrumentComtroller {
	@GetMapping("/addinstrument")
	public String getAddInstrumentCF(Model model,Instrument instrument) {
		model.addAttribute("page", "instrument");	
		return "addInstrument";
	}

	@PostMapping("/addinstrument")
    public String addInsturmnet(@Validated Instrument instrument, 
	BindingResult bindingResult, Model model) {
		System.out.println(instrument);
		return "index";
	}

}
