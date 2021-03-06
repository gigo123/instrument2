package ua.com.gigasoft.instrument2c.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.com.gigasoft.instrument2c.mainModel.Document;
import ua.com.gigasoft.instrument2c.secondModel.DocType;
@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {

	@Query("SELECT DC FROM Document  DC WHERE  DC.year = :year and DC.docType = :docType")
	public List<Document> getDocCatByYear(@Param("year")int year, @Param("docType")DocType docType);
	@Query("SELECT DC.number FROM Document  DC WHERE  DC.year = :year and DC.docType = :docType")
	public List<Integer> getDocCatByYearN(@Param("year")int year, @Param("docType")DocType docType);
}
