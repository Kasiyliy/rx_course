package kz.kasya.bitlab.RXCourse.controllers.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.kasya.bitlab.RXCourse.controllers.BaseController;
import kz.kasya.bitlab.RXCourse.exceptions.ServiceException;
import kz.kasya.bitlab.RXCourse.models.dtos.UserDto;
import kz.kasya.bitlab.RXCourse.models.entities.Role;
import kz.kasya.bitlab.RXCourse.models.entities.User;
import kz.kasya.bitlab.RXCourse.models.mappers.UserMapper;
import kz.kasya.bitlab.RXCourse.models.requests.UserRoleUpdateRequest;
import kz.kasya.bitlab.RXCourse.services.UserService;
import kz.kasya.bitlab.RXCourse.shared.utils.codes.ErrorCode;
import kz.kasya.bitlab.RXCourse.shared.utils.responses.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/users")
@Api(description = "Точка входа для распознования")
@AllArgsConstructor
public class UserController extends BaseController {

    private UserService userService;
    private UserMapper userMapper;

    @GetMapping
    @ApiOperation("Получение всех пользователей в грязном виде")
    public ResponseEntity<?> getAll() {
        return buildResponse(userMapper.toDtoList(userService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение по ID")
    public ResponseEntity<?> getOne(@ApiParam("ID элемента") @PathVariable Long id) throws ServiceException {
        return buildResponse(userMapper.toDto(userService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Регистрация пользователей")
    public ResponseEntity<?> add(@RequestBody @Validated UserDto userDto) throws ServiceException {
        User user = userMapper.toEntity(userDto);
        if (userService.findByLogin(user.getLogin()) == null) {
            Role role = new Role();
            role.setId(Role.ROLE_STUDENT_ID);
            user.setRole(role);
            user = userService.save(user);
            return buildResponse(userMapper.toDto(user), HttpStatus.OK);
        } else {
            return buildResponse("Login already exists", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/validate")
    @ApiOperation("Валидация логина")
    public ResponseEntity<?> validate(@RequestParam String login) throws ServiceException {
        User user = userService.findByLogin(login);
        if (user != null) {
            throw ServiceException.builder().message("Login exists").errorCode(ErrorCode.ALREADY_EXISTS).build();
        } else {
            return buildResponse(SuccessResponse.builder().message("OK").build(), HttpStatus.OK);
        }

    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ServiceException {
        userService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody UserDto userDto) throws ServiceException {
        userService.delete(userMapper.toEntity(userDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody UserDto userDto, Authentication authentication)
            throws ServiceException {
        User currentUser = userService.getUserByAuthentication(authentication);
        if (Objects.equals(currentUser.getId(), userDto.getId()) || currentUser.isAdmin()) {

            User user = userService.update(userMapper.toEntity(userDto));
            return buildResponse(userMapper.toDto(user), HttpStatus.OK);
        } else {
            throw new ServiceException("Unauthorized", ErrorCode.ACCESS_DENIED);
        }

    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/partial")
    public ResponseEntity<?> partialUpdate(@RequestBody UserDto userDto, Authentication authentication)
            throws ServiceException {
        User currentUser = userService.getUserByAuthentication(authentication);
        if (Objects.equals(currentUser.getId(), userDto.getId()) || currentUser.isAdmin()) {
            User user = userService.partialUpdate(userMapper.toEntity(userDto), currentUser.isAdmin());
            return buildResponse(userMapper.toDto(user), HttpStatus.OK);
        } else {
            throw new ServiceException("Unauthorized", ErrorCode.ACCESS_DENIED);
        }

    }

    @PostMapping("/current")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) throws ServiceException {
        return buildResponse(SuccessResponse.builder()
                .message("found")
                .data(userMapper.toDto(userService.getUserByAuthentication(authentication)))
                .build(), HttpStatus.OK);
    }

    @GetMapping("/roles/{roleId}")
    @ApiOperation("По ролям")
    public ResponseEntity<?> getByRole(@PathVariable Long roleId) {
        return buildResponse(userMapper.toDtoList(userService.findByRole(roleId)), HttpStatus.OK);
    }

    @PostMapping("/update/role")
    public ResponseEntity<?> updateRoleOfUser(@RequestBody @Validated UserRoleUpdateRequest userRoleUpdateRequest) {
        return buildSuccessResponse(
                userService.changeUserRole(userRoleUpdateRequest.getUserId(), userRoleUpdateRequest.getRoleId()));
    }

}
