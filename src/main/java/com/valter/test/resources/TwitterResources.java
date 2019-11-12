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

@RestController // anotação que identifica que a classe é um Controlador REST
@RequestMapping(value = "tweets")//anotação que identifica o endpoint pelo qual a classe responderá ao ser chamada
public class TwitterResources {

	@Autowired// anotação que identifica a injeção de dependencia
	TwitterService tService;
	@Autowired// anotação que identifica a injeção de dependencia
	UserService uService;

	@RequestMapping(method = RequestMethod.POST)// anotação que identifica que o metodo é uma função REST , associando o metodo a algum dos verbos do HTTP no caso POST
	public ResponseEntity<Void> insert(@RequestBody Twitter obj) {//tipo void retorna uma resposta com o "corpo" vazio,anotação @requestbody faz o JSON serconvertido para objeto java 
		obj = tService.insertTwitter(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(obj.getId()).toUri();// boas praticas indicam que alem do status, deve se retonar o URI em caso de criação de um novo objeto
		return ResponseEntity.created(uri).build();// retornando o status CREATED com a uri no corpo da resposta
	}

	@RequestMapping(method = RequestMethod.GET)//anotação que identifica que o metodo é uma função REST , associando o metodo a algum dos verbos do HTTP no caso GET
	public ResponseEntity<List<Twitter>> findAll() {
		List<Twitter> lista = tService.findTwitterAll();// busca a lista de twitter cadastrados
		return ResponseEntity.ok().body(lista);//ResponseEntity é um tipo do spring que armazena diversas informaçãoes de uma resposta HTTP para um serviço REST
	}// metodo "ok" do ResponseEntity indica que a operação ocorreu com sucesso, e no "corpo"(body) da resposta , vai conter a  lista de objetos encontrados na busca

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)// adicionando ao endpoint o id do usuario, o verbo HTTP GET informa que se trata de uma consulta
	public ResponseEntity<Twitter> findById(@PathVariable Integer id) {// @pathVariable : anotação que identifica para o spring que a id que vem na url, vai preencher a variavel id
		Twitter obj = tService.findTwitterById(id);//passando o id que veio na url como parametro do metodo que busca um Twitter pelo id
		return ResponseEntity.ok().body(obj);//ResponseEntity é um tipo do spring que armazena diversas informaçãoes de uma resposta HTTP para um serviço REST
	}// metodo "ok" do ResponseEntity indica que a operação ocorreu com sucesso, e no "corpo"(body) da resposta , vai conter o objeto encontrado na busca

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)// anotação que identifica que o metodo é uma função REST , associando o metodo a algum dos verbos do HTTP no caso PUT
	public ResponseEntity<Void> update(@RequestBody Twitter obj, @PathVariable Integer id) {// recebe tanto um objeto JSON quanto o parãmetro na URL, retorna uma reposta com corpo vazio
		obj.setId(id);// seta o id do obj passado com o id que foi passado no argumento
		obj = tService.updateTwitter(obj);// chamando o metodo de atualiazar do service
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)// anotação que identifica que o metodo é uma função REST , associando o metodo a algum dos verbos do HTTP no caso DELETE
	public ResponseEntity<Void> deleteTwitter(@PathVariable Integer id) {// @pathVariable : anotação que identifica para o spring que a id que vem na url, vai preencher a variavel id
		tService.deleteTwitter(id);// chamando o metodo de deletar passando o id obtido na URL como parametro
		return ResponseEntity.noContent().build();
	}

	
	  @RequestMapping(value = "/{tweetsUser}/{id}", method = RequestMethod.GET) //anotação que identifica que o metodo é uma função REST , associando o metodo a algum dos verbos do HTTP no caso GET
	  public ResponseEntity<List<Twitter>> findByUser(@PathVariable Integer id) { // @pathVariable : anotação que identifica para o spring que a id que vem na url, vai preencher a variavel id
	  User uObj = uService.findUserById(id); // chamando o metodo que busca um usuario , utilizando o id como filtro
	  List<Twitter> lista = tService.findTwitterByUser(uObj);// chamando o metodo que busca os tweets de um usuario passando um usuario como filtro
	 
	  return ResponseEntity.ok().body(lista);//ResponseEntity é um tipo do spring que armazena diversas informaçãoes de uma resposta HTTP para um serviço REST
	  }// metodo "ok" do ResponseEntity indica que a operação ocorreu com sucesso, e no "corpo"(body) da resposta , vai conter a  lista de objetos encontrados na busca

	  
	 

	@RequestMapping(value = "/{user}/{id}", method = RequestMethod.GET)//anotação que identifica o endpoint pelo qual a classe responderá ao ser chamada
	public ResponseEntity<List<Twitter>> findTop10ByUser(@PathVariable Integer id) {// retorna uma lista com os ultimos 10 tweets de um usuario
		User uObj = uService.findUserById(id);//buscando um usuario com o id passado como parametro  na URL
		List<Twitter> lista = tService.findTop10ByUserOrderByDesc(uObj);// chamando o metodo que retorna uma lista com os ultimos 10 tweets do usuario

		return ResponseEntity.ok().body(lista);// retornando uma lista no corpo da resposta
	}

	/*metodo que busca os ultimos 10 tweets do usuario e seu seguidor, mas não consegui fazer funcionar
	 * @RequestMapping(value = "/{us}/{follow}", method = RequestMethod.GET) public
	 * ResponseEntity<List<Twitter>> findByUserAndFollower() {
	 * 
	 * List<Twitter> lista = tService.findByUserAndFollower();
	 * 
	 * return ResponseEntity.ok().body(lista); }
	 */
	// metodo para inserir um usuario e seguidor na tabela users/followers que não consegui fazer funcionar
	@RequestMapping(value = "/{follow}/{id_usuario}/{id_seguidor}", method = RequestMethod.POST)
	public void insert(@PathVariable Integer id_user, Integer id_segui) {
		tService.insertUserFollower(id_user, id_segui);
	}
}
