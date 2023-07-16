package com.app.journeymemo.login.service;


import com.app.journeymemo.login.dto.UserDto;
import com.app.journeymemo.login.exception.InvalidEmailException;
import com.app.journeymemo.login.mapper.UserMapper;
import com.app.journeymemo.login.model.User;
import com.app.journeymemo.login.repository.UserRepository;
import com.app.journeymemo.login.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public void createUser(UserRequest userRequest) {
        final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(userRequest.getEmail());
        if (matcher.matches()){
            var user = userMapper.mapToUserFromRequest(userRequest);
            userRepository.save(user);
        }else throw new InvalidEmailException("Email format is not correct");
    }

    @Override
    public List<UserDto> getAllUser() {
        return null;
    }

    @Override
    public UserDto getUserById(Long id) {
        return null;
    }

    @Override
    public void updateUser(Long id, UserRequest userRequest) {

    }

    @Override
    public void deleteUser(Long id) {

    }
}
