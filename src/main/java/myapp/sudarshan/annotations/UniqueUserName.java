package myapp.sudarshan.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import myapp.sudarshan.validators.UniqueUserNameValidator;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { UniqueUserNameValidator.class })
public @interface UniqueUserName {
	
	String message() default "{javax.validation.constraints.Size.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
