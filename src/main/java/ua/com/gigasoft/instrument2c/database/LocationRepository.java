package ua.com.gigasoft.instrument2c.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.gigasoft.instrument2c.mainModel.Location;

public interface LocationRepository extends JpaRepository<Location,Long> {

	@Query("SELECT loc FROM Location loc WHERE loc.boxes=true")
	public List<Location> getAllLocationWB();
}
