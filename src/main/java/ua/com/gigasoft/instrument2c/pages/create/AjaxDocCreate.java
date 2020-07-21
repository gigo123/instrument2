package ua.com.gigasoft.instrument2c.pages.create;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ua.com.gigasoft.instrument2c.support.AjaxResponseDocBox;
import ua.com.gigasoft.instrument2c.support.SearchById;
import ua.com.gigasoft.instrument2c.dao.BoxDAO;
import ua.com.gigasoft.instrument2c.mainModel.Box;




@RestController
public class AjaxDocCreate {
	 
	@Autowired
	private BoxDAO boxDAO;
	
	@PostMapping("/getBoxFilter")
	public ResponseEntity<?> getSearchBoxResultViaAjax(@RequestBody SearchById search, Errors errors) {
		AjaxResponseDocBox result = new AjaxResponseDocBox();
		result.setCode("200");
		String msg = "РѕРє";
		long LocId = search.getBoxId();
		msg = msg + " " + LocId;
		Map<Long, Integer> boxMap = new HashMap<Long, Integer>();
		List<Box> BoxList = boxDAO.getAllBoxByLocation(LocId);
		for (Box box : BoxList) {
			boxMap.put(box.getId(), box.getNumber());
		}
		result.setMsg(msg);
		result.setBoxListId(boxMap);
		return ResponseEntity.ok(result);

	}

}
