package ua.com.gigasoft.instrument2c.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.com.gigasoft.instrument2c.mainModel.DocCatalog;
@Repository
public interface DocCatalogRepository extends JpaRepository<DocCatalog,Long> {

}
