package instrument2.pages;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ua.com.gigasoft.instrument2c.Instrument2cApplication.class)
@WebMvcTest(controllers = ua.com.gigasoft.instrument2c.pages.create.AddLocationController.class)
public class AddLocationPageTest {
	@Autowired
	private MockMvc mockMvc;
	@Test
	public void getNumberTest() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/addlocation")
					.param("name","test1")
					.param("boxes", "true"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
