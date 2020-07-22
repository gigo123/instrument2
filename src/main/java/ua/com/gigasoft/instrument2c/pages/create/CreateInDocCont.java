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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.com.gigasoft.instrument2c.secondModel.ExDocWEB;
import ua.com.gigasoft.instrument2c.dao.InstrumentDAO;
import ua.com.gigasoft.instrument2c.database.LocationJPADAO;
import ua.com.gigasoft.instrument2c.mainModel.Instrument;
import ua.com.gigasoft.instrument2c.mainModel.Location;
import ua.com.gigasoft.instrument2c.secondModel.ExDocWEBList;

@Controller
@RequestMapping("/createInDoc")
public class CreateInDocCont {

	ExDocWEBList exDocWEBList;

	@Autowired
	private LocationJPADAO locDAO;
	@Autowired
	private InstrumentDAO instDAO;

	//@GetMapping("/createInDoc")
	@RequestMapping(method = RequestMethod.GET)
	public String getInDocCF(Model model) {
		ExDocWEB doc = new ExDocWEB();
		 exDocWEBList = new ExDocWEBList();
		List<ExDocWEB> docList = new ArrayList<ExDocWEB>();
		docList.add(doc);
		exDocWEBList.setDocList(docList);
		model.addAttribute("docListObject", exDocWEBList);
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
	
	
	@RequestMapping(method = RequestMethod.GET, params = { "addRow"})
	public String addRow (Model model){
		if (exDocWEBList != null) {
		exDocWEBList.getDocList().add(new ExDocWEB());
		}
		System.out.println(exDocWEBList);
		model.addAttribute("docListObject", exDocWEBList);
		
		return "createInDoc";
	}
	
	@RequestMapping(method = RequestMethod.GET, params = { "removeRow" })
	public String docRemoveRow(@RequestParam("removeRow") String id, Model model) {
		System.out.println(id);
		int idInt = 0;
		try {
			idInt = Integer.parseInt(id);
		} catch (Exception e) {
			model.addAttribute("docListObject", exDocWEBList);
			return "createInDoc";
		}
		if (exDocWEBList != null) {
			if (exDocWEBList.getDocList().size() == 1) {
				ExDocWEB doc = new ExDocWEB();
				List<ExDocWEB> docList = new ArrayList<ExDocWEB>();
				docList.add(doc);
				exDocWEBList.setDocList(docList);
			}
			if (exDocWEBList.getDocList().size() > 1) {
				if (idInt <= exDocWEBList.getDocList().size()) {
					exDocWEBList.getDocList().remove(idInt);
				}
			}
		}
		
		model.addAttribute("docListObject", exDocWEBList);
		model.addAttribute("page", "indoc");
		return "createInDoc";

	}

	
}
