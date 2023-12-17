package com.SoftUni.DriverServiceProject.ValidationAnotations;


import com.SoftUni.DriverServiceProject.ValidationAnotations.annotation.FieldMatcher;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class MatcherValidator implements ConstraintValidator <FieldMatcher,Object>{

    private String first;
    private String second;
    private String message;


    @Override
    public void initialize(FieldMatcher constraintAnnotation) {
       this.first=constraintAnnotation.first();
       this.second=constraintAnnotation.second();
       this.message=constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapper beanWrapper= PropertyAccessorFactory.forBeanPropertyAccess(o);



        Object firstValue=beanWrapper.getPropertyValue(this.first);
        Object secondValue=beanWrapper.getPropertyValue(this.second);

        boolean valid;

        if(firstValue==null){
            valid=secondValue==null;
        }else {
            valid=firstValue.equals(secondValue);
        }

        if(!valid){
            constraintValidatorContext.
                    buildConstraintViolationWithTemplate(message).addPropertyNode(second).addConstraintViolation().disableDefaultConstraintViolation();




        }


        return valid;
    }
}
