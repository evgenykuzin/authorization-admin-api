package ru.sberbank.kuzin19190813.authorizationadminapi.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.kuzin19190813.authorizationadminapi.exceptions.ApiException;
import ru.sberbank.kuzin19190813.authorizationadminapi.exceptions.NotCompatibleRoleException;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.role.Role;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.body.AddUserBody;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.body.OkBody;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.body.SetRoleBody;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.body.UpdatePasswordBody;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.response.ErrorResponse;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.response.OkResponse;
import ru.sberbank.kuzin19190813.authorizationadminapi.service.UserService;

@RestController
        //("/user")
public class UsersController {
    UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ResponseEntity<OkBody> ping() {
        System.out.println("ping!");
        return new OkResponse("ping success");
    }

    @GetMapping("/{userId}/block")
    public @ResponseBody ResponseEntity<?> blockUser(@PathVariable Long userId) {
        return addRole(new SetRoleBody(userId, Role.BLOCKED));
    }

    @PostMapping("/role/add")
    public @ResponseBody ResponseEntity<?> addRole(SetRoleBody setRoleBody) {
        return changeRoles(setRoleBody, userService::addRole);
    }

    @PostMapping("/role/remove")
    public @ResponseBody ResponseEntity<?> removeRole(SetRoleBody setRoleBody) {
        return changeRoles(setRoleBody, userService::removeRole);
    }

    @PostMapping("/update-password")
    public @ResponseBody ResponseEntity<?> setRole(UpdatePasswordBody updatePasswordBody) {
        return executeResponse(() -> {
            String newPass = updatePasswordBody.getNewPassword();
            Long userId = updatePasswordBody.getUserId();
            userService.changeUserPassword(userId, newPass);
        });
    }

    @PostMapping("/add")
    public @ResponseBody ResponseEntity<?> addUser(AddUserBody addUserBody) {
        return executeResponse(() -> {
            String name = addUserBody.getName();
            String login = addUserBody.getLogin();
            String password = addUserBody.getPassword();
            userService.addUser(name, login, password);
        });
    }

    private ResponseEntity<?> changeRoles(SetRoleBody setRoleBody, ChangeRoleBiConsumer changeRoleBiConsumer) {
        return executeResponse(() -> {
            Long userId = setRoleBody.getUserId();
            Role role = setRoleBody.getRole();
            changeRoleBiConsumer.change(userId, role);
        });
    }

    public ResponseEntity<?> executeResponse(Executor executor) {
        System.out.println("helloooo");
        try {
            executor.execute();
        } catch (ApiException e) {
            e.printStackTrace();
            return new ErrorResponse(e.getMessage());
        }
        return new OkResponse();
    }

    private interface Executor {
        void execute() throws ApiException;
    }

    private interface ChangeRoleBiConsumer {
        void change(Long userId, Role role) throws NotCompatibleRoleException;
    }
}
