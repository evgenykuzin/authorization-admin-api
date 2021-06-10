package ru.sberbank.kuzin19190813.authorizationadminapi.mvc.service;

import ru.sberbank.kuzin19190813.authorizationadminapi.exceptions.ApiException;

public interface UserDataService {
    void changeUserPassword(Long userId, String password) throws ApiException;
}
