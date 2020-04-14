package com.codesquad.todo2.domain.user;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT name FROM user WHERE id = :id")
    Optional<String> findNameById(Long id);
}
