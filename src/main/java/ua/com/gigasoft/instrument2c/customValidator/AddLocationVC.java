package ua.com.gigasoft.instrument2c.customValidator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import ua.com.gigasoft.instrument2c.dao.LocationDAO;
import ua.com.gigasoft.instrument2c.database.LocationJPADAO;
import ua.com.gigasoft.instrument2c.mainModel.Location;

public class AddLocationVC implements ConstraintValidator<AddLocationValidator, String> {

	@Autowired
	private ApplicationContext applicationContext;

	private AddLocationCheckService service;

	// private String fieldName;
	@Override
	public void initialize(AddLocationValidator constraint) {
		Class<? extends AddLocationCheckService> clazz = constraint.service();
		// this.fieldName = constraint.fieldName();
		System.out.println("init");
		try {
			this.service = this.applicationContext.getBean(clazz);
			System.out.println(service);
		} catch (Exception ex) {
			// Cant't initialize service which extends UniqueUsernameValidator
			System.out.println(ex);
		}
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		if (this.service != null) {

			if (service.checkName(value)) {
				return true;
			} else {

				context.buildConstraintViolationWithTemplate("место храненя существует").addConstraintViolation();
				System.out.println("false");
				return false;
			}
		}
		return true;
	}

}
