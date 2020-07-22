package ua.com.gigasoft.instrument2c.support;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import ua.com.gigasoft.instrument2c.dao.DocCatalogDAO;
import ua.com.gigasoft.instrument2c.mainModel.ExDocCatalog;
import ua.com.gigasoft.instrument2c.mainModel.InDocCatalog;
import ua.com.gigasoft.instrument2c.mainModel.OutDocCatalog;
import ua.com.gigasoft.instrument2c.secondModel.DocType;
import ua.com.gigasoft.instrument2c.secondModel.ExDocTempStore;


public class DocCreateWorker {
	DocCatalogDAO docCatDAO;
	
	
	 private String writeDocCatolog(DocType docType,int tInstr,float tAmount) {
		StringBuilder errorText = new StringBuilder("");
		LocalDate date = LocalDate.now();
		int year = date.getYear();
		String numberString = null;
	
			List<Integer> numberList = docCatDAO.getDocCatalogByYearN(year);
			Collections.sort(numberList);
			int lastNumber;
			if (numberList.size() == 0) {
				lastNumber = 1;
			} else {
				lastNumber = numberList.get(numberList.size() - 1);
				lastNumber++;
			}
			numberString = "" + year + "-" + lastNumber;
			ExDocCatalog exCat = new ExDocCatalog(year, lastNumber, numberString, date);
			exCat.setTotalInstrum(tInstr);
			exCat.setTotalAmount(tAmount);
			if (!docCatDAO.createDocCatalog(exCat)) {
				errorText.append("<li>РѕС€С‹Р±РєР° Р±Р°Р·Рё РґР°РЅРЅРёС… </li>");
			}
			
		String errString = errorText.toString();
		if (errString.equals("")) {
			return numberString;
		} else {
			return errString;
		}

	}
	
	
	private static float calcTotalAmount  (List<ExDocTempStore> docTempList){
		float tAmount=0;
		for (ExDocTempStore exDocTempStore : docTempList) {
			tAmount+=exDocTempStore.getDoc().getAmount();
		}
		return tAmount;
		
	}
}
