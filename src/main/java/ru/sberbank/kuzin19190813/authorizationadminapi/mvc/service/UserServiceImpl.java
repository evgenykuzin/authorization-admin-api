package ru.sberbank.kuzin19190813.authorizationadminapi.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.kuzin19190813.authorizationadminapi.exceptions.ApiException;
import ru.sberbank.kuzin19190813.authorizationadminapi.exceptions.NoSuchUserException;
import ru.sberbank.kuzin19190813.authorizationadminapi.exceptions.NotCompatibleRoleException;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.role.Role;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.user.UserDTO;
import ru.sberbank.kuzin19190813.authorizationadminapi.util.RoleChecker;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.repository.*;
import ru.sberbank.kuzin19190813.authorizationadminapi.util.converter.UserConverter;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;

@Service
public class UserServiceImpl extends DefaultUserService {

    @Autowired
    public UserServiceImpl(UserRepository repository, UserConverter userConverter) {
        super(repository, userConverter);
    }

    @Override
    public void changeRoles(Long userId, Role role, BiConsumer<Set<Role>, Role> setConsumer) throws ApiException {
        AtomicBoolean roleIsValid = new AtomicBoolean();
        Set<Role> roles = new HashSet<>();
        UserDTO result = repository.updateField(userId, userDTO -> {
            roles.addAll(userDTO.getRoles());
            roleIsValid.set(RoleChecker.checkCompatible(role, roles));
            System.out.println(roleIsValid);
            if (roleIsValid.get()) {
                setConsumer.accept(roles, role);
                userDTO.setRoles(roles);
            }
        });
        if (result == null) throw new NoSuchUserException(userId);
        if (!roleIsValid.get()) {
            throw new NotCompatibleRoleException(role, roles);
        }
    }

    @Override
    public Set<Role> getRoles(Long userId) throws ApiException {
        Optional<UserDTO> userDTO = repository.findById(userId);
        if (userDTO.orElse(null) == null) throw new NoSuchUserException(userId);
        return userDTO.map(UserDTO::getRoles).orElse(Collections.emptySet());
    }

    @Override
    public void changeUserPassword(Long userId, String password) throws ApiException {
        if (repository.updateField(userId, userDTO -> userDTO.setPassword(password)) == null) throw new NoSuchUserException(userId);
    }
}
