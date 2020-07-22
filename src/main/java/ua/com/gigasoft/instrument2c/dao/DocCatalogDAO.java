package ua.com.gigasoft.instrument2c.dao;

import java.time.LocalDate;
import java.util.List;

import ua.com.gigasoft.instrument2c.mainModel.DocCatalog;

public interface DocCatalogDAO {

	public boolean createDocCatalog(DocCatalog DocCatalog);
	public DocCatalog getDocCatalogById(long id);
	public List<DocCatalog> getDocCatalogByDate(LocalDate date);
	public List<DocCatalog> getDocCatalogByNumber(int number);
	public List<DocCatalog> getDocCatalogByYear(int year);
	public List<DocCatalog> getAllDoc();
	public List<Integer> getDocCatalogByYearN(int year);
	public DocCatalog getDocCatalogBySnumber(String numberString);
	public boolean deleteDocCatalogDoc(long id);
	public boolean hasError();

}
