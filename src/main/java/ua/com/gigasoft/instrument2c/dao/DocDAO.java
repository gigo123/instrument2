package ua.com.gigasoft.instrument2c.dao;

import java.util.List;


import ua.com.gigasoft.instrument2c.mainModel.DocModel;
import ua.com.gigasoft.instrument2c.mainModel.ExDoc;
import ua.com.gigasoft.instrument2c.secondModel.DocType;

public interface DocDAO {
	public boolean createDoc(DocModel Doc, DocType docType);
	public ExDoc getDocById(long id,DocType docType);
	public List<DocModel> getDocByCatolog(long id, DocType docType);
	public List<DocModel> getDocByInstrum(long id,DocType docType);
	public List<DocModel> getDocByBox(long id,DocType docType);
	public List<DocModel> getDocByLocation(long id,DocType docType);
	public boolean deleteDoc(long id,DocType docType);
	public boolean hasError();

}
