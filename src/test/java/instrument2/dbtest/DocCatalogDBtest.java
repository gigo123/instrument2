package instrument2.dbtest;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.com.gigasoft.instrument2c.database.DocCatalogRepository;
import ua.com.gigasoft.instrument2c.mainModel.DocCatalog;
import ua.com.gigasoft.instrument2c.secondModel.DocType;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ua.com.gigasoft.instrument2c.Instrument2cApplication.class)
@DataJpaTest
public class DocCatalogDBtest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private DocCatalogRepository DCRepository;

	@Test
	public void whenFindbYear() {
		// given
		LocalDate date = LocalDate.now();
		DocCatalog docCat = new DocCatalog(2020, 1, "2020-1", date, DocType.INDOC);
		entityManager.persist(docCat);
		entityManager.flush();

		// when

		List<DocCatalog> docCat2 = DCRepository.getDocCatByYear(2020, DocType.INDOC);
		// then
		assert (docCat2.size() == 1);
		List<Integer> docCat3 = DCRepository.getDocCatByYearN(2020, DocType.INDOC);
		assert (docCat3.size() == 1);
	}

}
