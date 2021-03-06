package ua.com.gigasoft.instrument2c.database;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.gigasoft.instrument2c.dao.BoxDAO;
import ua.com.gigasoft.instrument2c.mainModel.Box;

@Service
public class BoxJPADAO implements BoxDAO {

	@Autowired
    private BoxRepository boxRepo;
	
	@Override
	public Optional<Box> getBoxByNumber(int number, long idLocation) {
		return boxRepo.getBoxByNumber(number, idLocation);
	}

	@Override
	public boolean hasError() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBox(long id, Box box) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Box> getAllBox() {
		return boxRepo.findAll();
	}

	@Override
	public List<Box> getNotEmptyBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Box> getNotEmptyBoxByLocation(long idLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Box> getAllBoxByLocation(long idLocation) {
		return boxRepo.getAllBoxByLocation(idLocation);

	}

	@Override
	public Optional<Box> getBoxByID(long id) {
		
		return boxRepo.findById(id);
	}

	@Override
	public void createBox(Box box) {
		boxRepo.save(box);
	}

	@Override
	public void deleteBox(Box box) {
		boxRepo.delete(box);
	}

	@Override
	public void closeConection() {
		// TODO Auto-generated method stub
		
	}

}
