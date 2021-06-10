package ru.sberbank.kuzin19190813.authorizationadminapi.util;

import lombok.extern.slf4j.Slf4j;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.role.Role;
import java.util.Set;
@Slf4j
public class RoleChecker {
    public static boolean checkCompatible(Role roleToCheck, Set<Role> roles) {
        System.out.println(roleToCheck);
        log.info(roleToCheck.toString());
        log.info(roles.toString());
        System.out.println(roles);
        if (roles.contains(Role.BLOCKED)) return false;
        if (roles.isEmpty() && roleToCheck.equals(Role.AUTHORIZED)) return true;
        if (!roles.contains(Role.AUTHORIZED) && (roleToCheck.equals(Role.ADMIN)) || roleToCheck.equals(Role.PRIVILEGED)) return false;
        return true;
    }
}
