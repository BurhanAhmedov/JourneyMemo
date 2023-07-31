package com.app.journeymemo.login.service;


import com.app.journeymemo.login.dto.UserGeneralInfoDto;
import com.app.journeymemo.login.exception.InvalidEmailException;
import com.app.journeymemo.login.exception.UserNotFoundException;
import com.app.journeymemo.login.exception.UsernameAlreadyExistsException;
import com.app.journeymemo.login.mapper.UserMapper;
import com.app.journeymemo.login.repository.UserGeneralInfoRepository;
import com.app.journeymemo.login.repository.UserLoginInfoRepository;
import com.app.journeymemo.login.request.UserGeneralInfoRequest;
import com.app.journeymemo.login.request.UserLoginInfoRequest;
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
    private final UserGeneralInfoRepository userGeneralRepository;
    private final UserLoginInfoRepository userLoginRepository;
    private final UserMapper userMapper;

    @Override
    public void createUser(UserGeneralInfoRequest userGeneralInfoRequest, UserLoginInfoRequest userLoginInfoRequest) {
        final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(userLoginInfoRequest.getEmail());
        if (userLoginRepository.checkUsernameUnique(userLoginInfoRequest.getUsername())==0) {
            if (matcher.matches()) {
                var userGeneral = userMapper.mapToUserGeneralFromRequest(userGeneralInfoRequest);
                var userLogin = userMapper.mapToUserLoginFromRequest(userLoginInfoRequest);
                userLogin.setFkUserId(userGeneral.getId());
                userLoginRepository.save(userLogin);
                userGeneralRepository.save(userGeneral);
                log.info("User successfully is created");
            } else throw new InvalidEmailException("Email format is not correct: " + userLoginInfoRequest.getEmail());
        } else {
            throw new UsernameAlreadyExistsException("Username already exists: " + userLoginInfoRequest.getUsername());
        }
    }

    @Override
    public List<UserGeneralInfoDto> getAllUser() {
        var userGeneralList = userGeneralRepository.findAll();
        var userGeneralInfoDtoList = userMapper.mapToUserListDto(userGeneralList);
        return userGeneralInfoDtoList;
    }

    @Override
    public UserGeneralInfoDto getUserById(Long id) {
        var user = userGeneralRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found by id:" + id));
        var userDto = userMapper.mapToUserDto(Optional.ofNullable(user));
        return userDto;


    }

    @Override
    public void updateGeneralInfoUser(Long id, UserGeneralInfoRequest userGeneralInfoRequest) {
        if (userGeneralRepository.existsById(id)) {
            userGeneralRepository.updateGeneralInfoById(
                    userGeneralInfoRequest.getName(),
                    userGeneralInfoRequest.getSurname(),
                    userGeneralInfoRequest.getBirthday(),
                    userGeneralInfoRequest.getCountry(),
                    userGeneralInfoRequest.getGender(),
                    id);
            log.info("User general info successfully is updated by " + id);
        } else
            throw new UserNotFoundException("User not found by " + id);

    }

    @Override
    public void updateLoginInfoUser(Long id, UserLoginInfoRequest userLoginInfoRequest) {
        if (userLoginRepository.existsById(id)) {
            if (userLoginRepository.checkUsernameUnique(userLoginInfoRequest.getUsername())==0) {
                final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
                Pattern pattern = Pattern.compile(EMAIL_REGEX);
                Matcher matcher = pattern.matcher(userLoginInfoRequest.getEmail());
                if (matcher.matches()) {
                    userLoginRepository.updateLoginInfoById(
                            userLoginInfoRequest.getEmail(),
                            userLoginInfoRequest.getPassword(),
                            userLoginInfoRequest.getUsername(),
                            id);
                    log.info("User login info successfully is updated by " + id);
                } else
                    throw new InvalidEmailException("Email format is not correct: " + userLoginInfoRequest.getEmail());

            } else
                throw new UsernameAlreadyExistsException("Username already exists:" + userLoginInfoRequest.getUsername());
        } else {
            throw new UserNotFoundException("User not found by " + id);
        }

    }

    @Override
    public void deleteUser(Long id) {
        if (userGeneralRepository.existsById(id) && userLoginRepository.existsById(id)) {
            userGeneralRepository.deleteById(id);
            userLoginRepository.deleteByFkId(id);
            log.info("User successfully is deleted by" + id);
        } else
            throw new UserNotFoundException("User not found by " + id);
    }
}
