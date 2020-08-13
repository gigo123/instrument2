package instrument2.dbtest;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import ua.com.gigasoft.instrument2c.database.BoxRepository;
import ua.com.gigasoft.instrument2c.database.LocationRepository;
import ua.com.gigasoft.instrument2c.mainModel.Box;
import ua.com.gigasoft.instrument2c.mainModel.Location;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ua.com.gigasoft.instrument2c.Instrument2cApplication.class)
@DataJpaTest
public class BoxDBTest {
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private BoxRepository boxRepo;
	@Autowired
	private LocationRepository locRepo;

	@Test
	public void getNumberTest() {
	
		Location location = new Location("test1", true);
		entityManager.persist(location);
		entityManager.flush();
		Optional<Location> loc = locRepo.getLocByName("test1");
		if(loc.isPresent()) {
			Location loc2 = loc.get();
			System.out.println(loc2);
			Box box = new Box(1, loc2);
			entityManager.persist(box);
			entityManager.flush();
			
			Optional<Box> boxRespond = boxRepo.getBoxByNumber(1, loc2.getId());
			assert (boxRespond.isPresent());
		}
		
	}

}
