package ua.com.gigasoft.instrument2c.dao;

import java.util.List;
import java.util.Optional;

import ua.com.gigasoft.instrument2c.mainModel.Instrument;
public interface InstrumentDAO {
	public Optional<Instrument> getInstrument(Instrument instrument);
	public Optional<Instrument>getInstrumentByName(String name);
	public List<Instrument> getInstrumentByNameL(String name);
	public void updateInstrument(Instrument instrument);
	public List<Instrument> getAllInstrument();
	public void createInstrument(Instrument instrument);
	public void deleteInstrument(Instrument instrument);
	public boolean hasError();
	public void closeConection();
}
