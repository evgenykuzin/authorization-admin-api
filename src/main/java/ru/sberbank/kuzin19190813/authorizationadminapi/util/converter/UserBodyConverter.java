package ru.sberbank.kuzin19190813.authorizationadminapi.util.converter;

import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.user.User;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.body.AddUserBody;

import java.util.Collections;

public class UserBodyConverter implements Converter<AddUserBody, User> {
    @Override
    public AddUserBody to(User user) {
        if (user == null) return null;
        return new AddUserBody(user.getName(), user.getLogin(), user.getPassword());
    }

    @Override
    public User from(AddUserBody addUserBody) {
        if (addUserBody == null) return null;
        return new User(
                null,
                addUserBody.getName(),
                addUserBody.getLogin(),
                addUserBody.getPassword(),
                Collections.emptySet()
        );
    }
}
