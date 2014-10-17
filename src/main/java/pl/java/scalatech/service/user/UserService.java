package pl.java.scalatech.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.java.scalatech.domain.User;

public interface UserService {

    User findUserByLogin(String login);

    User save(User user);

    Page<User> getUsers(Pageable pageable);

    void populate();
}
