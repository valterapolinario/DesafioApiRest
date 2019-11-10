package com.valter.test.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.valter.test.domain.Twitter;
import com.valter.test.domain.User;
import com.valter.test.service.TwitterService;
import com.valter.test.service.UserService;

@RestController
@RequestMapping(value = "tweets")
public class TwitterResources {

	@Autowired
	TwitterService tService;
	@Autowired
	UserService uService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Twitter obj) {
		obj = tService.insertTwitter(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Twitter>> findAll() {
		List<Twitter> lista = tService.findTwitterAll();
		return ResponseEntity.ok().body(lista);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Twitter> find(@PathVariable Integer id) {
		Twitter obj = tService.findTwitterById(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Twitter obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = tService.updateTwitter(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteTwitter(@PathVariable Integer id) {
		tService.deleteTwitter(id);
		return ResponseEntity.noContent().build();
	}

	/*
	 * @RequestMapping(value = "/{user}/{id}", method = RequestMethod.GET) public
	 * ResponseEntity<List<Twitter>> findByUser(@PathVariable Integer id) { User
	 * uObj = uService.findUserById(id); List<Twitter> lista =
	 * tService.findTwitterByUser(uObj);
	 * 
	 * return ResponseEntity.ok().body(lista); }
	 */

	@RequestMapping(value = "/{user}/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Twitter>> findTop10ByUser(@PathVariable Integer id) {
		User uObj = uService.findUserById(id);
		List<Twitter> lista = tService.findTop10ByUserOrderByDesc(uObj);

		return ResponseEntity.ok().body(lista);
	}

	@RequestMapping(value = "/{user}/{follow}", method = RequestMethod.GET)
	public ResponseEntity<List<Twitter>> findByUserAndFollower() {

		List<Twitter> lista = tService.findByUserAndFollower();

		return ResponseEntity.ok().body(lista);
	}

	@RequestMapping(value = "/{follow}/{id_usuario}/{id_seguidor}", method = RequestMethod.POST)
	public void insert(@PathVariable Integer id_user, Integer id_segui) {
		tService.insertUserFollower(id_user, id_segui);
	}
}
