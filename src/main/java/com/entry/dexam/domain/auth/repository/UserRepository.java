package com.entry.dexam.domain.auth.repository;

import com.entry.dexam.domain.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("""
        select u
        from User u
        left join fetch u.classInfo
        where u.email = :email
    """) // Null 고려
    Optional<User> findByEmailWithClassInfo(String email);
}