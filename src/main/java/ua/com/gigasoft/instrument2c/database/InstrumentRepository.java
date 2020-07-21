package ua.com.gigasoft.instrument2c.database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.com.gigasoft.instrument2c.mainModel.Instrument;
@Repository
public interface InstrumentRepository extends JpaRepository<Instrument,Long> {
	@Query("SELECT inst FROM Instrument inst WHERE inst.name=:instname")
	public Optional<Instrument> getInstrumentByName(@Param("instname")String name);
}
