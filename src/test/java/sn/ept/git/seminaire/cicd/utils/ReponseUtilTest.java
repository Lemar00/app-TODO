package sn.ept.git.seminaire.cicd.utils;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResponseUtilTest {

    @Test
    void wrapOrNotFoundShouldReturnResponseEntityWithData() {
        String data = "Hello, World!";
        Optional<String> maybeResponse = Optional.of(data);

        ResponseEntity<String> responseEntity = ResponseUtil.wrapOrNotFound(maybeResponse);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(data, responseEntity.getBody());
    }

    @Test
    void wrapOrNotFoundShouldReturnResponseEntityWithCustomStatus() {
        String data = "Custom Status";
        Optional<String> maybeResponse = Optional.of(data);
        HttpStatus customStatus = HttpStatus.ACCEPTED;

        ResponseEntity<String> responseEntity = ResponseUtil.wrapOrNotFound(maybeResponse, customStatus);

        assertEquals(customStatus, responseEntity.getStatusCode());
        assertEquals(data, responseEntity.getBody());
    }

    @Test
    void wrapOrNotFoundShouldReturnResponseEntityWithCustomStatusAndReason() {
        String data = "Custom Status and Reason";
        Optional<String> maybeResponse = Optional.of(data);
        HttpStatus customStatus = HttpStatus.BAD_REQUEST;
        String reason = "Custom Reason";

        ResponseEntity<String> responseEntity = ResponseUtil.wrapOrNotFound(maybeResponse, customStatus, reason);

        assertEquals(customStatus, responseEntity.getStatusCode());
        assertEquals(data, responseEntity.getBody());
    }


    @Test
    void wrapOrNotFoundShouldThrowResponseStatusExceptionIfEmpty() {
        Optional<String> maybeResponse = Optional.empty();

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> ResponseUtil.wrapOrNotFound(maybeResponse));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void testWrapOrNotFoundWithReason() {
        Optional<String> data = Optional.of("Hello, World!");
        ResponseEntity<String> response = ResponseUtil.wrapOrNotFound(data, HttpStatus.OK, "Custom Reason");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello, World!", response.getBody());
    }


    @Test
    void testWrapOrNotFoundWithReason1() {

        Optional<String> maybeResponse = Optional.of("Hello, World!");

        ResponseEntity<String> responseEntity = ResponseUtil.wrapOrNotFound(maybeResponse, "Custom Reason");


        assertNotNull(responseEntity);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertEquals("Hello, World!", responseEntity.getBody());
    }



    @Test
    public void testWrapOrNotFoundWithEmptyDataAndReason() {
        Optional<String> emptyData = Optional.empty();
        String reason = "No data found";

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> ResponseUtil.wrapOrNotFound(emptyData, HttpStatus.NOT_FOUND, reason));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals(reason, exception.getReason());
    }




    @Test
    void wrapOrNotFoundShouldReturnResponseEntityWithHeaders() {
        String data = "With Headers";
        Optional<String> maybeResponse = Optional.of(data);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Header Value");

        ResponseEntity<String> responseEntity = ResponseUtil.wrapOrNotFound(maybeResponse, headers, HttpStatus.OK);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(data, responseEntity.getBody());
        assertEquals("Header Value", responseEntity.getHeaders().getFirst("Custom-Header"));
    }
}
