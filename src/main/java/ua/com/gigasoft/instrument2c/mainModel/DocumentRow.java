package ua.com.gigasoft.instrument2c.mainModel;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.PositiveOrZero;

@Embeddable
public class DocumentRow {
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
