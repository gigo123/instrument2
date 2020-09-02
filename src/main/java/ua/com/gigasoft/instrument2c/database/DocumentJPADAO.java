package ua.com.gigasoft.instrument2c.database;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.gigasoft.instrument2c.dao.DocumentDAO;
import ua.com.gigasoft.instrument2c.mainModel.Document;
import ua.com.gigasoft.instrument2c.secondModel.DocType;

@Service
public class DocumentJPADAO  implements DocumentDAO{

	@Autowired
	private DocumentRepository docRepo;
	
	
	@Override
	public boolean createDocument(Document Document ) {
		docRepo.save((Document)Document);
		return true;
	}

	@Override
	public Document getDocumentById(long id) {
		docRepo.findById(id);
		return null;
	}

	@Override
	public List<Document> getDocumentByDate(LocalDate date , DocType docType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Document> getDocumentByNumber(int number ,DocType docType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Document> getDocumentByYear(int year, DocType docType) {
			return docRepo.getDocCatByYear(year, docType);
		
	}

	@Override
	public List<Document> getAllDoc() {
		List<Document> docList =  docRepo.findAll().stream()
				    .map(e -> (Document) e)
				    .collect(Collectors.toList());
		 return docList;
	}

	@Override
	public List<Integer> getDocumentByYearN(int year ,DocType docType) {
		
		return docRepo.getDocCatByYearN(year, docType);
	}

	@Override
	public Document getDocumentBySnumber(String numberString ,DocType docType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteDocumentDoc(long id) {
		docRepo.deleteById(id);
		return false;
	}

	@Override
	public boolean hasError() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Document> getAllDocByType(DocType docType) {
		// TODO Auto-generated method stub
		return null;
	}

}
