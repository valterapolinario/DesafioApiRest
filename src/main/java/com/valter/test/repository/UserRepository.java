package com.valter.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valter.test.domain.User;

@Repository// anotação que identifica que a classe é um repositorio( reponsavel pela manipulação e persistencia de dados)
public interface UserRepository extends JpaRepository<User, Integer> {

}
