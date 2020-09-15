package ua.com.gigasoft.instrument2c.customValidator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import ua.com.gigasoft.instrument2c.database.InstrumentJPADAO;
import ua.com.gigasoft.instrument2c.mainModel.Instrument;
public class AddInstrumentVC implements ConstraintValidator<AddInstrumentValidator, Instrument>{

	@Autowired
	private ApplicationContext applicationContext;

	private AddInstrumentCheckService service;

	@Override
	public void initialize(AddInstrumentValidator constraint) {
		Class<? extends AddInstrumentCheckService> clazz = constraint.service();
		try {
			this.service = this.applicationContext.getBean(clazz);
		} catch (Exception ex) {
			// Cant't initialize service which extends UniqueUsernameValidator
			System.out.println(ex);
		}
	}
	
	@Override
	public boolean isValid(Instrument value, ConstraintValidatorContext context) {
		
		if (this.service != null) {
			if (service.checkName(value.getName())) {
				return true;
			} else {
				//context.buildConstraintViolationWithTemplate("место храненя существует").addConstraintViolation();
				System.out.println("false");
				return false;
			}
		}
	//	String name = (String) new BeanWrapperImpl(value).getPropertyValue("name");
	//	context.disableDefaultConstraintViolation();
	//	Optional<Instrument> instrum = instDAO.getInstrumentByName(name);
	//	if (instrum.isPresent()) {
	//		context.buildConstraintViolationWithTemplate("инструмент существует")
	//		.addPropertyNode("name").addConstraintViolation();
	//				return false;
	//	}
		
		return true;
	}

}
