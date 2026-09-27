package com.merkit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.merkit.entity.TimeCapsule;

public interface TimeCapsuleRepository extends JpaRepository<TimeCapsule, Long> {
}