package es.davidrico.springboot.forminterceptors.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = RequeridoValidador.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface Requerido {
	
	String message() default "Campo es requerido - usando anotaciones";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}

