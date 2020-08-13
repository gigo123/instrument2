package ua.com.gigasoft.instrument2c.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.com.gigasoft.instrument2c.mainModel.DocCatalog;
import ua.com.gigasoft.instrument2c.secondModel.DocType;
@Repository
public interface DocCatalogRepository extends JpaRepository<DocCatalog,Long> {

	@Query("SELECT DC FROM doc_catalog  DC WHERE  DC.year = :year and DC.docType = :docType")
	public List<DocCatalog> getDocCatByYear(@Param("year")int year, @Param("docType")DocType docType);
	@Query("SELECT DC.year FROM doc_catalog  DC WHERE  DC.year = :year and DC.docType = :docType")
	public List<Integer> getDocCatByYearN(@Param("year")int year, @Param("docType")DocType docType);
}
