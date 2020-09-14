package ua.com.gigasoft.instrument2c.customValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import ua.com.gigasoft.instrument2c.dao.LocationDAO;
import ua.com.gigasoft.instrument2c.mainModel.Location;

@Component
public class AddLocationCheckServiceImpl implements AddLocationCheckService {
	@Autowired
	private LocationDAO locDAO;

	@Override
	public boolean checkName(String name) {
		Optional<Location> loc = locDAO.getLocByName(name);
		if (loc.isPresent()) {
			return false;
		}
		return true;
	}

}
