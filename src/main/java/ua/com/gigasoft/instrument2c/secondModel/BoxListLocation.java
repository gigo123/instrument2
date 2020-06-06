package ua.com.gigasoft.instrument2c.secondModel;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.com.gigasoft.instrument2c.dao.LocationDAO;
import ua.com.gigasoft.instrument2c.mainModel.Box;
import ua.com.gigasoft.instrument2c.mainModel.Location;

public class BoxListLocation extends Box{
	private Map<Long, String> locationWB;
	private String startNum;
	private String endNum;
	private String manyBox;
	@Autowired
	private LocationDAO locDAO;
	
	public String getManyBox() {
		return manyBox;
	}

	public void setManyBox(String manyBox) {
		this.manyBox = manyBox;
	}

	public String getStartNum() {
		return startNum;
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

	

	public Map<Long, String> getLocationWB() {
		return locationWB;
	}

	public void setLocationWB(Map<Long, String> locationWB) {
		this.locationWB = locationWB;
	}

	@Override
	public String toString() {
		return "BoxListLocation [locationWB=" + locationWB + "]";
	}

	public BoxListLocation() {

		Map<Long, String> locationWB = new HashMap<Long, String>();
		List<Location> locList = locDAO.getAllLocatinWB();
		for (Location location : locList) {
			locationWB.put(location.getId(), location.getName());
		}
	}
	

}
