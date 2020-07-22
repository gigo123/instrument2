package ua.com.gigasoft.instrument2c.database;

import org.springframework.data.jpa.repository.JpaRepository;


import ua.com.gigasoft.instrument2c.mainModel.InDocCatalog;

public interface InDocCatalogRepository extends JpaRepository<InDocCatalog,Long> {

}
