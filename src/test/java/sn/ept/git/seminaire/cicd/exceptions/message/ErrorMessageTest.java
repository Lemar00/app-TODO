package sn.ept.git.seminaire.cicd.exceptions.message;


import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorMessageTest {

    @Test
    void gettersAndSettersShouldWork() {

        ErrorMessage errorMessage = ErrorMessage.of(404, new Date(), "Not Found", "Resource not found");

        assertEquals(404, errorMessage.getStatus());
        assertEquals("Not Found", errorMessage.getMessage());
        assertEquals("Resource not found", errorMessage.getDescription());

        errorMessage.setStatus(500);
        errorMessage.setMessage("Internal Server Error");
        errorMessage.setDescription("An internal server error occurred");

        assertEquals(500, errorMessage.getStatus());
        assertEquals("Internal Server Error", errorMessage.getMessage());
        assertEquals("An internal server error occurred", errorMessage.getDescription());
    }

    @Test
    void noArgsConstructorShouldCreateInstance() {
        ErrorMessage errorMessage = new ErrorMessage();

        assertNotNull(errorMessage);
    }

    @Test
    void equalsAndHashCodeShouldWork() {
        ErrorMessage errorMessage1 = ErrorMessage.of(404, null, "Not Found", "Resource not found");
        ErrorMessage errorMessage2 = ErrorMessage.of(404, null, "Not Found", "Resource not found");

        assertEquals(errorMessage1, errorMessage2);
        assertEquals(errorMessage1.hashCode(), errorMessage2.hashCode());
    }


    @Test
    void testEqualsAndHashCodeWithNull() {
        ErrorMessage errorMessage = ErrorMessage.of(404, new Date(), "Not Found", "Resource not found");
        assertFalse(errorMessage.equals(null));
    }

    @Test
    void testEqualsAndHashCodeWithDifferentType() {
        ErrorMessage errorMessage = ErrorMessage.of(404, new Date(), "Not Found", "Resource not found");
        assertFalse(errorMessage.equals("SomeString"));
    }

    @Test
    void testEqualsAndHashCodeWithDifferentField() {
        ErrorMessage errorMessage1 = ErrorMessage.of(404, new Date(), "Not Found", "Resource not found");
        ErrorMessage errorMessage2 = ErrorMessage.of(404, new Date(), "Not Found", "Different Description");
        assertFalse(errorMessage1.equals(errorMessage2));
    }

    @Test
    void testEqualsAndHashCodeWithAllFieldsDifferent() {
        ErrorMessage errorMessage1 = ErrorMessage.of(404, new Date(), "Not Found", "Resource not found");
        ErrorMessage errorMessage2 = ErrorMessage.of(500, new Date(), "Internal Server Error", "Server error");
        assertFalse(errorMessage1.equals(errorMessage2));
    }


    @Test
    void toStringShouldNotBeNull() {
        ErrorMessage errorMessage = ErrorMessage.of(404, new Date(), "Not Found", "Resource not found");

        assertNotNull(errorMessage.toString());
    }

    @Test
    void superBuilderShouldCreateInstance() {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .status(404)
                .message("Not Found")
                .description("Resource not found")
                .build();

        assertNotNull(errorMessage);
    }
}
