package sn.ept.git.seminaire.cicd.demo.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.mock;

public class DivisionByZeroExceptionTest {

    @Test
    void testDefaultConstructor() {
        DivisionByZeroException exception = new DivisionByZeroException();
        assertNotNull(exception);
        assertEquals(null, exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        String errorMessage = "Division by zero";
        Throwable cause = new ArithmeticException("Division by zero");

        DivisionByZeroException exception = new DivisionByZeroException(errorMessage, cause);

        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        Throwable cause = new ArithmeticException("Division by zero");

        DivisionByZeroException exception = new DivisionByZeroException(cause);

        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithAllParameters() {
        String message = "Test message";
        Throwable cause = mock(Throwable.class);

        DivisionByZeroException exception = new DivisionByZeroException(message, cause, true, true);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertEquals(true, exception.getStackTrace().length > 0); // Vérifiez si la pile d'appels n'est pas vide
    }


}
