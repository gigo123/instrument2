package ua.com.gigasoft.instrument2c.mainModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.Size;

import ua.com.gigasoft.instrument2c.customValidator.AddLocationCheckServiceImpl;
import ua.com.gigasoft.instrument2c.customValidator.AddLocationValidator;

@Entity
@Table(name = "location") 
public class Location {
	 @Id 
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Size(min = 4, max = 20, message = "имя должно бить от 4 до 20 символов")
	@AddLocationValidator(service = AddLocationCheckServiceImpl.class )
	private String name;
	private boolean boxes;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isBoxes() {
		return boxes;
	}
	public void setBoxes(boolean boxes) {
		this.boxes = boxes;
	}
	public Location() {
	}
	public Location(String name, boolean boxes) {
		super();
		this.name = name;
		this.boxes = boxes;
	}
	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", boxes=" + boxes + "]";
	}
	

}
