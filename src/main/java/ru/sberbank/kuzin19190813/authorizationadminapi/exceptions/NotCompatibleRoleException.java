package ru.sberbank.kuzin19190813.authorizationadminapi.exceptions;

import lombok.AllArgsConstructor;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.role.Role;

import java.util.Set;

@AllArgsConstructor
public class NotCompatibleRoleException extends ApiException {
    Role role;
    Set<Role> roles;

    @Override
    public String getMessage() {
        return String.format("role %s is not compatible with existed roles: %s", role, roles);
    }
}
