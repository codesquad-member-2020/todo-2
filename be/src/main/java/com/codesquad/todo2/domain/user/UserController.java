package com.codesquad.todo2.domain.user;

import com.codesquad.todo2.auth.JwtUtil;
import com.codesquad.todo2.api.ResponseBodyWrapper;
import com.codesquad.todo2.exception.IncorrectInputException;
import com.codesquad.todo2.exception.NotFoundUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public ResponseEntity<ResponseBodyWrapper> login(@RequestParam("name") String name,
                                @RequestParam("password") String password,
                                HttpServletResponse response) {
        final String NOT_FOUND_USER_ERROR_MESSAGE = "회원정보가 존재하지 않습니다.";
        final String INCORRECT_INPUT_ERROR_MESSAGE = "인증에 실해하였습니다.";
        log.debug("name : {}, password : {}", name, password);
        User loginUser = userRepository.findByName(name).orElseThrow(() -> new NotFoundUserException(NOT_FOUND_USER_ERROR_MESSAGE));
        log.debug(loginUser.toString());
        if (loginUser.isDifferentPassword(password)) {
            throw new IncorrectInputException(INCORRECT_INPUT_ERROR_MESSAGE);
        }
        String token = JwtUtil.createToken(loginUser.getName(), loginUser.getPassword());
        response.setHeader("Authorization", token);
        return ResponseEntity.ok(ResponseBodyWrapper.ok());
    }
}
