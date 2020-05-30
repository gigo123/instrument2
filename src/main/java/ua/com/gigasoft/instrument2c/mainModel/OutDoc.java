package ua.com.gigasoft.instrument2c.mainModel;


public class OutDoc extends DocModel {
	public OutDoc(Location outLocation, Box outBox, DocCatalog catalogId, Instrument instrument, float amount) {
		super(outLocation, outBox, catalogId, instrument, amount);
	}
	public OutDoc() {};
}
