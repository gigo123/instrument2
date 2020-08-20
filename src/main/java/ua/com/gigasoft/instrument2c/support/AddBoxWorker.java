package ua.com.gigasoft.instrument2c.support;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import ua.com.gigasoft.instrument2c.database.BoxJPADAO;
import ua.com.gigasoft.instrument2c.database.LocationJPADAO;
import ua.com.gigasoft.instrument2c.mainModel.Box;
import ua.com.gigasoft.instrument2c.mainModel.Location;
import ua.com.gigasoft.instrument2c.secondModel.BoxListLocation;

public class AddBoxWorker {
	@Autowired
	private LocationJPADAO locDAO;
	@Autowired
	private BoxJPADAO boxDAO;
	
	public String addBox(BoxListLocation box) {
		Optional<Location> loc = locDAO.getLocById(box.getLocationWB());
		Location location = loc.get();
		if (box.getManyBox().equals("O")) {

			Box newBox = new Box(box.getNumber(), location);
			boxDAO.createBox(newBox);

		} else {
			int start =box.getStartNum();
			int end = box.getEndNum();
			for (int i = start; i <= end; i++) {
				Box tempBox = new Box();
				tempBox.setLocation(location);
				tempBox.setNumber(i);
				boxDAO.createBox(tempBox);
			}

		}
		return null;
	}
}
