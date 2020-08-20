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

	public void addInstrumentWork(Instrument ininstr) {

		instDAO.createInstrument(ininstr);
	}

	public String addBoxWork(BoxListLocation box) {
System.out.println(box.getLocationWB());
		Optional<Location> loc = locDAO.getLocById(box.getLocationWB());
		Location location = loc.get();
		if (box.getManyBox().equals("O")) {

			Box newBox = new Box(box.getNumber(), location);
			boxDAO.createBox(newBox);

		} else {
			int start =box.getStartNum();
			int end = box.getEndNum();
			end++;
			System.out.println(start);
			System.out.println(end);
			for (int i = start; i < end; i++) {
				Box tempBox = new Box();
				tempBox.setLocation(location);
				tempBox.setNumber(i);
				boxDAO.createBox(tempBox);
			}

		}
		return null;
	}

}
