package com.valter.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valter.test.domain.User;
import com.valter.test.repository.UserRepository;
import com.valter.test.service.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findUserById(Integer id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getSimpleName()));

	}

	public User insertUser(User obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public User updateUser(User obj) {

		User newObj = findUserById(obj.getId());
		return repo.save(newObj);
	}

	public void deleteUser(Integer id) {
		findUserById(id);

		repo.deleteById(id);

	}
}
