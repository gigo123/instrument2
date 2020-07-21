package ua.com.gigasoft.instrument2c.support;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonView;


public class AjaxResponseDocBox  extends AjaxResponse{
	
  
    Map<Long, Integer>  boxListId;

	public Map<Long, Integer> getBoxListId() {
		return boxListId;
	}

	public void setBoxListId(Map<Long, Integer> boxListId) {
		this.boxListId = boxListId;
	}

    
    

}
