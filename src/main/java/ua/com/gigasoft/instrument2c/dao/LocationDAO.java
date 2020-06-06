package ua.com.gigasoft.instrument2c.dao;

import java.util.List;
import java.util.Optional;

import ua.com.gigasoft.instrument2c.mainModel.Location;
public interface LocationDAO {
	public  boolean createLocation(Location location);
	public Optional<Location> getLocById(long id);
	public Location getLocByName(String name);
	public List<Location> getAllLocatin();
	public List<Location> getAllLocatinWB();
	public boolean hasError();
	public void closeConection();
	public void deleteLocation(Location location);
}
