package com.valter.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valter.test.domain.Twitter;

@Repository
public interface TwitterRepository extends JpaRepository<Twitter, Integer> {

}
