package ua.com.gigasoft.instrument2c.customValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target ({ElementType.TYPE , ElementType.FIELD})
@Retention (RetentionPolicy.RUNTIME)
@Constraint (validatedBy = AddInstrumentVC.class)
public @interface AddInstrumentValidator {
	String message() default "инструмент существует";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
