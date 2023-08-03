package com.app.journeymemo.login.service;


import com.app.journeymemo.login.dto.UserDto;
import com.app.journeymemo.login.exception.UserNotFoundException;
import com.app.journeymemo.login.mapper.UserMapper;
import com.app.journeymemo.login.repository.UserRepository;
import com.app.journeymemo.login.request.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<String> createUser(UserRequest userRequest) {
        final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(userRequest.getEmail());
        if (userRepository.checkUsernameUnique(userRequest.getUsername()) == 0) {
            if (userRepository.findUserByEmail(userRequest.getEmail()).isEmpty()) {
                if (matcher.matches()) {
                    var user = userMapper.mapToUserGeneralFromRequest(userRequest);
                    userRepository.save(user);
                    log.info("User successfully is created");
                    return ResponseEntity.ok("Registration is successful");
                } else {
                    log.error("Email format is not correct: " + userRequest.getEmail());
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email format is not correct");
                    //throw new InvalidEmailException("Email format is not correct: " + userRequest.getEmail());
                }
            } else {
                log.error("Email already exists: " + userRequest.getEmail());
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
                // throw new UserAlreadyExistsException("Email already exists: " + userRequest.getEmail());
            }
        } else {
            log.error("Username already exists: " + userRequest.getUsername());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
            // throw new UserAlreadyExistsException("Username already exists: " + userRequest.getUsername());
        }
    }

    @Override
    public List<UserDto> getAllUser() {
        var userGeneralList = userRepository.findAll();
        var userGeneralInfoDtoList = userMapper.mapToUserListDto(userGeneralList);
        return userGeneralInfoDtoList;
    }

    @Override
    public ResponseEntity<UserDto> getUserById(String id) {
        var user = userRepository.findById(id);
        if (user.isPresent()) {
            var userDto = userMapper.mapToUserDto(user.get());
            return ResponseEntity.ok(userDto);
        }
        log.error("User Not Found by id:" + id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserDto());
    }

    @Override
    public ResponseEntity<UserDto> getUserByUsername(String username) {
        var user = userRepository.findUserByUsername(username);
        if (user!=null) {
            var userDto = userMapper.mapToUserDto(user);
            return ResponseEntity.ok(userDto);
        }
        log.error("User not found by username:" +username);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserDto());
    }


    @Override
    public void updateUserById(String id, UserRequest userRequest) {
        if (userRepository.existsById(id)) {
            userRepository.updateUserById(
                    userRequest.getName(),
                    userRequest.getSurname(),
                    userRequest.getBirthday(),
                    userRequest.getCountry(),
                    userRequest.getGender(),
                    userRequest.getUsername(),
                    userRequest.getEmail(),
                    userRequest.getPassword(),
                    id);
            log.info("User general info successfully is updated by " + id);
        } else
            throw new UserNotFoundException("User not found by " + id);

    }

    @Override
    public void deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            log.info("User successfully is deleted by" + id);
        } else
            throw new UserNotFoundException("User not found by " + id);
    }

    @Override
    public ResponseEntity<String> loginUser(String username, String password) {
        var user = userRepository.findUserByUsername(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return ResponseEntity.ok("Welcome " + username);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password is not correct");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username is not exists");
    }
}
