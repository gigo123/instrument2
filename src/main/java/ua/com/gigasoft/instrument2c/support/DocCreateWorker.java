package ua.com.gigasoft.instrument2c.support;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import ua.com.gigasoft.instrument2c.mainModel.Location;
import ua.com.gigasoft.instrument2c.secondModel.ExDocWEB;
import ua.com.gigasoft.instrument2c.mainModel.Box;
import ua.com.gigasoft.instrument2c.mainModel.DocModel;
import ua.com.gigasoft.instrument2c.mainModel.ExDoc;
import ua.com.gigasoft.instrument2c.mainModel.Instrument;
import ua.com.gigasoft.instrument2c.mainModel.Storage;
import ua.com.gigasoft.instrument2c.dao.BoxDAO;
import ua.com.gigasoft.instrument2c.dao.DocCatalogDAO;
import ua.com.gigasoft.instrument2c.dao.DocDAO;
import ua.com.gigasoft.instrument2c.dao.InstrumentDAO;
import ua.com.gigasoft.instrument2c.dao.LocationDAO;
import ua.com.gigasoft.instrument2c.dao.StorageDAO;
import ua.com.gigasoft.instrument2c.mainModel.ExDocCatalog;
import ua.com.gigasoft.instrument2c.secondModel.DocType;
import ua.com.gigasoft.instrument2c.secondModel.ExDocTempStore;

public class DocCreateWorker {
	DocCatalogDAO docCatDAO;
	StorageDAO storageDAO;
	DocDAO docDAO;
	BoxDAO boxDAO;
	InstrumentDAO instDAO;
	LocationDAO locDAO;

	private  ExDocTempStore checkOutParam(ExDocWEB docW, int number, DocModel doc) {
		String errorText = "";
		try {
			Optional<Location>  location = locDAO.getLocById(Long.parseLong(docW.getOutLocation()));
			if (location.isPresent()) {
				doc.setOutLocation(location.get());
				Optional<Box> box = boxDAO.getBoxByID(docW.getOutBox());
				if (box.isPresent()) {
					doc.setOutBox(box.get());
				} else {
					errorText = "<li>РЅРµРїСЂР°РІРёР»СЊРЅР°СЏ РІРёРґР°СЋС‰Р°СЏ СЏС‡РµР№РєР° РІ СЃС‚СЂРѕРєРµ " + number + "</li>";
				}
			} else {
				errorText = "<li>РЅРµРїСЂР°РІРёР»СЊРЅРѕРµ РјРµСЃС‚Рѕ  РІРёРґР°С‡Рё РІ СЃС‚РѕРєРµ " + number + "</li>";
			}
		} catch (Exception e) {
			errorText = "<li>РЅРµРїСЂР°РІРёР»СЊРЅРѕРµ РјРµСЃС‚Рѕ РІРёРґР°С‡Рё РІ СЃС‚РѕРєРµ " + number + "</li>";
		}

		ExDocTempStore tempDoc = new ExDocTempStore(errorText, doc, 0);
		return tempDoc;
	}
	
	private String writeExDoc(DocModel doc, long catId, long outStorageId, DocType docType) {
		try {

			long inStorageId = 0;
			Storage storage = null;
			List<Storage> storeList = null;
			float amount;
			ExDoc exDoc = null;
			if (docType == DocType.EXDOC || docType == DocType.INDOC) {

				if (docType == DocType.EXDOC) {
					exDoc = (ExDoc) doc;
					storage = storageDAO.getStorageByID(outStorageId);
					amount = storage.getAmount() - doc.getAmount();
					if (amount <= 0.0) {
						storageDAO.deleteStorage(outStorageId);
					} else {
						storage.setAmount(amount);
						storageDAO.updateStorage(outStorageId, storage);
					}
					storeList = storageDAO.getStorageByBox(exDoc.getInBox().getId());
					doc.setCatalogId(docCatDAO.getDocCatalogById(catId));
				}
				if (docType == DocType.INDOC) {
					storeList = storageDAO.getStorageByBox(doc.getOutBox().getId());
					doc.setCatalogId(docCatDAO.getDocCatalogById(catId));
				}
				Instrument instrument = doc.getInstrument();
				boolean hasInstrument = false;
				for (int i = 0; i < storeList.size(); i++) {
					Instrument tempInst = storeList.get(i).getInstrument();
					if (tempInst != null) {
						if (tempInst.getId() == instrument.getId()) {
							hasInstrument = true;
							inStorageId = storeList.get(i).getId();
							break;
						}
					}
				}
				if (hasInstrument) {
					storage = storageDAO.getStorageByID(inStorageId);
					amount = storage.getAmount() + doc.getAmount();
					storage.setAmount(amount);
					storageDAO.updateStorage(inStorageId, storage);
				} else {
					if (docType == DocType.EXDOC) {
						Box box = doc.getOutBox();
						List<Storage> tempStoreList = storageDAO.getStorageByBox(box.getId());
						if (tempStoreList.size() == 0) {
							box.setNotEmpty(true);
							boxDAO.updateBox(box.getId(), box);
						}
						Storage newInStorage = new Storage(exDoc.getInBox(), instrument, doc.getAmount());
						storageDAO.createStorage(newInStorage);
					}
					if (docType == DocType.INDOC) {
						Storage newInStorage = new Storage(doc.getOutBox(), instrument, doc.getAmount());
						storageDAO.createStorage(newInStorage);
					}
				}
				float instumentNumber = instrument.getTotalNumber() + doc.getAmount();
				instrument.setTotalNumber(instumentNumber);
				instDAO.updateInstrument(instrument);
			}
			if (docType == DocType.OUTDOC) {
				doc.setCatalogId(docCatDAO.getDocCatalogById(catId));
				storage = storageDAO.getStorageByID(outStorageId);
				amount = storage.getAmount() - doc.getAmount();
				if (amount <= 0.0) {
					storageDAO.deleteStorage(outStorageId);
					Box box = doc.getOutBox();
					List<Storage> tempStoreList = storageDAO.getStorageByBox(box.getId());
					if (tempStoreList.size() == 0) {
						box.setNotEmpty(false);
						boxDAO.updateBox(box.getId(), box);
					}
				} else {
					storage.setAmount(amount);
					storageDAO.updateStorage(outStorageId, storage);
				}

				float instumentNumber = doc.getInstrument().getTotalNumber() - doc.getAmount();
				doc.getInstrument().setTotalNumber(instumentNumber);
				instDAO.updateInstrument(doc.getInstrument());
			}
			docDAO.createDoc(doc, docType);
		} catch (Exception e) {
			return "<li>РѕС€С‹Р±РєР° Р±Р°Р·Рё РґР°РЅРЅРёС… </li>";
		}
		return "РґРѕРєСѓРјРµРЅС‚ СЃРѕР·РґР°РЅ";

	}

	private String writeDocCatolog(DocType docType, int tInstr, float tAmount) {
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

	private static float calcTotalAmount(List<ExDocTempStore> docTempList) {
		float tAmount = 0;
		for (ExDocTempStore exDocTempStore : docTempList) {
			tAmount += exDocTempStore.getDoc().getAmount();
		}
		return tAmount;

	}
}
