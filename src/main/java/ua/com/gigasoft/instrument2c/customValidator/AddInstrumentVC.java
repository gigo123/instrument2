package ua.com.gigasoft.instrument2c.customValidator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import ua.com.gigasoft.instrument2c.database.InstrumentJPADAO;
import ua.com.gigasoft.instrument2c.mainModel.Instrument;
public class AddInstrumentVC implements ConstraintValidator<AddInstrumentValidator, Object>{

	@Autowired
	private InstrumentJPADAO instDAO;
	
	public void initialize(AddInstrumentValidator constraint) {
	}
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		System.out.println("begin");
		String name = (String) new BeanWrapperImpl(value).getPropertyValue("name");
		context.disableDefaultConstraintViolation();
		Optional<Instrument> instrum = instDAO.getInstrumentByName(name);
		if (instrum.isPresent()) {
			context.buildConstraintViolationWithTemplate("инструмент существует")
			.addPropertyNode("name").addConstraintViolation();
					return false;
		}
		
		return true;
	}

}
