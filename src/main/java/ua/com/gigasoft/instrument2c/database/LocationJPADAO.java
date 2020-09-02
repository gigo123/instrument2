package ua.com.gigasoft.instrument2c.database;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.gigasoft.instrument2c.dao.LocationDAO;
import ua.com.gigasoft.instrument2c.mainModel.Location;

@Component
public class LocationJPADAO implements LocationDAO {

	@Autowired
    private LocationRepository locationRepo;
	@Override
	public void createLocation(Location location) {
		locationRepo.save(location);
	}

	@Override
	public Optional<Location> getLocById(long id) {
		return locationRepo.findById(id);
	}

	@Override
	public Optional<Location> getLocByName(String name) {
		return locationRepo.getLocByName(name);
	}
	@Override
	public List<Location> getAllLocatin() {
		return locationRepo.findAll();
	}

	@Override
	public List<Location> getAllLocatinWB() {
		return locationRepo.getAllLocationWB();
	}

	@Override
	public void deleteLocation(Location location) {
		locationRepo.delete(location);
	}

	@Override
	public boolean hasError() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void closeConection() {
		// TODO Auto-generated method stub
		
	}

}
