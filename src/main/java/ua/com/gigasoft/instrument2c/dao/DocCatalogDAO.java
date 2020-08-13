package ua.com.gigasoft.instrument2c.dao;

import java.time.LocalDate;
import java.util.List;

import ua.com.gigasoft.instrument2c.mainModel.DocCatalog;
import ua.com.gigasoft.instrument2c.secondModel.DocType;

public interface DocCatalogDAO {

	public boolean createDocCatalog(DocCatalog DocCatalog);
	public DocCatalog getDocCatalogById(long id);
	public List<DocCatalog> getDocCatalogByDate(LocalDate date, DocType docType);
	public List<DocCatalog> getDocCatalogByNumber(int number,DocType docType);
	public List<DocCatalog> getDocCatalogByYear(int year,DocType docType);
	public List<DocCatalog> getAllDoc();
	public List<DocCatalog> getAllDocByType(DocType docType);
	public List<Integer> getDocCatalogByYearN(int year,DocType docType);
	public DocCatalog getDocCatalogBySnumber(String numberString , DocType docType);
	public boolean deleteDocCatalogDoc(long id);
	public boolean hasError();

}
