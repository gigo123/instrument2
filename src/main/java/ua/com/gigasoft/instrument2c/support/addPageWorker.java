package ua.com.gigasoft.instrument2c.support;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import ua.com.gigasoft.instrument2c.database.BoxJPADAO;
import ua.com.gigasoft.instrument2c.database.LocationJPADAO;
import ua.com.gigasoft.instrument2c.mainModel.Box;
import ua.com.gigasoft.instrument2c.mainModel.Location;

@Service
public class addPageWorker {
	@Autowired
	private LocationJPADAO locDAO;
	@Autowired
	private BoxJPADAO boxDAO;

	public String addLocationWork(Location location) {
		StringBuilder errorText = new StringBuilder("<ul>");
		Optional<Location> loc = locDAO.getLocByName(location.getName());
		boolean error = false;
		if (loc.isPresent()) {
			error = true;
			errorText.append("<li> место хранения существует </li>");
		}
		if (!error) {
			locDAO.createLocation(location);
			System.out.println("создание лок");
			if (location.isBoxes() == false) {
				Box box = new Box(0, location);
				boxDAO.createBox(box);
			}
		}
		errorText.append("</ul>");
		String errString = errorText.toString();
		if (errString.equals("<ul></ul>")) {
			return "место хранения успешно создано";
		} else {
			return errString;
		}

	}

	/*
	 * public static String addInstrumentWork(Instrument ininstr) { StringBuilder
	 * errorText = new StringBuilder("<ul>"); InstrumentDAO instDAO =
	 * (InstrumentDAO) context.getBean("InstrumentDAO"); boolean error = false;
	 * 
	 * Instrument instrum = instDAO.getInstrumentByName(ininstr.getName()); if
	 * (instDAO.hasError()) { error = true;
	 * errorText.append("<li> ошыбка бази данних </li>"); } else { if (instrum !=
	 * null) { error = true;
	 * errorText.append("<li> инструмент с таким именем уже существует </li>"); } }
	 * if (!error) { if (!instDAO.createInstrument(ininstr)) { error = true;
	 * errorText.append("<li>ошыбка бази данних </li>"); } }
	 * errorText.append("</ul>"); String errString = errorText.toString();
	 * instDAO.closeConection(); if (errString.equals("<ul></ul>")) { return
	 * "Инструмент успешно создан"; } else { return errString; } }
	 * 
	 * public static String addBoxWork(BoxListLocation box) { StringBuilder
	 * errorText = new StringBuilder("<ul>"); BoxDAO boxDAO = (BoxDAO)
	 * context.getBean("BoxDAO"); LocationDAO locDAO = (LocationDAO)
	 * context.getBean("LocationDAO"); boolean error = false; boolean menyBox =
	 * false; try { long locId = Long.parseLong(box.getLocationWB()); Location loc =
	 * locDAO.getLocById(locId); if (loc == null) { error = true;
	 * errorText.append("<li> неправильное место хранения </li>"); } else {
	 * box.setLocation(loc); if (box.getManyBox().equals("O")) { Box tempBox =
	 * boxDAO.getBoxByNumber(box.getNumber(), box.getLocation().getId()); if
	 * (tempBox != null) { error = true;
	 * errorText.append("<li> ячейка с таким номером уже существует </li>"); } if
	 * (!box.getLocation().isBoxes()) { error = true;
	 * errorText.append("<li> место хранения не может содержать ячейки </li>"); } }
	 * else { menyBox = true; } } } catch (Exception e) { error = true;
	 * errorText.append("<li>неправильное место хранения</li>"); }
	 * 
	 * if (!error) { if (menyBox) { int start = Integer.parseInt(box.getStartNum());
	 * int end = Integer.parseInt(box.getEndNum()); end++; Box tempBox = new Box();
	 * tempBox.setLocation(box.getLocation()); for (int i = start; i < end; i++) {
	 * tempBox.setNumber(i); boxDAO.createBox(tempBox); } } else { if
	 * (!boxDAO.createBox(box)) { error = true;
	 * errorText.append("<li>ошыбка бази данних </li>"); } } }
	 * errorText.append("</ul>"); boxDAO.closeConection(); locDAO.closeConection();
	 * String errString = errorText.toString(); if (errString.equals("<ul></ul>")) {
	 * return "Ячейка успесно создана"; } else { return errString; }
	 * 
	 * }
	 */
}
