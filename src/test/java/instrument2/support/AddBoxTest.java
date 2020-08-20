package instrument2.support;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.com.gigasoft.instrument2c.database.BoxJPADAO;
import ua.com.gigasoft.instrument2c.database.BoxRepository;
import ua.com.gigasoft.instrument2c.database.LocationRepository;
import ua.com.gigasoft.instrument2c.mainModel.Location;
import ua.com.gigasoft.instrument2c.secondModel.BoxListLocation;
import ua.com.gigasoft.instrument2c.support.AddBoxWorker;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ua.com.gigasoft.instrument2c.Instrument2cApplication.class)
@DataJpaTest
@ComponentScan("ua.com.gigasoft.instrument2c")
public class AddBoxTest {
	@Autowired
	private LocationRepository locRepo;
	@Autowired @Qualifier("addBoxS")
	private AddBoxWorker boxWorker;
	@Autowired
	private BoxJPADAO boxDao;
	@Test
	public void addBox() {
		Location location = new Location("test1", true);
		locRepo.save(location );
		System.out.println(locRepo.findAll());
		BoxListLocation box = new BoxListLocation();
		box.setNumber(1);
		box.setLocationWB(1);
		boxWorker.addBox(box);
		System.out.println(boxDao.getAllBox());
	}
	@Test
	public void addBoxRange() {
		Location location = new Location("test1", true);
		locRepo.save(location );
		System.out.println(locRepo.findAll());
		BoxListLocation box = new BoxListLocation();
		box.setStartNum(2);
		box.setEndNum(20);
		box.setLocationWB(2);
		box.setManyBox("M");
		boxWorker.addBox(box);
		System.out.println(boxDao.getAllBox());
	}

}
