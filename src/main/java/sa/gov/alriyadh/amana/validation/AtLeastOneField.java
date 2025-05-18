package sa.gov.alriyadh.amana.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AtLeastOneFieldValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AtLeastOneField {

    String message() default "At least one of roleId, userCode, or requestNo must be provided";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
