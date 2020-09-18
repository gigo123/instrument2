package ua.com.gigasoft.instrument2c.customValidator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import ua.com.gigasoft.instrument2c.customValidator.AddWDocValidator;
import ua.com.gigasoft.instrument2c.secondModel.ExDocWEB;

public class AddWDocVC implements ConstraintValidator<AddWDocValidator, ExDocWEB> {

	@Autowired
	private ApplicationContext applicationContext;
	
	private AddWDocChekService service;
	
	@Override
	public void initialize(AddWDocValidator constraint) {
		Class<? extends AddWDocChekService> clazz = constraint.service();
		try {
			this.service = this.applicationContext.getBean(clazz);
		} catch (Exception ex) {
			// Cant't initialize service which extends UniqueUsernameValidator
			System.out.println(ex);
		}
	}

	@Override
	public boolean isValid(ExDocWEB value, ConstraintValidatorContext context) {
		if (this.service != null) {
			if (service.checkRow(value,context)) {
				return true;
			} else {
			//	context.buildConstraintViolationWithTemplate("место храненя существует").addPropertyNode("amount").addConstraintViolation();
				//context.buildConstraintViolationWithTemplate("инструмент существует")
				//		.addPropertyNode("name").addConstraintViolation();
				return false;
			}
		}
		return false;
	}

}
