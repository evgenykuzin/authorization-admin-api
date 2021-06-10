package ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.body;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.role.Role;

import java.util.HashSet;
import java.util.Set;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RolesBody implements Body {
    Set<Role> roles = new HashSet<>();
}
