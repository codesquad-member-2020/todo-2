package com.codesquad.todo2.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String findNameById(Long id) {
        Optional<String> name = userRepository.findNameById(id);
        return name.orElse(null); // TODO: handle 404 with orElseThrow
    }
}
