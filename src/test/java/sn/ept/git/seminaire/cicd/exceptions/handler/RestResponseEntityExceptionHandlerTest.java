package sn.ept.git.seminaire.cicd.exceptions.handler;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import sn.ept.git.seminaire.cicd.exceptions.ForbiddenException;
import sn.ept.git.seminaire.cicd.exceptions.InvalidException;
import sn.ept.git.seminaire.cicd.exceptions.ItemExistsException;
import sn.ept.git.seminaire.cicd.exceptions.message.ErrorMessage;


public class RestResponseEntityExceptionHandlerTest {

    @Test
    public void testConflictExceptionHandler() {
        // Créer une instance du gestionnaire d'exceptions à tester
        RestResponseEntityExceptionHandler handler = new RestResponseEntityExceptionHandler();

        // Créer une instance de l'exception
        ItemExistsException exception = new ItemExistsException("Conflict");

        // Créer une instance de WebRequest
        WebRequest webRequest = mock(WebRequest.class);

        // Appeler la méthode du gestionnaire d'exceptions
        ResponseEntity<ErrorMessage> response = handler.conflict(exception, webRequest);

        // Vérifier que la réponse HTTP a le statut attendu (HttpStatus.CONFLICT)
        assert(response.getStatusCode() == HttpStatus.CONFLICT);

        // vérifier d'autres attributs de la réponse, par exemple, le corps de la réponse
        ErrorMessage errorMessage = response.getBody();
        assert(errorMessage != null);
        assert(errorMessage.getStatus() == HttpStatus.CONFLICT.value());

    }

    @Test
    public void testBadRequestExceptionHandler() {
        // Créer une instance du gestionnaire d'exceptions à tester
        RestResponseEntityExceptionHandler handler = new RestResponseEntityExceptionHandler();

        // Créer une instance de l'exception
        InvalidException exception = new InvalidException("Bad Request");

        // Créer une instance de WebRequest
        WebRequest webRequest = mock(WebRequest.class);

        // Appeler la méthode du gestionnaire d'exceptions
        ResponseEntity<ErrorMessage> response = handler.badRequest(exception, webRequest);

        // Vérifier que la réponse HTTP a le statut attendu (HttpStatus.BAD_REQUEST)
        assert(response.getStatusCode() == HttpStatus.BAD_REQUEST);

        // vérifier d'autres attributs de la réponse, par exemple, le corps de la réponse
        ErrorMessage errorMessage = response.getBody();
        assert(errorMessage != null);
        assert(errorMessage.getStatus() == HttpStatus.BAD_REQUEST.value());

    }

    @Test
    public void testPermissionDeniedExceptionHandler() {
        // Créer une instance du gestionnaire d'exceptions à tester
        RestResponseEntityExceptionHandler handler = new RestResponseEntityExceptionHandler();

        // Créer une instance de l'exception
        ForbiddenException exception = new ForbiddenException("Permission Denied");

        // Créer une instance de WebRequest
        WebRequest webRequest = mock(WebRequest.class);

        // Appeler la méthode du gestionnaire d'exceptions
        ResponseEntity<ErrorMessage> response = handler.permissionDenied(exception, webRequest);

        // Vérifier que la réponse HTTP a le statut attendu (HttpStatus.FORBIDDEN)
        assert(response.getStatusCode() == HttpStatus.FORBIDDEN);

        // vérifier d'autres attributs de la réponse, par exemple, le corps de la réponse
        ErrorMessage errorMessage = response.getBody();
        assert(errorMessage != null);
        assert(errorMessage.getStatus() == HttpStatus.FORBIDDEN.value());

    }
}
