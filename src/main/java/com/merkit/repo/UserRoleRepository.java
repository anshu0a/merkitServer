package com.merkit.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.merkit.entity.User;
import com.merkit.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findByUser(User user);

    Optional<UserRole> findByUserIdAndRoleId(Long userId, Long roleId);

    void deleteByUserId(Long userId);

}
