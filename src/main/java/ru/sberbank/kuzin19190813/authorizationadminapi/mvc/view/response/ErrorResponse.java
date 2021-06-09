package ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.body.ErrorBody;

public class ErrorResponse extends ResponseEntity<ErrorBody> {
    private ErrorResponse(ErrorBody body, HttpStatus status) {
        super(body, status);
    }

    public ErrorResponse(String message) {
        super(new ErrorBody(message), HttpStatus.BAD_REQUEST);
    }
}
