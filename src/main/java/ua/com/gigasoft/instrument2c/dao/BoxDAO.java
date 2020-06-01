package ua.com.gigasoft.instrument2c.dao;

import java.util.List;
import java.util.Optional;

import ua.com.gigasoft.instrument2c.mainModel.Box;

public interface BoxDAO {
	public Optional<Box> getBoxByID(long id);
	public Optional<Box> getBoxByNumber(int number,long idLocation);
	public boolean createBox(Box box);
	public boolean deleteBox(long id);
	public boolean hasError();
	public boolean updateBox(long id,Box box);
	public List<Box> getAllBox();
	public List<Box> getNotEmptyBox();
	public List<Box> getNotEmptyBoxByLocation(long idLocation);
	public List<Box> getAllBoxByLocation(long idLocation);
	public void closeConection();
}
