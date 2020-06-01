package ua.com.gigasoft.instrument2c.dao;

import java.util.List;
import ua.com.gigasoft.instrument2c.mainModel.Instrument;
public interface InstrumentDAO {
	public Instrument getInstrumentByID(long id);
	public Instrument getInstrumentByName(String name);
	public List<Instrument> getInstrumentByNameL(String name);
	public boolean updateInstrument(Instrument instrument);
	public List<Instrument> getAllInstrument();
	public boolean createInstrument(Instrument instrument);
	public boolean deleteInstrument(long id);
	public boolean hasError();
	public void closeConection();
}
