package com.valter.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valter.test.domain.Follower;
import com.valter.test.repository.FollowerRepository;
import com.valter.test.service.exceptions.ObjectNotFoundException;

@Service
public class FollowerService {
	@Autowired
	private FollowerRepository repo;

	public List<Follower> findAll() {
		return repo.findAll();
	}

	public Follower findFollowerById(Integer id) {
		Optional<Follower> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Follower.class.getSimpleName()));

	}

	public Follower insertFollower(Follower obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Follower updateFollower(Follower obj) {

		Follower newObj = findFollowerById(obj.getId());
		return repo.save(newObj);
	}

	public void deleteFollower(Integer id) {
		findFollowerById(id);

		repo.deleteById(id);

	}
}
