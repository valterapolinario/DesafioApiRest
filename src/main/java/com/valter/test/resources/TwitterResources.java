package com.valter.test.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.valter.test.domain.Twitter;
import com.valter.test.service.TwitterService;

@RestController
@RequestMapping(value = "tweets")
public class TwitterResources {

	@Autowired
	TwitterService uService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Twitter obj) {
		obj = uService.insertTwitter(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Twitter>> findAll() {
		List<Twitter> lista = uService.findTwitterAll();
		return ResponseEntity.ok().body(lista);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Twitter> find(@PathVariable Integer id) {
		Twitter obj = uService.findTwitterById(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Twitter obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = uService.updateTwitter(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteTwitter(@PathVariable Integer id) {
		uService.deleteTwitter(id);
		return ResponseEntity.noContent().build();
	}
}
