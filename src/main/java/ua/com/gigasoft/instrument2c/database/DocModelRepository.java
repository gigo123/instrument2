package ua.com.gigasoft.instrument2c.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.com.gigasoft.instrument2c.mainModel.DocModel;

@Repository
public interface DocModelRepository extends JpaRepository<DocModel,Long>{

}
