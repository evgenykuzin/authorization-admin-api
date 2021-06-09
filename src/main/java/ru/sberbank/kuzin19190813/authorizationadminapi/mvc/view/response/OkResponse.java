package ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.body.OkBody;

public class OkResponse extends ResponseEntity<OkBody> {
    private OkResponse(OkBody body, HttpStatus status) {
        super(body, status);
    }

    public OkResponse(String message) {
        super(new OkBody(message), HttpStatus.OK);
    }

    public OkResponse() {
        super(new OkBody("OK"), HttpStatus.OK);
    }
}
