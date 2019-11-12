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

	@RequestMapping(method = RequestMethod.POST)// anotação que identifica que o metodo é uma função REST , associando o metodo a algum dos verbos do HTTP no caso POST
	public ResponseEntity<Void> insert(@RequestBody User obj) {//tipo void retorna uma resposta com o "corpo" vazio,anotação @requestbody faz o JSON serconvertido para objeto java 
		obj = uService.insertUser(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(obj.getId()).toUri();// boas praticas indicam que alem do status, deve se retonar o URI em caso de criação de um novo objeto
		return ResponseEntity.created(uri).build();// retornando o status CREATED com a uri no corpo da resposta
	}

	@RequestMapping(method = RequestMethod.GET)//anotação que identifica que o metodo é uma função REST , associando o metodo a algum dos verbos do HTTP no caso GET
	public ResponseEntity<List<User>> findAll() {
		List<User> lista = uService.findAll();// busca a lista de usuarios cadastrados
		return ResponseEntity.ok().body(lista);//ResponseEntity é um tipo do spring que armazena diversas informaçãoes de uma resposta HTTP para um serviço REST
	}  // metodo "ok" do ResponseEntity indica que a operação ocorreu com sucesso, e no "corpo"(body) da resposta , vai conter a  lista de objetos encontrados na busca

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)// adicionando ao endpoint o id do usuario, o verbo HTTP GET informa que se trata de uma consulta
	public ResponseEntity<User> findById(@PathVariable Integer id) {// @pathVariable : anotação que identifica para o spring que a id que vem na url, vai preencher a variavel id
		User obj = uService.findUserById(id);//passando o id que veio na url como parametro do metodo que busca um usuario pelo id
		return ResponseEntity.ok().body(obj);//ResponseEntity é um tipo do spring que armazena diversas informaçãoes de uma resposta HTTP para um serviço REST
	}			// metodo "ok" do ResponseEntity indica que a operação ocorreu com sucesso, e no "corpo"(body) da resposta , vai conter o objeto encontrado na busca

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)// anotação que identifica que o metodo é uma função REST , associando o metodo a algum dos verbos do HTTP no caso PUT
	public ResponseEntity<Void> update(@RequestBody User obj, @PathVariable Integer id) {// recebe tanto um objeto JSON quanto o parãmetro na URL, retorna uma reposta com corpo vazio
		obj.setId(id);// seta o id do obj passado com o id que foi passado no argumento
		obj = uService.updateUser(obj);// chamando o metodo de atualiazar do service
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)// anotação que identifica que o metodo é uma função REST , associando o metodo a algum dos verbos do HTTP no caso DELETE
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {// @pathVariable : anotação que identifica para o spring que a id que vem na url, vai preencher a variavel id
		uService.deleteUser(id);// chamada do metodo para deletar do service
		return ResponseEntity.noContent().build();
	}
}
