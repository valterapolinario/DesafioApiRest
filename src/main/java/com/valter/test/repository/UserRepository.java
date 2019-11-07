package com.valter.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valter.test.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
