package ru.sberbank.kuzin19190813.authorizationadminapi.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.user.UserDTO;

import java.util.function.Consumer;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {
    default UserDTO updateField(Long userId, Consumer<UserDTO> consumer) {
        UserDTO userDTO = findById(userId).orElse(null);
        if (userDTO == null) return null;
        consumer.accept(userDTO);
        save(userDTO);
        return userDTO;
    }

}
