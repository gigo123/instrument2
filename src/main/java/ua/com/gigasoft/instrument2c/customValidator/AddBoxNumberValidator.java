package ua.com.gigasoft.instrument2c.customValidator;

import java.lang.reflect.Field;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import ua.com.gigasoft.instrument2c.database.BoxJPADAO;
import ua.com.gigasoft.instrument2c.database.LocationJPADAO;
import ua.com.gigasoft.instrument2c.mainModel.Box;
import ua.com.gigasoft.instrument2c.mainModel.Location;

public class AddBoxNumberValidator implements ConstraintValidator<AddBoxNumer, Object> {

	@Autowired
	private BoxJPADAO boxDAO;
	@Autowired
	private LocationJPADAO locDAO;

	public void initialize(AddBoxNumer constraint) {
	}
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String locationN = (String) new BeanWrapperImpl(value).getPropertyValue("locationWB");
		context.disableDefaultConstraintViolation();
		String manyBox = (String) new BeanWrapperImpl(value).getPropertyValue("manyBox");
		int number = (int) new BeanWrapperImpl(value).getPropertyValue("number");
		long locId = Long.parseLong(locationN);
		Optional<Location> loc = locDAO.getLocById(locId);
		if (loc.isPresent()) {
			if (manyBox.equals("O")) {
				Optional<Box> tempBox = boxDAO.getBoxByNumber(number, locId);
				if (tempBox.isPresent()) {
					context.buildConstraintViolationWithTemplate("ячейка с таким номером уже существует")
					.addPropertyNode("number").addConstraintViolation();
					return false;
				}
			}
		} else {
			System.out.println("<li> неправильное место хранения </li>");
			return false;
		}
		return true;
	}
}
