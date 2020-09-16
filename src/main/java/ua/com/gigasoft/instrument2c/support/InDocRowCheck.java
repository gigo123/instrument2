package ua.com.gigasoft.instrument2c.support;

import java.util.Optional;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ua.com.gigasoft.instrument2c.dao.BoxDAO;
import ua.com.gigasoft.instrument2c.dao.InstrumentDAO;
import ua.com.gigasoft.instrument2c.dao.LocationDAO;
import ua.com.gigasoft.instrument2c.mainModel.Box;
import ua.com.gigasoft.instrument2c.mainModel.Location;
import ua.com.gigasoft.instrument2c.secondModel.DocType;
import ua.com.gigasoft.instrument2c.secondModel.ExDocWEB;

public class InDocRowCheck  implements DocRowCheckWorker{
	@Autowired
	BoxDAO boxDAO;
	@Autowired
	InstrumentDAO instDAO;
	@Autowired
	LocationDAO locDAO;
	
	@Override
	public boolean chekRow(ExDocWEB docRow, ConstraintValidatorContext context) {
		boolean checkRow = true;
		try {
						Optional<Location> location = locDAO.getLocById(Long.parseLong(docRow.getInLocation()));
						if (location.isPresent()) {
							// docRow.setOutLocation(location.get().);
							Optional<Box> box = boxDAO.getBoxByID(docRow.getInBox());
							if (!box.isPresent()) {
								checkRow = false;
							}
							System.out.println("doc i ok");
						} else {
							// errorText = "<li>РЅРµРїСЂР°РІРёР»СЊРЅРѕРµ РјРµСЃС‚Рѕ РїСЂРёРµРјР° РІ
							// СЃС‚РѕРєРµ " ;
							checkRow = false;
						}
			

					} catch (Exception e) {
						// errorText = "<li>РЅРµРїСЂР°РІРёР»СЊРЅРѕРµ РјРµСЃС‚Рѕ РїСЂРёРµРјР° РІ
						// СЃС‚РѕРєРµ " ;
						checkRow = false;
					}
					return checkRow;
	}

}
