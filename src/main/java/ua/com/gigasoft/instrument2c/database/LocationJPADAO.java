package ua.com.gigasoft.instrument2c.database;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.gigasoft.instrument2c.dao.LocationDAO;
import ua.com.gigasoft.instrument2c.mainModel.Location;

@Service
public class LocationJPADAO implements LocationDAO {

	@Autowired
    private LocationRepository locationRepo;
	@Override
	public boolean createLocation(Location location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Location> getLocById(long id) {
		return locationRepo.findById(id);
	}

	@Override
	public Location getLocByName(String name) {
		// TODO Auto-generated method stub
		return null;
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
