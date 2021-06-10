package ru.sberbank.kuzin19190813.authorizationadminapi.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.kuzin19190813.authorizationadminapi.exceptions.ApiException;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.role.Role;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.service.UserServiceImpl;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.body.*;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.response.ErrorResponse;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.response.OkResponse;
import ru.sberbank.kuzin19190813.authorizationadminapi.util.converter.UserBodyConverter;

import java.util.Set;

@RestController("/users")
@RequestMapping("/")
public class UsersController {
    UserServiceImpl userServiceImpl;

    @Autowired
    public UsersController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "OK";
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public OkResponse ping() {
        System.out.println("ping!");
        return new OkResponse();
    }

    @GetMapping("/{userId}/block")
    public @ResponseBody ResponseEntity<? extends Body> blockUser(@PathVariable Long userId) {
        return addRole(new SetRoleBody(userId, Role.BLOCKED));
    }

    @PostMapping("/roles/add")
    public @ResponseBody ResponseEntity<? extends Body> addRole(@RequestBody SetRoleBody setRoleBody) {
        return changeRoles(setRoleBody, userServiceImpl::addRole);
    }

    @PostMapping("/roles/remove")
    public @ResponseBody ResponseEntity<? extends Body> removeRole(@RequestBody SetRoleBody setRoleBody) {
        return changeRoles(setRoleBody, userServiceImpl::removeRole);
    }

    @PostMapping("/update-password")
    public @ResponseBody ResponseEntity<? extends Body> setRole(@RequestBody UpdatePasswordBody updatePasswordBody) {
        return executeResponse(() -> {
            String newPass = updatePasswordBody.getNewPassword();
            Long userId = updatePasswordBody.getUserId();
            userServiceImpl.changeUserPassword(userId, newPass);
        });
    }

    @PostMapping("/add")
    public @ResponseBody ResponseEntity<? extends Body> addUser(@RequestBody AddUserBody addUserBody) {
        return executeResponse(() -> {
            userServiceImpl.addUser(new UserBodyConverter().from(addUserBody));
        });
    }

    @GetMapping("{userId}/roles/")
    public @ResponseBody ResponseEntity<? extends Body> getRoles(@PathVariable Long userId) {
        try {
            Set<Role> roles = userServiceImpl.getRoles(userId);
            RolesBody rolesBody = new RolesBody();
            rolesBody.setRoles(roles);
            return new ResponseEntity<>(rolesBody, HttpStatus.OK);
        } catch (ApiException e) {
            e.printStackTrace();
            return new ErrorResponse(e.getMessage());
        }
    }

    private ResponseEntity<? extends Body> changeRoles(SetRoleBody setRoleBody, ChangeRoleBiConsumer changeRoleBiConsumer) {
        return executeResponse(() -> {
            Long userId = setRoleBody.getUserId();
            Role role = setRoleBody.getRole();
            changeRoleBiConsumer.change(userId, role);
        });
    }

    public ResponseEntity<? extends Body> executeResponse(Executor executor) {
        try {
            executor.execute();
        } catch (ApiException e) {
            e.printStackTrace();
            return new ErrorResponse(e.getMessage());
        }
        return new OkResponse();
    }

    @FunctionalInterface
    private interface Executor {
        void execute() throws ApiException;
    }

    @FunctionalInterface
    private interface ChangeRoleBiConsumer {
        void change(Long userId, Role role) throws ApiException;
    }
}
