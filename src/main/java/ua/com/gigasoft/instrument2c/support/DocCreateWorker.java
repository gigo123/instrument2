package ua.com.gigasoft.instrument2c.support;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import ua.com.gigasoft.instrument2c.mainModel.Location;
import ua.com.gigasoft.instrument2c.secondModel.ExDocWEB;
import ua.com.gigasoft.instrument2c.secondModel.ExDocWEBList;
import ua.com.gigasoft.instrument2c.mainModel.Box;
import ua.com.gigasoft.instrument2c.mainModel.DocCatalog;
import ua.com.gigasoft.instrument2c.mainModel.DocModel;
import ua.com.gigasoft.instrument2c.mainModel.Instrument;
import ua.com.gigasoft.instrument2c.mainModel.Storage;
import ua.com.gigasoft.instrument2c.dao.BoxDAO;
import ua.com.gigasoft.instrument2c.dao.DocCatalogDAO;
import ua.com.gigasoft.instrument2c.dao.DocDAO;
import ua.com.gigasoft.instrument2c.dao.InstrumentDAO;
import ua.com.gigasoft.instrument2c.dao.LocationDAO;
import ua.com.gigasoft.instrument2c.dao.StorageDAO;
import ua.com.gigasoft.instrument2c.secondModel.DocType;
import ua.com.gigasoft.instrument2c.secondModel.ExDocTempStore;

public class DocCreateWorker {
	DocCatalogDAO docCatDAO;
	StorageDAO storageDAO;
	DocDAO docDAO;
	BoxDAO boxDAO;
	InstrumentDAO instDAO;
	LocationDAO locDAO;

	public String createExDocUnwrap(ExDocWEBList docListWrap, DocType docType) {
		List<ExDocWEB> docList = docListWrap.getDocList();
		StringBuilder errorText = new StringBuilder("<ul>");
		List<ExDocTempStore> docTempList = new ArrayList<ExDocTempStore>();
		for (int i = 0; i < docList.size(); i++) {
			ExDocTempStore tempDoc = makeExDoc(docList.get(i), i, docType);
			errorText.append(tempDoc.getErrorString());
			docTempList.add(makeExDoc(docList.get(i), i, docType));
		}
		errorText.append("</ul>");
		String errString = errorText.toString();
		if (errString.equals("<ul></ul>")) {
			String error = writeDocCatolog(docType, docTempList.size(), calcTotalAmount(docTempList));
			/*
			 * if (!error.equals("<li>РѕС€С‹Р±РєР° Р±Р°Р·Рё РґР°РЅРЅРёС… </li>")) { long
			 * catalogId = 0; catalogId = docCatDAO.getDocCatalogBySnumber(error).getId();
			 * for (ExDocTempStore exDocTempStore : docTempList) { error +=
			 * writeExDoc(exDocTempStore.getDoc(), catalogId,
			 * exDocTempStore.getOutStorageId(), docType); } }
			 */
			return error;
		} else {
			return errString;
		}

	}

	private ExDocTempStore makeExDoc(ExDocWEB docW, int number, DocType docType) {
		String errorText = "";
		number++;
		DocModel doc = new DocModel();
		doc.setDocType(docType);
		ExDocTempStore tempDoc = null;
		if (docType == DocType.EXDOC) {

			tempDoc = checkInParam(docW, number, doc);
			errorText = tempDoc.getErrorString();
			tempDoc = checkOutParam(docW, number, tempDoc.getDoc());
			errorText += tempDoc.getErrorString();
		}
		if (docType == DocType.INDOC) {
			tempDoc = checkOutParam(docW, number, doc);
			errorText += tempDoc.getErrorString();
		}
		if (docType == DocType.OUTDOC) {
			tempDoc = checkOutParam(docW, number, doc);
			errorText += tempDoc.getErrorString();
		}
		if (errorText.equals("")) {
			tempDoc = checkInstrument(docW, number, tempDoc.getDoc(), docType);
			errorText += tempDoc.getErrorString();
			if (errorText.equals("")) {
				tempDoc.getDoc().setAmount(docW.getAmount());
				errorText = tempDoc.getErrorString();
				if (docType == DocType.EXDOC) {
					errorText += checkBox(tempDoc.getDoc());
				}
				tempDoc.setErrorString(errorText);
				return tempDoc;
			} else {
				return new ExDocTempStore(errorText, doc, 0);
			}
		} else {
			return new ExDocTempStore(errorText, doc, 0);
		}
	}

	private String checkBox(DocModel doc) {
		long inID = doc.getInBox().getId();
		long outId = doc.getOutBox().getId();
		if (inID == outId) {
			return "<li>РѕРґРёРЅР°РєРѕРІРёРµ  СЏС‡РµР№РєРё РїСЂРёРµРјР° Рё РІРёРґР°С‡Рё</li>";
		}
		return "";

	}

