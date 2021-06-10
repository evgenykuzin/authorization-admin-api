package ru.sberbank.kuzin19190813.authorizationadminapi.mvc.service;

import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.user.User;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.repository.UserRepository;
import ru.sberbank.kuzin19190813.authorizationadminapi.util.converter.UserConverter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class DefaultUserService implements UserRolesService, UserDataService, UserCrudService {
    protected final UserRepository repository;
    protected final UserConverter userConverter;

    public DefaultUserService(UserRepository repository, UserConverter userConverter) {
        this.repository = repository;
        this.userConverter = userConverter;
    }

    @Override
    public void addUser(User user) {
        repository.save(userConverter.from(user));
    }

    @Override
    public void updateUser(User user) {
        addUser(user);
    }

    @Override
    public void deleteUser(User user) {
        repository.delete(userConverter.from(user));
    }

    @Override
    public User getUser(Long userId) {
        return userConverter.to(repository.getOne(userId));
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll().stream().map(userConverter::to).collect(Collectors.toList());
    }
}
