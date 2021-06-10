package ru.sberbank.kuzin19190813.authorizationadminapi.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NoSuchUserException extends ApiException {
    Long id;

    @Override
    public String getMessage() {
        return String.format("No such user with id: %s", id);
    }
}
