package ua.com.gigasoft.instrument2c.pages.create;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.com.gigasoft.instrument2c.dao.InstrumentDAO;
import ua.com.gigasoft.instrument2c.database.LocationJPADAO;
import ua.com.gigasoft.instrument2c.mainModel.Instrument;
import ua.com.gigasoft.instrument2c.mainModel.Location;
import ua.com.gigasoft.instrument2c.secondModel.DocType;
import ua.com.gigasoft.instrument2c.secondModel.ExDocWEB;
import ua.com.gigasoft.instrument2c.secondModel.ExDocWEBList;

@Controller
@RequestMapping("/createInDoc")
public class CreateInDocCont {

	private ExDocWEBList exDocWEBList;

	@Autowired
	private LocationJPADAO locDAO;
	@Autowired
	private InstrumentDAO instDAO;

	@RequestMapping(method = RequestMethod.GET)
	public String getInDocCF(Model model) {
		if (exDocWEBList == null) {
			ExDocWEB doc = new ExDocWEB();
			doc.setDocType(DocType.INDOC);
			exDocWEBList = new ExDocWEBList();
			List<ExDocWEB> docList = new ArrayList<ExDocWEB>();
			docList.add(doc);
			exDocWEBList.setDocList(docList);
		}
		
		model.addAttribute("WEBList", exDocWEBList);
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

	@RequestMapping(method = RequestMethod.GET, params = { "addRow" })
	public String addRow(Model model) {
		
			ExDocWEB doc = new ExDocWEB();
			doc.setDocType(DocType.INDOC);
			exDocWEBList.getDocList().add(doc);
		
		System.out.println(exDocWEBList);
		model.addAttribute("WEBList", exDocWEBList);

		return "createInDoc";
	}

	@RequestMapping(method = RequestMethod.GET, params = { "removeRow" })
	public String docRemoveRow(@RequestParam("removeRow") int id, Model model) {
		int idInt = 0;
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

		model.addAttribute("DocWEBList", exDocWEBList);
		model.addAttribute("page", "indoc");
		return "createInDoc";

	}

	@RequestMapping(method = RequestMethod.POST )
	public String postBoxCF(@Validated @Valid @ModelAttribute("WEBList") ExDocWEBList WEBList,
			BindingResult bindingResult, Model model) {
		System.out.println(WEBList);
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getErrorCount());
			 System.out.println(bindingResult.getAllErrors());
			return "createInDoc";
		}
		String message;
		// message=ControllersCheckWDoc.createExDocUnwrap(exDocWEBList, DocType.INDOC);
		// model.addAttribute("errorText",message);
		return "operation";
	}

}
