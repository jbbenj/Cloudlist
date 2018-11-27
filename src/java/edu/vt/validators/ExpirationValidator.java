/*
 * Created by Muhammad Afiq Yusof on 2018.11.02  * 
 * Copyright © 2018 Muhammad Afiq Yusof. All rights reserved. * 
 */
package edu.vt.validators;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/*
The @FacesValidator annotation on a class automatically registers the class with the runtime as a Validator. 
The "expirationValidator" attribute is the validator-id used in a JSF facelets page within

    <f:validator validatorId="expirationValidator" />

to invoke the "validate" method of the annotated class given below.                           
 */
@FacesValidator("expirationValidator")

public class ExpirationValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        /*
        DateFormat is an abstract class for date/time formatting subclasses which
            formats and parses dates or time in a language-independent manner.
        SimpleDateFormat is a concrete class inheriting from DateFormat
            for formatting and parsing dates in a locale-sensitive manner.
        */
        // Create a month number formatter object in two-digit format
        DateFormat monthNumberFormatter = new SimpleDateFormat("MM");
        
        // Create a year number formatter object in two-digit format
        DateFormat yearNumberFormatter = new SimpleDateFormat("yy");
        
        // Obtain the current month number in two-digit format as an integer
        int currentMonthNumber = Integer.parseInt(monthNumberFormatter.format(Calendar.getInstance().getTime()));
        
        // Obtain the last 2 digits of the current year as an integer
        int currentYearNumber = Integer.parseInt(yearNumberFormatter.format(Calendar.getInstance().getTime()));
        
        // Type cast the inputted "value" to enteredExpirationDate of type String
        String enteredExpirationDate = (String) value;

        if (enteredExpirationDate == null || enteredExpirationDate.isEmpty()) {
            // Do not take any action. 
            // The required="true" in the XHTML file will catch this and produce an error message.
            return;
        }
        
        // REGular EXpression (regex) to validate if the entered credit card expiration date consists of digits only
        String regex = "[0-9]*";
        
        if (!enteredExpirationDate.matches(regex)) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_FATAL,
                    "Unrecognized Credit Card Expiration Date!",
                    "The date must consist of only 4 digits in MMYY format!"));
        } 
        
        // Convert the entered expiration date MMYY String to Integer
        Integer enteredExpirationDateInteger = Integer.valueOf(enteredExpirationDate);
        
        // Obtain the entered month number MM from enteredExpirationDateInteger MMYY
        int enteredExpirationMonthNumber = (int)Math.floor(enteredExpirationDateInteger / 100);
        
        // The % (modulus) operator divides enteredExpirationDateInteger MMYY by 100 and returns remainder, which is the year YY
        int enteredExpirationYearNumber = enteredExpirationDateInteger % 100;

        if (enteredExpirationDate.length() < 4) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_FATAL,
                    "Unrecognized Credit Card Expiration Date!",
                    "The date must consist of only 4 digits in MMYY format!"));
            // Throwing an exception exits the method returning the control to the exception's handler.
        } 
        
        if (enteredExpirationMonthNumber > 12) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_FATAL,
                    "Unrecognized Credit Card Expiration Month!",
                    "Credit card expiration month cannot be greater than 12!"));
            // Throwing an exception exits the method returning the control to the exception's handler.
        } 
        
        if ((enteredExpirationMonthNumber < currentMonthNumber) && (enteredExpirationYearNumber == currentYearNumber)) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_FATAL,
                    "Unrecognized Credit Card Expiration Month!",
                    "Credit card expiration month is in the past!"));

            // Throwing an exception exits the method returning the control to the exception's handler.
        } 
        
        if (enteredExpirationYearNumber < currentYearNumber) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_FATAL,
                    "Unrecognized Credit Card Expiration Year!",
                    "Credit card expiration year is in the past!"));
            // Throwing an exception exits the method returning the control to the exception's handler.
        }
    }

}
