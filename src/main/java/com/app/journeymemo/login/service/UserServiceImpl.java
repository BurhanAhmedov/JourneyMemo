package com.app.journeymemo.login.service;


import com.app.journeymemo.login.dto.UserDto;
import com.app.journeymemo.login.exception.InvalidEmailException;
import com.app.journeymemo.login.exception.UserNotFoundException;
import com.app.journeymemo.login.exception.UsernameAlreadyExistsException;
import com.app.journeymemo.login.mapper.UserMapper;
import com.app.journeymemo.login.repository.UserRepository;
import com.app.journeymemo.login.request.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void createUser(UserRequest userRequest) {
        final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(userRequest.getEmail());
        if (userRepository.checkUsernameUnique(userRequest.getUsername()) == 0) {
            if (matcher.matches()) {
                var user = userMapper.mapToUserGeneralFromRequest(userRequest);
                userRepository.save(user);
                log.info("User successfully is created");
            } else throw new InvalidEmailException("Email format is not correct: " + userRequest.getEmail());
        } else {
            throw new UsernameAlreadyExistsException("Username already exists: " + userRequest.getUsername());
        }
    }

    @Override
    public List<UserDto> getAllUser() {
        var userGeneralList = userRepository.findAll();
        var userGeneralInfoDtoList = userMapper.mapToUserListDto(userGeneralList);
        return userGeneralInfoDtoList;
    }

    @Override
    public UserDto getUserById(Long id) {
        var user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found by id:" + id));
        var userDto = userMapper.mapToUserDto(Optional.ofNullable(user));
        return userDto;


    }

    @Override
    public void updateUserById(Long id, UserRequest userRequest) {
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
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            log.info("User successfully is deleted by" + id);
        } else
            throw new UserNotFoundException("User not found by " + id);
    }
}
