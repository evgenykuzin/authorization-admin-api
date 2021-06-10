package ru.sberbank.kuzin19190813.authorizationadminapi.mvc.service;

import ru.sberbank.kuzin19190813.authorizationadminapi.exceptions.ApiException;
import ru.sberbank.kuzin19190813.authorizationadminapi.exceptions.NotCompatibleRoleException;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.role.Role;

import java.util.Set;
import java.util.function.BiConsumer;

public interface UserRolesService {
    default void addRole(Long userId, Role role) throws ApiException {
        changeRoles(userId, role, Set::add);
    }

    default void removeRole(Long userId, Role role) throws ApiException {
        changeRoles(userId, role, Set::remove);
    }

    void changeRoles(Long userId, Role role, BiConsumer<Set<Role>, Role> setConsumer) throws NotCompatibleRoleException, ApiException;

    Set<Role> getRoles(Long userId) throws ApiException;
}
