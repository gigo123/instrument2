package ua.com.gigasoft.instrument2c.mainModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "box")  
public class Box {
	 @Id 
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@PositiveOrZero (message = "ячейка должна бить положительным числом")
	private int number;
	
	@OneToOne(cascade = CascadeType.ALL)
   @PrimaryKeyJoinColumn
	private Location location;

	private boolean notEmpty;
	
	
	public boolean isNotEmpty() {
		return notEmpty;
	}
	public void setNotEmpty(boolean notEmpty) {
		this.notEmpty = notEmpty;
	}
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
