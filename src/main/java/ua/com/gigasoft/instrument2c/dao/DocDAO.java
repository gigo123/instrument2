package ua.com.gigasoft.instrument2c.dao;

import java.util.List;


import ua.com.gigasoft.instrument2c.mainModel.DocModel;
import ua.com.gigasoft.instrument2c.mainModel.ExDoc;
import ua.com.gigasoft.instrument2c.support.DocType;

public interface DocDAO {
	public boolean createExDoc(DocModel Doc, DocType docType);
	public ExDoc getExDocById(long id,DocType docType);
	public List<DocModel> getExDocByCatolog(long id, DocType docType);
	public List<DocModel> getExDocByInstrum(long id,DocType docType);
	public List<DocModel> getExDocByBox(long id,DocType docType);
	public List<DocModel> getExDocByLocation(long id,DocType docType);
	public boolean deleteExDoc(long id,DocType docType);
	public boolean hasError();
	public void closeConection();

}
