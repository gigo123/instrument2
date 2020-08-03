package ua.com.gigasoft.instrument2c.database;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.gigasoft.instrument2c.dao.InstrumentDAO;
import ua.com.gigasoft.instrument2c.mainModel.Instrument;
@Service
public class InstrumentJPADAO implements InstrumentDAO{

	@Autowired
    private InstrumentRepository instrumentRepo;
	
	@Override
	public Optional<Instrument> getInstrumentByName(String name) {
		return  instrumentRepo.getInstrumentByName(name);
	}

	@Override
	public List<Instrument> getInstrumentByNameL(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateInstrument(Instrument instrument) {
		instrumentRepo.save(instrument);
	}

	@Override
	public List<Instrument> getAllInstrument() {
		return 	instrumentRepo.findAll();
	}

	@Override
	public void createInstrument(Instrument instrument) {
		instrumentRepo.save(instrument);
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

	@Override
	public Optional<Instrument> getInstrument(Instrument instrument) {
		return instrumentRepo.findById(instrument.getId());
	}

	@Override
	public void deleteInstrument(Instrument instrument) {
		instrumentRepo.delete(instrument);
		
	}

	@Override
	public Optional<Instrument> getInstrumentByid(long id) {
		return instrumentRepo.findById(id);
	}

}
