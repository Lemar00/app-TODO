package sn.ept.git.seminaire.cicd.demo.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BadPhoneExceptionTest {

    @Test
    void defaultConstructorShouldCreateInstance() {
        BadPhoneException exception = new BadPhoneException();

        assertNotNull(exception);
    }

    @Test
    void messageConstructorShouldCreateInstanceWithMessage() {
        String errorMessage = "Invalid phone number format";

        BadPhoneException exception = new BadPhoneException(errorMessage);

        assertNotNull(exception);


        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void messageAndCauseConstructorShouldCreateInstanceWithMessageAndCause() {

        String errorMessage = "Invalid phone number format";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        BadPhoneException exception = new BadPhoneException(errorMessage, cause);


        assertNotNull(exception);

        assertEquals(errorMessage, exception.getMessage());

        assertEquals(cause, exception.getCause());
    }

    @Test
    void causeConstructorShouldCreateInstanceWithCause() {
        Throwable cause = new IllegalArgumentException("Invalid argument");

        BadPhoneException exception = new BadPhoneException(cause);

        assertNotNull(exception);

        assertEquals(cause, exception.getCause());
    }

    @Test
    void fullConstructorShouldCreateInstanceWithAllParameters() {
        String errorMessage = "Invalid phone number format";
        Throwable cause = new IllegalArgumentException("Invalid argument");
        boolean enableSuppression = true;
        boolean writableStackTrace = false;

        BadPhoneException exception = new BadPhoneException(errorMessage, cause, enableSuppression, writableStackTrace);

        assertNotNull(exception);

        assertEquals(errorMessage, exception.getMessage());

        assertEquals(cause, exception.getCause());

        assertEquals(writableStackTrace, exception.getStackTrace().length > 0);
    }
}
