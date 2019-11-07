package com.valter.test.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valter.test.domain.Twitter;
import com.valter.test.domain.User;

@Repository
public interface TwitterRepository extends JpaRepository<Twitter, Integer> {

	List<Twitter> findByUser(User user);
}