	private ExDocTempStore checkInstrument(ExDocWEB docW, int number, DocModel doc, DocType docType) {
		StringBuilder errorText = new StringBuilder("");
		Box box = doc.getOutBox();
		long storageId = 0;
		Optional<Instrument> instrumentOpt = instDAO.getInstrumentByid(Long.parseLong(docW.getInstrument()));
		if (instrumentOpt.isPresent()) {
			Instrument instrument = instrumentOpt.get();
			if (docType == DocType.EXDOC || docType == DocType.OUTDOC) {
				List<Storage> storeList = storageDAO.getStorageByBox(box.getId());
				boolean hasInstrument = false;
				for (int i = 0; i < storeList.size(); i++) {
					Instrument tempInst = storeList.get(i).getInstrument();
					if (tempInst != null) {
						if (tempInst.getId() == instrument.getId()) {
							hasInstrument = true;
							storageId = storeList.get(i).getId();
						}
					}
				}
				if (hasInstrument) {
					Storage storage = storageDAO.getStorageByID(storageId);
					float instLeft = storage.getAmount() - docW.getAmount();
					if (instLeft > 0.0) {
						doc.setInstrument(instrument);
					} else {
						errorText.append(
								"<li>РЅРµРґРѕСЃС‚Р°С‡РЅРѕ РёРЅСЃС‚СЂСѓРјРµРЅС‚Р° РґР»СЏ РІРёРґР°С‡Рё  РІ СЃС‚СЂРѕРєРµ "
										+ number + "</li>");
					}

				} else {
					errorText.append("<li>РЅРµС‚ РёРЅСЃС‚СЂСѓРјРµРЅС‚Р° РІ СЏС‡РµРєРµ РІРёРґР°С‡Рё  РІ СЃС‚СЂРѕРєРµ "
							+ number + "</li>");
				}

			}
			if (docType == DocType.INDOC) {
				doc.setInstrument(instrument);
			}

		} else {
			errorText.append("<li>РЅРµ РїСЂР°РІРёР»СЊРЅРёР№ РёРЅСЃС‚СЂСѓРјРµРЅС‚ РІ СЃС‚СЂРѕРєРµ " + number + " </li>");
		}
		ExDocTempStore tempDoc = new ExDocTempStore(errorText.toString(), doc, storageId);
		return tempDoc;
	}

	private ExDocTempStore checkInParam(ExDocWEB docW, int number, DocModel doc) {
		String errorText = "";
		try {
			Optional<Location> location = locDAO.getLocById(Long.parseLong(docW.getOutLocation()));
			if (location.isPresent()) {
				doc.setOutLocation(location.get());
				Optional<Box> box = boxDAO.getBoxByID(docW.getInBox());
				if (box.isPresent()) {
					doc.setInBox(box.get());
				} else {
					errorText = "<li>РЅРµРїСЂР°РІРёР»СЊРЅР°СЏ РїСЂРёРЅРёРјР°СЋС‰Р°СЏ СЏС‡РµР№РєР° РІ СЃС‚СЂРѕРєРµ "
							+ number + "</li>";
				}
			} else {
				errorText = "<li>РЅРµРїСЂР°РІРёР»СЊРЅРѕРµ РјРµСЃС‚Рѕ РїСЂРёРµРјР° РІ СЃС‚РѕРєРµ " + number + "</li>";
			}
		} catch (Exception e) {
			errorText = "<li>РЅРµРїСЂР°РІРёР»СЊРЅРѕРµ РјРµСЃС‚Рѕ РїСЂРёРµРјР° РІ СЃС‚РѕРєРµ " + number + "</li>";
		}

		ExDocTempStore tempDoc = new ExDocTempStore(errorText, doc, 0);
		return tempDoc;
	}

	private ExDocTempStore checkOutParam(ExDocWEB docW, int number, DocModel doc) {
		String errorText = "";
		try {
			Optional<Location> location = locDAO.getLocById(Long.parseLong(docW.getOutLocation()));
			if (location.isPresent()) {
				doc.setOutLocation(location.get());
				Optional<Box> box = boxDAO.getBoxByID(docW.getOutBox());
				if (box.isPresent()) {
					doc.setOutBox(box.get());
				} else {
					errorText = "<li>РЅРµРїСЂР°РІРёР»СЊРЅР°СЏ РІРёРґР°СЋС‰Р°СЏ СЏС‡РµР№РєР° РІ СЃС‚СЂРѕРєРµ " + number
							+ "</li>";
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
			DocModel exDoc = new DocModel();
			exDoc.setDocType(docType);
			if (docType == DocType.EXDOC || docType == DocType.INDOC) {

				if (docType == DocType.EXDOC) {
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

		List<Integer> numberList = docCatDAO.getDocCatalogByYearN(year, docType);
		Collections.sort(numberList);
		int lastNumber;
		if (numberList.size() == 0) {
			lastNumber = 1;
		} else {
			lastNumber = numberList.get(numberList.size() - 1);
			lastNumber++;
		}
		numberString = "" + year + "-" + lastNumber;
		DocCatalog exCat = new DocCatalog(year, lastNumber, numberString, date, docType);
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
