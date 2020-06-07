package ua.com.gigasoft.instrument2c.support;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import ua.com.gigasoft.instrument2c.database.BoxJPADAO;
import ua.com.gigasoft.instrument2c.database.InstrumentJPADAO;
import ua.com.gigasoft.instrument2c.database.LocationJPADAO;
import ua.com.gigasoft.instrument2c.mainModel.Box;
import ua.com.gigasoft.instrument2c.mainModel.Instrument;
import ua.com.gigasoft.instrument2c.mainModel.Location;
import ua.com.gigasoft.instrument2c.secondModel.BoxListLocation;

@Service
public class addPageWorker {
	@Autowired
	private LocationJPADAO locDAO;
	@Autowired
	private BoxJPADAO boxDAO;
	@Autowired
	private InstrumentJPADAO instDAO;

	public void addLocationWork(Location location) {

		locDAO.createLocation(location);
		if (location.isBoxes() == false) {
			Box box = new Box(0, location);
			boxDAO.createBox(box);
		}
	}

	public String addInstrumentWork(Instrument ininstr) {
		StringBuilder errorText = new StringBuilder("<ul>");
		boolean error = false;

		Optional<Instrument> instrum = instDAO.getInstrumentByName(ininstr.getName());

		if (instrum.isPresent()) {
			error = true;
			errorText.append("<li> инструмент с таким именем уже существует </li>");
		}

		if (!error) {
			instDAO.createInstrument(ininstr);
		}
		errorText.append("</ul>");
		String errString = errorText.toString();
		if (errString.equals("<ul></ul>")) {
			return "Инструмент успешно создан";
		} else {
			return errString;
		}
	}

	/*
	 * public String addBoxWork(BoxListLocation box) { StringBuilder errorText = new
	 * StringBuilder("<ul>");
	 * 
	 * boolean error = false; boolean menyBox = false; long locId =
	 * Long.parseLong(box.getLocationWB()); Optional<Location> loc =
	 * locDAO.getLocById(locId); if (loc.isPresent()) { Location location =
	 * loc.get(); box.setLocation(location); if (box.getManyBox().equals("O")) {
	 * Optional<Box> tempBox = boxDAO.getBoxByNumber(box.getNumber(),
	 * box.getLocation().getId()); if (tempBox.isPresent()) { error = true;
	 * errorText.append("<li> ячейка с таким номером уже существует </li>"); } else
	 * { menyBox = true; }
	 * 
	 * } else { error = true;
	 * errorText.append("<li> неправильное место хранения </li>"); }
	 * 
	 * if (!error) { if (menyBox) { int start = Integer.parseInt(box.getStartNum());
	 * int end = Integer.parseInt(box.getEndNum()); end++; Box tempBox = new Box();
	 * tempBox.setLocation(box.getLocation()); for (int i = start; i < end; i++) {
	 * tempBox.setNumber(i); boxDAO.createBox(tempBox); } } else {
	 * boxDAO.createBox(box);
	 * 
	 * } } errorText.append("</ul>");
	 * 
	 * String errString = errorText.toString(); if (errString.equals("<ul></ul>")) {
	 * return "Ячейка успесно создана"; } else { return errString; } } return null;
	 * }
	 */
}
