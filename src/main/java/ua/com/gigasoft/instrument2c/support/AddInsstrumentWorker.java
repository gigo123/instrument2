package ua.com.gigasoft.instrument2c.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.gigasoft.instrument2c.database.InstrumentJPADAO;
import ua.com.gigasoft.instrument2c.mainModel.Instrument;
@Component("addInstrumentS") 
public class AddInsstrumentWorker {
	@Autowired
	private InstrumentJPADAO instDAO;
	public void addInstrument(Instrument ininstr) {

		instDAO.createInstrument(ininstr);
	}

}
