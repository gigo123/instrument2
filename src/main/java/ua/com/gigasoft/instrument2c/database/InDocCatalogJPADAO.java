package ua.com.gigasoft.instrument2c.database;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.gigasoft.instrument2c.dao.DocCatalogDAO;
import ua.com.gigasoft.instrument2c.mainModel.DocCatalog;
import ua.com.gigasoft.instrument2c.mainModel.InDocCatalog;

@Service
public class InDocCatalogJPADAO  implements DocCatalogDAO{

	@Autowired
	private InDocCatalogRepository docRepo;
	
	
	@Override
	public boolean createDocCatalog(DocCatalog DocCatalog) {
		docRepo.save((InDocCatalog)DocCatalog);
		return true;
	}

	@Override
	public DocCatalog getDocCatalogById(long id) {
		docRepo.findById(id);
		return null;
	}

	@Override
	public List<DocCatalog> getDocCatalogByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocCatalog> getDocCatalogByNumber(int number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocCatalog> getDocCatalogByYear(int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocCatalog> getAllDoc() {
		List<DocCatalog> docList =  docRepo.findAll().stream()
				    .map(e -> (DocCatalog) e)
				    .collect(Collectors.toList());
		 return docList;
	}

	@Override
	public List<Integer> getDocCatalogByYearN(int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocCatalog getDocCatalogBySnumber(String numberString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteDocCatalogDoc(long id) {
		docRepo.deleteById(id);
		return false;
	}

	@Override
	public boolean hasError() {
		// TODO Auto-generated method stub
		return false;
	}

}
