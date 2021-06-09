package ru.sberbank.kuzin19190813.authorizationadminapi.util;

import org.springframework.web.client.RestTemplate;
import ru.sberbank.kuzin19190813.authorizationadminapi.app.AuthorizationAdminApiApplication;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.role.Role;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

public class RoleValidator {
    public static boolean checkCompatible(Role roleToCheck, Set<Role> roles) {
        return true;
    }

    public static void main(String[] args) throws URISyntaxException {
        //AuthorizationAdminApiApplication.main(new String[]{});
        String restTemplate = new RestTemplate().getForObject(new URI("http://localhost:8080/admin/api/v1/user/ping"), String.class);
        System.out.println(restTemplate);
    }
}
