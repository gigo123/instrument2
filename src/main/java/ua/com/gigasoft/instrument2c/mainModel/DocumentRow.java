package ua.com.gigasoft.instrument2c.mainModel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "doc") 
public class DocumentRow {
	 @Id 
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	 
	 @ManyToOne(fetch=FetchType.LAZY)
	private Box outBox;
	 @ManyToOne(fetch=FetchType.LAZY)
	private Box inBox;
	 @ManyToOne(fetch=FetchType.LAZY)
	private Instrument instrument;
	 @PositiveOrZero
	private float amount;
	
	
	
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
	
	public Box getOutBox() {
		return outBox;
	}
	public void setOutBox(Box outBox) {
		this.outBox = outBox;
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
	


	
	public DocumentRow( Box outBox, Box inBox,
			Instrument instrument, float amount) {
		super();
		
		this.outBox = outBox;

		this.inBox = inBox;

		this.instrument = instrument;
		this.amount = amount;
	}
	
	
	public DocumentRow( Box outBox, Instrument instrument, float amount) {
		
		this.outBox = outBox;

		this.instrument = instrument;
		this.amount = amount;
	}
	
	public DocumentRow() {
	}

}
