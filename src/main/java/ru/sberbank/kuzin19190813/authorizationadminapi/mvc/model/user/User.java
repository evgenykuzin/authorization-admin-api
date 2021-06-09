package ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.role.Role;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class User {
    Long id;
    String name;
    String login;
    String password;
    private Set<Role> roles;
}
