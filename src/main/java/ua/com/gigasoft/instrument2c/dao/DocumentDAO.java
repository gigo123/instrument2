package ua.com.gigasoft.instrument2c.dao;

import java.time.LocalDate;
import java.util.List;

import ua.com.gigasoft.instrument2c.mainModel.Document;
import ua.com.gigasoft.instrument2c.secondModel.DocType;

public interface DocumentDAO {

	public boolean createDocument(Document Document);
	public Document getDocumentById(long id);
	public List<Document> getDocumentByDate(LocalDate date, DocType docType);
	public List<Document> getDocumentByNumber(int number,DocType docType);
	public List<Document> getDocumentByYear(int year,DocType docType);
	public List<Document> getAllDoc();
	public List<Document> getAllDocByType(DocType docType);
	public List<Integer> getDocumentByYearN(int year,DocType docType);
	public Document getDocumentBySnumber(String numberString , DocType docType);
	public boolean deleteDocumentDoc(long id);
	public boolean hasError();

}
