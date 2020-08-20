package ua.com.gigasoft.instrument2c.secondModel;


import javax.validation.constraints.PositiveOrZero;

import ua.com.gigasoft.instrument2c.customValidator.AddBoxNumerValidator;

public class BoxListLocation {
	@PositiveOrZero (message = "ячейка должна бить положительным числом")
	private int startNum =0;
	@PositiveOrZero (message = "ячейка должна бить положительным числом")
	private int endNum =0;
	private String manyBox ="O";
	private long locationWB;
	
	@PositiveOrZero (message = "ячейка должна бить положительным числом")
	private int number;

	


	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	
	public String getManyBox() {
		return manyBox;
	}

	public void setManyBox(String manyBox) {
		this.manyBox = manyBox;
	}

	public long getLocationWB() {
		return locationWB;
	}

	public void setLocationWB(long locationWB) {
		this.locationWB = locationWB;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public BoxListLocation() {

	}
	

}
