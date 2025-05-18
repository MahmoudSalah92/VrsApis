package sa.gov.alriyadh.amana.validation;

import sa.gov.alriyadh.amana.pojo.VrsRequestFilter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AtLeastOneFieldValidator implements ConstraintValidator<AtLeastOneField, VrsRequestFilter> {

    @Override
    public boolean isValid(VrsRequestFilter filter, ConstraintValidatorContext context) {
        if (filter == null) {
            return false;  // null object is invalid here
        }

        boolean valid = filter.getRoleId() != null
                || (filter.getUserCode() != null && !filter.getUserCode().trim().isEmpty())
                || filter.getRequestNo() != null;

        if (!valid) {
            // This is optional but helps to customize the validation message on class level
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("roleId")    // You can attach the message to one or all fields
                    .addConstraintViolation();
        }

        return valid;
    }
}
