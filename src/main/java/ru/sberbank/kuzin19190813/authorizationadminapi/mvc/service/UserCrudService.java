package ru.sberbank.kuzin19190813.authorizationadminapi.mvc.service;

import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.user.User;

import java.util.Collection;

public interface UserCrudService {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User getUser(Long userId);
    Collection<User> getAllUsers();
}
