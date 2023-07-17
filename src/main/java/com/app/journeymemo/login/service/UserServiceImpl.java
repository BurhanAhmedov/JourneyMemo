package com.app.journeymemo.login.service;


import com.app.journeymemo.login.dto.UserGeneralInfoDto;
import com.app.journeymemo.login.exception.InvalidEmailException;
import com.app.journeymemo.login.mapper.UserMapper;
import com.app.journeymemo.login.repository.UserGeneralInfoRepository;
import com.app.journeymemo.login.repository.UserLoginInfoRepository;
import com.app.journeymemo.login.request.UserGeneralInfoRequest;
import com.app.journeymemo.login.request.UserLoginInfoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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
        if (matcher.matches()){
            var userGeneral = userMapper.mapToUserGeneralFromRequest(userGeneralInfoRequest);
            var userLogin = userMapper.mapToUserLoginFromRequest(userLoginInfoRequest);
            userGeneralRepository.save(userGeneral);
            userLogin.setFkUserId(userGeneral.getId());
            userLoginRepository.save(userLogin);
            log.info("User successfully created");
        }else throw new InvalidEmailException("Email format is not correct");
    }

    @Override
    public List<UserGeneralInfoDto> getAllUser() {
        return null;
    }

    @Override
    public UserGeneralInfoDto getUserById(Long id) {
        return null;
    }

    @Override
    public void updateUser(Long id, UserGeneralInfoRequest userGeneralInfoRequest) {

    }

    @Override
    public void deleteUser(Long id) {

    }
}
