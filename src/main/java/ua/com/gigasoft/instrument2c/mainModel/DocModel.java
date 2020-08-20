package ua.com.gigasoft.instrument2c.mainModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

import ua.com.gigasoft.instrument2c.secondModel.DocType;

@Entity
@Table(name = "doc") 
public class DocModel {
	 @Id 
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	 @OneToOne
	private Location outLocation;
	 @OneToOne
	private Box outBox;
	 @OneToOne
	private Location inLocation;
	 @OneToOne
	private Box inBox;
	 @OneToOne
	private DocCatalog catalogId;
	 @OneToOne
	private Instrument instrument;
	 @PositiveOrZero
	private float amount;
	 
	private DocType docType;
	
	public DocType getDocType() {
		return docType;
	}
	public void setDocType(DocType docType) {
		this.docType = docType;
	}
	public Location getInLocation() {
		return inLocation;
	}
	public void setInLocation(Location inLocation) {
		this.inLocation = inLocation;
	}
	public Box getInBox() {
		return inBox;
	}
	public void setInBox(Box inBox) {
		this.inBox = inBox;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Location getOutLocation() {
		return outLocation;
	}
	public void setOutLocation(Location outLocation) {
		this.outLocation = outLocation;
	}
	public Box getOutBox() {
		return outBox;
	}
	public void setOutBox(Box outBox) {
		this.outBox = outBox;
	}
	public DocCatalog getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(DocCatalog catalogId) {
		this.catalogId = catalogId;
	}
	public Instrument getInstrument() {
		return instrument;
	}
	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	


	
	public DocModel(Location outLocation, Box outBox, Location inLocation, Box inBox, DocCatalog catalogId,
			Instrument instrument, float amount, DocType docType) {
		super();
		this.outLocation = outLocation;
		this.outBox = outBox;
		this.inLocation = inLocation;
		this.inBox = inBox;
		this.catalogId = catalogId;
		this.instrument = instrument;
		this.amount = amount;
		this.docType = docType;
	}
	
	
	public DocModel(Location outLocation, Box outBox, DocCatalog catalogId, Instrument instrument, float amount,
			DocType docType) {
		super();
		this.outLocation = outLocation;
		this.outBox = outBox;
		this.catalogId = catalogId;
		this.instrument = instrument;
		this.amount = amount;
		this.docType = docType;
	}
	
	public DocModel() {
	}

}
