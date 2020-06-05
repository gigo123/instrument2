package ua.com.gigasoft.instrument2c.database;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.gigasoft.instrument2c.mainModel.Location;

public interface LocationRepository extends JpaRepository<Location,Long> {

}
