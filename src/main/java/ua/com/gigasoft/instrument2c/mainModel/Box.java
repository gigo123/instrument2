package ua.com.gigasoft.instrument2c.mainModel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;


@Entity
@Table(name = "box")  
public class Box {
	 @Id 
	 @Column(name = "id")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	 @ManyToOne(fetch=FetchType.LAZY, 
     cascade=CascadeType.ALL)
	 @JoinColumn (name="location")
		private Location location;
	@PositiveOrZero (message = "ячейка должна бить положительным числом")
	private int number;
	
    

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Box(int number, Location location) {
		super();
		this.number = number;
		this.location = location;
	}
	public Box() {
	}
	@Override
	public String toString() {
		return "Box [id=" + id + ", number=" + number + ", location=" + location + "]";
	}
	
}
