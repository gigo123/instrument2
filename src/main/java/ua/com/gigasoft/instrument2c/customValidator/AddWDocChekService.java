package ua.com.gigasoft.instrument2c.customValidator;

import javax.validation.ConstraintValidatorContext;

import ua.com.gigasoft.instrument2c.secondModel.ExDocWEB;

public interface AddWDocChekService {
	public boolean checkRow( ExDocWEB docRow, ConstraintValidatorContext context);
}
