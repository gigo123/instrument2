package ua.com.gigasoft.instrument2c.database;

import org.springframework.data.jpa.repository.JpaRepository;


import ua.com.gigasoft.instrument2c.mainModel.DocCatalog;

public interface DocCatalogRepository extends JpaRepository<DocCatalog,Long> {

}
