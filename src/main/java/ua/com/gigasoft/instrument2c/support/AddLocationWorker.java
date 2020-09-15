package ua.com.gigasoft.instrument2c.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ua.com.gigasoft.instrument2c.database.BoxJPADAO;
import ua.com.gigasoft.instrument2c.database.LocationJPADAO;
import ua.com.gigasoft.instrument2c.mainModel.Box;
import ua.com.gigasoft.instrument2c.mainModel.Location;

@Component
public class AddLocationWorker {
	
	@Autowired
	private BoxJPADAO boxDAO;
	@Autowired
	private LocationJPADAO locDAO;
	public void addLocation(Location location) {

		location = locDAO.createLocation(location);
		if (location.isBoxes() == false) {
			Box box = new Box(0, location);
			boxDAO.createBox(box);
		}
	}
}
