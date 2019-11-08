package com.valter.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valter.test.domain.Follower;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Integer> {

}
