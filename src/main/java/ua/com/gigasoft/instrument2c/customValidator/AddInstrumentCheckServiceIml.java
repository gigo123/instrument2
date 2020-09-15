package ua.com.gigasoft.instrument2c.customValidator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.gigasoft.instrument2c.database.InstrumentJPADAO;
import ua.com.gigasoft.instrument2c.mainModel.Instrument;

@Component
public class AddInstrumentCheckServiceIml implements AddInstrumentCheckService {
	@Autowired
	private InstrumentJPADAO instDAO;
	
	@Override
	public boolean checkName(String name) {
		Optional<Instrument> instrum = instDAO.getInstrumentByName(name);
		System.out.println("check in db");
		if (instrum.isPresent()) {
					return false;
		}
		return true;
	}

}
