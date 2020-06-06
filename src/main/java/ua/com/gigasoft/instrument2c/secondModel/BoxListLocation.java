package ua.com.gigasoft.instrument2c.secondModel;



import ua.com.gigasoft.instrument2c.customValidator.AddBoxNumer;
@AddBoxNumer()
public class BoxListLocation {
	private String startNum;
	private String endNum;
	private String manyBox;
	private String locationWB;
	private int number;

	public String getManyBox() {
		return manyBox;
	}

	@Override
	public String toString() {
		return "BoxListLocation [" + (startNum != null ? "startNum=" + startNum + ", " : "")
				+ (endNum != null ? "endNum=" + endNum + ", " : "")
				+ (manyBox != null ? "manyBox=" + manyBox + ", " : "")
				+ (locationWB != null ? "locationWB=" + locationWB : "") + "]";
	}

	public void setManyBox(String manyBox) {
		this.manyBox = manyBox;
	}

	public String getStartNum() {
		return startNum;
	}

	public String getLocationWB() {
		return locationWB;
	}

	public void setLocationWB(String locationWB) {
		this.locationWB = locationWB;
	}

	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}

	public String getEndNum() {
		return endNum;
	}

	public void setEndNum(String endNum) {
		this.endNum = endNum;
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
