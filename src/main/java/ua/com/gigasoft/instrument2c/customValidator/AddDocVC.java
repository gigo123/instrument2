package ua.com.gigasoft.instrument2c.customValidator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ua.com.gigasoft.instrument2c.customValidator.AddDocValidator;

public class AddDocVC implements ConstraintValidator<AddDocValidator, Object> {

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}

}
