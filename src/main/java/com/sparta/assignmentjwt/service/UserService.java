package com.sparta.assignmentjwt.service;

import com.sparta.assignmentjwt.dto.ResponseDto;
import com.sparta.assignmentjwt.dto.TokenDTO;
import com.sparta.assignmentjwt.dto.UserLoginDTO;
import com.sparta.assignmentjwt.dto.UserSignupDTO;
import com.sparta.assignmentjwt.entity.User;
import com.sparta.assignmentjwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    //username이 DB에 있는지 확인
    private boolean usernameOverlapCheck(String username) {
        User findUser = userRepository.findByUsername(username);
        return findUser == null;
    }

    //username이 양식에 맞는지 확인
    private boolean usernameCheck(String username) {
//        Pattern pUsername = Pattern.compile("^[a-zA-Z0-9]$");
//        Matcher mUsername = pUsername.matcher(username);
//        if (username.length() < 4 || username.length() > 12) {
//            return false;
//        }
//        if (!mUsername.matches()) {
//            return false;
//        }
//        return true;
        return Pattern.matches("^[a-zA-Z0-9]{4,12}$", username);
    }

    //possword가 양식에 맞는지 확인
    private boolean passwordCheck(String password) {
//        Pattern PPassword = Pattern.compile("^[a-z0-9]$");
//        Matcher mPassword = PPassword.matcher(password);
//        if (password.length() < 4 || password.length() > 32) {
//            return false;
//        }
//        if (!mPassword.matches()) {
//            return false;
//        }
//        return true;
        return Pattern.matches("^[a-z0-9]{4,32}$", password);
    }

    //회원가입
    @Transactional
    public ResponseEntity<?> register(UserSignupDTO userSignupDTO) {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        ////username이 DB에 있는지 확인
        String username = userSignupDTO.getUsername();
        String password = userSignupDTO.getPassword();
        if (!usernameOverlapCheck(username)) {
            return new ResponseEntity<>(ResponseDto.fail("중복된 아이디입니다."), HttpStatus.OK);
        }

        //username과 password가 양식에 맞는지 확인
        if (!usernameCheck(username)) {
            return new ResponseEntity<>(ResponseDto.fail("양식에 맞지 않는 아이디입니다. 4-12자의 영문 대소문자, 숫자만 사용 가능합니다."), HttpStatus.OK);
        } else if (!password.equals(userSignupDTO.getPasswordConfirm())) {
            return new ResponseEntity<>(ResponseDto.fail("비밀번호와 비밀번호 확인이 일치하지 않습니다."), HttpStatus.OK);
        } else if (!passwordCheck(password)) {
            return new ResponseEntity<>(ResponseDto.fail("양식에 맞지 앟는 비밀번호입니다. 4-32자의 영문 소문자, 숫자만 사용 가능합니다."), HttpStatus.OK);
        }
        userLoginDTO.setUsername(username);
        userLoginDTO.setPassword(passwordEncoder.encode(password));
        User saveUser = userRepository.save(new User(userLoginDTO));
        return new ResponseEntity<>(ResponseDto.success(saveUser), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        if (usernameOverlapCheck(username)){
            return new ResponseEntity<>(ResponseDto.fail("회원가입을 진행해주세요."), HttpStatus.OK);
        }

        String passwordCheck = userRepository.findByUsername(username).getPassword();
        if (!passwordEncoder.matches(userLoginDTO.getPassword(), passwordCheck)) {
            return new ResponseEntity<>(ResponseDto.fail("비밀번호가 맞지 않습니다."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ResponseDto.success("로그인 성공"), HttpStatus.OK);
        }
    }
}
