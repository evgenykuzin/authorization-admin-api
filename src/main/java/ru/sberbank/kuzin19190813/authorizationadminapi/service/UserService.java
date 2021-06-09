package ru.sberbank.kuzin19190813.authorizationadminapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.kuzin19190813.authorizationadminapi.repository.UserRepository;
import ru.sberbank.kuzin19190813.authorizationadminapi.exceptions.NotCompatibleRoleException;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.role.Role;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.user.UserDTO;
import ru.sberbank.kuzin19190813.authorizationadminapi.util.RoleValidator;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;

@Service
public class UserService {
    UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void addRole(Long userId, Role role) throws NotCompatibleRoleException {
        changeRoles(userId, role, Set::add);
    }

    public void removeRole(Long userId, Role role) throws NotCompatibleRoleException {
        changeRoles(userId, role, Set::remove);
    }

    public void changeRoles(Long userId, Role role, BiConsumer<Set<Role>, Role> setConsumer) throws NotCompatibleRoleException {
        AtomicBoolean roleIsValid = new AtomicBoolean();
        repository.update(userId, userDTO -> {
            Set<Role> roles = new HashSet<>(userDTO.getRoles());
            roleIsValid.set(RoleValidator.checkCompatible(role, roles));
            if (roleIsValid.get()) {
                setConsumer.accept(roles, role);
                userDTO.setRoles(roles);
            }
        });
        if (!roleIsValid.get()) {
            throw new NotCompatibleRoleException(role.name());
        }
    }

    public void changeUserPassword(Long userId, String password) {
        repository.update(userId, userDTO -> userDTO.setPassword(password));
    }

    public void addUser(String name, String login, String password) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(name);
        userDTO.setLogin(login);
        userDTO.setPassword(password);
        repository.save(userDTO);
    }
}
