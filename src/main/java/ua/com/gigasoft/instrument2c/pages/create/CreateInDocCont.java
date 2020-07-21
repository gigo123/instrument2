package ua.com.gigasoft.instrument2c.pages.create;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ua.com.gigasoft.instrument2c.dao.InstrumentDAO;
import ua.com.gigasoft.instrument2c.database.LocationJPADAO;
import ua.com.gigasoft.instrument2c.mainModel.Instrument;
import ua.com.gigasoft.instrument2c.mainModel.Location;
import ua.com.gigasoft.instrument2c.secondModel.ExDocWEBList;

@Controller
public class CreateInDocCont {

	@Autowired
	ExDocWEBList exDocWEBList;

	@Autowired
	private LocationJPADAO locDAO;
	@Autowired
	private InstrumentDAO instDAO;

	@GetMapping("/createInDoc")
	public String getInDocCF(Model model) {
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

	@ModelAttribute("instrumentList")
	private Map<Long, String> getInstrumentMap() {
		Map<Long, String> instrumentMap = new HashMap<Long, String>();

		List<Instrument> instList = instDAO.getAllInstrument();
		for (Instrument instrument : instList) {
			instrumentMap.put(instrument.getId(), instrument.getName());
		}
		return instrumentMap;
	}
}
