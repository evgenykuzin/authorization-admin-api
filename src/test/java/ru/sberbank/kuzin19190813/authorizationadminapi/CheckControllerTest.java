package ru.sberbank.kuzin19190813.authorizationadminapi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sberbank.kuzin19190813.authorizationadminapi.exceptions.NotCompatibleRoleException;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.controller.UsersController;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.role.Role;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.body.*;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckControllerTest {

    @Autowired
    UsersController controller;

    @Test
    public void shouldReturnOK() {
        ResponseEntity<? extends Body> actual = controller.ping();
        Assert.assertEquals("Method ping() always return OK", new OkBody("OK"), actual.getBody());
    }

    @Test
    public void addRoleIsOk() {
        controller.addUser(new AddUserBody("jeka", "jekalogin", "pass123"));
        ResponseEntity<? extends Body> actual = controller.addRole(new SetRoleBody(1L, Role.AUTHORIZED));
        Assert.assertEquals(new OkBody("OK"), actual.getBody());
        Assert.assertFalse(getRoles(1L).isEmpty());
    }

    @Test
    public void roleIsNotCompatible() {
        controller.addUser(new AddUserBody("jeka", "jekalogin", "pass123"));
        ResponseEntity<? extends Body> actual = controller.addRole(new SetRoleBody(1L, Role.ADMIN));
        Assert.assertEquals(new ErrorBody(new NotCompatibleRoleException(Role.ADMIN, Collections.emptySet()).getMessage()), actual.getBody());
        Assert.assertTrue(getRoles(1L).isEmpty());
    }

    public Set<Role> getRoles(Long userId) {
        return ((RolesBody) Objects.requireNonNull(controller.getRoles(userId).getBody())).getRoles();
    }
}