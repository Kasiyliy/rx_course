package kz.kasya.bitlab.RXCourse.services;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Role;
import kz.kasya.bitlab.RXCourse.models.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    User findById(Long id) throws ServiceException;

    List<User> findAllByRole(Long id);

    List<User> findAll();

    List<User> findAllWithDeleted();

    User update(User user) throws ServiceException;

    User save(User user) throws ServiceException;

    void delete(User user) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    Set getAuthority(User user);

    User findByLogin(String login);

    List<User> findByRole(Long roleId);

    User changeUserRole(Long userId, Long roleId);

    User getUserByAuthentication(Authentication authentication);

    User partialUpdate(User user, boolean isAdmin) throws ServiceException;

}
