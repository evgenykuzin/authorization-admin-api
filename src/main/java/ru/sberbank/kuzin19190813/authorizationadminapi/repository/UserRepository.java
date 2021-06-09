package ru.sberbank.kuzin19190813.authorizationadminapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.user.UserDTO;

import java.util.function.Consumer;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {
//    default void update(Long userId, Consumer<UserDTO> consumer) {
//        findById(userId)
//                .ifPresent(userDTO -> {
//                    consumer.accept(userDTO);
//                    save(userDTO);
//                });
//    }

    void update(Long userId, Consumer<UserDTO> consumer);
}
