package ua.com.gigasoft.instrument2c.support;

import javax.validation.ConstraintValidatorContext;

import ua.com.gigasoft.instrument2c.secondModel.ExDocWEB;

public interface DocRowCheckWorker {
	public boolean   chekRow ( ExDocWEB docRow ,ConstraintValidatorContext context);

}
