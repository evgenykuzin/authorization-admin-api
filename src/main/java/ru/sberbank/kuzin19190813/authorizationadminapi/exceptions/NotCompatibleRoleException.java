package ru.sberbank.kuzin19190813.authorizationadminapi.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotCompatibleRoleException extends ApiException {
    String roleName;

    @Override
    public String getMessage() {
        return String.format("role %s is not compatible with existed roles", roleName);
    }
}
