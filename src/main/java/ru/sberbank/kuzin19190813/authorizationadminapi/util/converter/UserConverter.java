package ru.sberbank.kuzin19190813.authorizationadminapi.util.converter;

import org.springframework.stereotype.Component;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.user.UserDTO;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.user.User;

import java.util.Random;

@Component
public class UserConverter implements Converter<User, UserDTO> {

    @Override
    public User to(UserDTO userDTO) {
        if (userDTO == null) return null;
        return new User(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getLogin(),
                userDTO.getPassword(),
                userDTO.getRoles()
        );
    }

    @Override
    public UserDTO from(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId() != null ? user.getId() : new Random().nextLong());
        userDto.setName(user.getName());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
