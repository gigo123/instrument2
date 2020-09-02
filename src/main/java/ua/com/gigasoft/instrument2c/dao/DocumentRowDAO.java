package ua.com.gigasoft.instrument2c.dao;

import java.util.List;

import ua.com.gigasoft.instrument2c.mainModel.DocumentRow;
import ua.com.gigasoft.instrument2c.secondModel.DocType;

public interface DocumentRowDAO {
	public boolean createDoc(DocumentRow Doc, DocType docType);
	public DocumentRow getDocById(long id,DocType docType);
	public List<DocumentRow> getDocByCatolog(long id, DocType docType);
	public List<DocumentRow> getDocByInstrum(long id,DocType docType);
	public List<DocumentRow> getDocByBox(long id,DocType docType);
	public List<DocumentRow> getDocByLocation(long id,DocType docType);
	public boolean deleteDoc(long id,DocType docType);
	public boolean hasError();

}
