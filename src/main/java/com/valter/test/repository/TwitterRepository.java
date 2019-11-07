package com.valter.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.valter.test.domain.Twitter;
import com.valter.test.domain.User;

@Repository
public interface TwitterRepository extends JpaRepository<Twitter, Integer> {

	List<Twitter> findByUser(User user);

	@Transactional(readOnly = true)
	List<Twitter> findTop10ByUserOrderByIdDesc(User user);
}
