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

import com.valter.test.domain.User;
import com.valter.test.service.UserService;

@RestController // anotação que identifica que a classe é um Controlador REST
@RequestMapping(value = "users")//anotação que identifica o endpoint pelo qual a classe responderá ao ser chamada
public class UserResources {

	@Autowired // anotação que identifica a injeção de dependencia
	UserService uService;

	@RequestMapping(method = RequestMethod.POST)// anotação que identifica que o metodo é uma função REST , associando o metodo a algum dos verbos do HTTP
	public ResponseEntity<Void> insert(@RequestBody User obj) {//
		obj = uService.insertUser(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		List<User> lista = uService.findAll();// busca a lista de usuarios cadastrados
		return ResponseEntity.ok().body(lista);//ResponseEntity é um tipo do spring que armazena diversas informaçãoes de uma resposta HTTP para um serviço REST
	}  // metodo "ok" do ResponseEntity indica que a operação ocorreu com sucesso, e no "corpo"(body) da resposta , vai conter a  lista de objetos encontrados na busca

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)// adicionando ao endpoint o id do usuario, o verbo HTTP GET informa que se trata de uma consulta
	public ResponseEntity<User> find(@PathVariable Integer id) {// @pathVariable : anotação que identifica para o spring que a id que vem na url, vai preencher a variavel id
		User obj = uService.findUserById(id);//passando o id que veio na url como parametro do metodo que busca um usuario pelo id
		return ResponseEntity.ok().body(obj);//ResponseEntity é um tipo do spring que armazena diversas informaçãoes de uma resposta HTTP para um serviço REST
	}			// metodo "ok" do ResponseEntity indica que a operação ocorreu com sucesso, e no "corpo"(body) da resposta , vai conter o objeto encontrado na busca

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody User obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = uService.updateUser(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		uService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
