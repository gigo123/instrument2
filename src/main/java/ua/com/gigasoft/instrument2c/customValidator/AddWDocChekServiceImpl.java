package ua.com.gigasoft.instrument2c.customValidator;

import java.util.Optional;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.gigasoft.instrument2c.dao.BoxDAO;
import ua.com.gigasoft.instrument2c.dao.InstrumentDAO;
import ua.com.gigasoft.instrument2c.dao.LocationDAO;
import ua.com.gigasoft.instrument2c.mainModel.Box;
import ua.com.gigasoft.instrument2c.mainModel.Location;
import ua.com.gigasoft.instrument2c.secondModel.DocType;
import ua.com.gigasoft.instrument2c.secondModel.ExDocWEB;

@Component
public class AddWDocChekServiceImpl implements AddWDocChekService {
	@Autowired
	BoxDAO boxDAO;
	@Autowired
	InstrumentDAO instDAO;
	@Autowired
	LocationDAO locDAO;

	@Override
	public boolean checkRow(ExDocWEB docRow, ConstraintValidatorContext context) {
		boolean checkRow = true;
		//DocRowCheckWorker worker= null;
		// checkInParam
		if (docRow.getDocType() == DocType.INDOC) {

			System.out.println("start check");
			try {
				System.out.println(locDAO);
							Optional<Location> location = locDAO.getLocById(Long.parseLong(docRow.getInLocation()));
							System.out.println(location);
							if (location.isPresent()) {
								// docRow.setOutLocation(location.get().);
								Optional<Box> box = boxDAO.getBoxByID(docRow.getInBox());
								if (!box.isPresent()) {
									checkRow = false;
								}
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
		}
		System.out.println(checkRow);
		return checkRow;
	}
}
