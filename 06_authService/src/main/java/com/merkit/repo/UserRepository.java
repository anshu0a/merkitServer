package com.merkit.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.merkit.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@EntityGraph(attributePaths = {
            "userRoles",
            "userRoles.role"
    })
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByMobile(String mobile);

    Optional<User> findByUsernameOrEmailOrMobile(
            String username,
            String email,
            String mobile);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByMobile(String mobile);
    
    @Query("""
            SELECT u 
            FROM User u
            LEFT JOIN FETCH u.userRoles
            WHERE u.username = :username
           """)
    Optional<User> findByUsernameWithRoles(String username);
    @Query("""
            SELECT u 
            FROM User u
            LEFT JOIN FETCH u.userRoles
            WHERE u.email = :email
           """)
    Optional<User> findByEmailWithRoles(String email);

}