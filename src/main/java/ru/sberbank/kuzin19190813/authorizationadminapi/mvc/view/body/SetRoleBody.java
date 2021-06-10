package ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.body;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.role.Role;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SetRoleBody implements Body {
    Long userId;
    Role role;
}
