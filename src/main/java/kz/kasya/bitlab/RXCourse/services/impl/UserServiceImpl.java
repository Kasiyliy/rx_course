package kz.kasya.bitlab.RXCourse.services.impl;

import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.entities.Role;
import kz.kasya.bitlab.RXCourse.models.entities.User;
import kz.kasya.bitlab.RXCourse.repositories.UserRepository;
import kz.kasya.bitlab.RXCourse.services.RoleService;
import kz.kasya.bitlab.RXCourse.services.UserService;
import kz.kasya.bitlab.RXCourse.shared.utils.codes.ErrorCode;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * @author Assylkhan
 * on 10.04.2019
 * @project logistic_server
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findById(Long id) throws ServiceException {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("user not found")
                .build());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public List<User> findAllByRole(Long id) {
        return userRepository.findAllByDeletedAtIsNullAndRole_Id(id);
    }

    @Override
    public List<User> findAllWithDeleted() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) throws ServiceException {
        if (Objects.isNull(user.getId())) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("user is null")
                    .build();
        }
        return userRepository.save(user);
    }

    @Override
    public User save(User user) throws ServiceException {
        if (Objects.nonNull(user.getId())) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("user already exists")
                    .build();
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) throws ServiceException {
        if (Objects.isNull(user.getId())) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("user is null")
                    .build();
        }
        user = findById(user.getId());
        user.setDeletedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (Objects.isNull(id)) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        User user = findById(id);
        user.setDeletedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLoginAndDeletedAtIsNull(login).orElse(null);
    }

    @Override
    public Set getAuthority(User user) {
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole().getName()));
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = findByLogin(login);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthority(user));
    }

    @Override
    public List<User> findByRole(Long roleId) {
        return userRepository.findAllByDeletedAtIsNullAndRole_Id(roleId);
    }

    @Override
    public User changeUserRole(Long userId, Long roleId) {
        User user = findById(userId);
        Role role = roleService.findById(roleId);
        if (Objects.isNull(user)) {
            throw new ServiceException("No user with such id", ErrorCode.RESOURCE_NOT_FOUND);
        }
        if (Objects.isNull(role)) {
            throw new ServiceException("No role with such id", ErrorCode.RESOURCE_NOT_FOUND);
        }
        user.setRole(role);
        return save(user);
    }
}
