package ua.com.gigasoft.instrument2c.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.com.gigasoft.instrument2c.mainModel.Box;

@Repository
public interface BoxRepository extends JpaRepository<Box,Long>{

	@Query("SELECT b FROM Box b WHERE b.number = :number and b.location.id = :location ")
	public Optional<Box> getBoxByNumber(@Param("number")int number, @Param("location")long idLocation);
	//public boolean hasError();
	//public boolean updateBox(long id,Box box);
//	public List<Box> getAllBox();
//	public List<Box> getNotEmptyBox();
//	public List<Box> getNotEmptyBoxByLocation(long idLocation);
	//public List<Box> getAllBoxByLocation(long idLocation);
}
