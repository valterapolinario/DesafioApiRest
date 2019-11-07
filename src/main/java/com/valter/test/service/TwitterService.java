package com.valter.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valter.test.domain.Twitter;
import com.valter.test.domain.User;
import com.valter.test.repository.TwitterRepository;
import com.valter.test.repository.UserRepository;
import com.valter.test.service.exceptions.ObjectNotFoundException;

@Service
public class TwitterService {
	@Autowired
	private TwitterRepository repo;

	public List<Twitter> findTwitterAll() {
		return repo.findAll();
	}

	public Twitter findTwitterById(Integer id) {
		Optional<Twitter> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Twitter.class.getSimpleName()));

	}

	public Twitter insertTwitter(Twitter obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Twitter updateTwitter(Twitter obj) {

		Twitter newObj = findTwitterById(obj.getId());
		return repo.save(newObj);
	}

	public void deleteTwitter(Integer id) {
		findTwitterById(id);

		repo.deleteById(id);

	}

	public List<Twitter> findTwitterByUser(User user) {

		return repo.findByUser(user);

	}
}
