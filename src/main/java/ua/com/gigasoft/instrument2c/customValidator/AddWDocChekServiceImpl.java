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
import ua.com.gigasoft.instrument2c.support.DocRowCheckWorker;
import ua.com.gigasoft.instrument2c.support.InDocRowCheck;

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
		System.out.println("Hello from  roe validaror");
		
		DocRowCheckWorker worker= null;
		// checkInParam
		System.out.println(docRow.getDocType());
		if (docRow.getDocType() == DocType.INDOC) {
			worker = new InDocRowCheck();
		}
		System.out.println(worker);
		if(worker==null) {
			return false;
		}
		return worker.chekRow(docRow, context);

	}
}
