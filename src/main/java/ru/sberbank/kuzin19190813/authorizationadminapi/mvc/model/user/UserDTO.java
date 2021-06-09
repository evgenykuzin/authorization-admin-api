package ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.model.role.Role;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "SBER_USER")
@Entity(name = "SBER_USER")
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "user_name")
    String name;

    @Column(name = "login")
    String login;

    @Column(name = "password")
    String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Column(name = "roles", nullable = false)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    Set<Role> roles;
}
