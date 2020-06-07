package ua.com.gigasoft.instrument2c.customValidator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import ua.com.gigasoft.instrument2c.database.LocationJPADAO;
import ua.com.gigasoft.instrument2c.mainModel.Location;

public class AddLocationVC implements ConstraintValidator<AddLocationValidator, Object>{

	@Autowired
	private LocationJPADAO locDAO;

	public void initialize(AddLocationValidator constraint) {
	}
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String name = (String) new BeanWrapperImpl(value).getPropertyValue("name");
		context.disableDefaultConstraintViolation();
		Optional<Location> loc = locDAO.getLocByName(name);
		if (loc.isPresent()) {
			context.buildConstraintViolationWithTemplate("место храненя существует")
			.addPropertyNode("name").addConstraintViolation();
					return false;
		}
		
		return true;
	}

}
