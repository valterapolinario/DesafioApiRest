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

@Service // anotação que indica que esta classe será uma classe que fornece "serviços" entre uma camada e outra da aplicação
public class TwitterService {
	@Autowired // anotação que identifica a injeção de dependencia
	private TwitterRepository repo;

	public List<Twitter> findTwitterAll() { // metodo que retorna uma lista com todos os registros(objetos) de Twitter
		return repo.findAll();
	}

	public Twitter findTwitterById(Integer id) { // metodo que retorna um unico registro(objeto) usando o id como filtro de busca
		Optional<Twitter> obj = repo.findById(id); // uso do optional, um tipo do java 8 que funciona como um container(*caixa) para guardar o objeto retornado, caso objeto esteja vazio(a busca não encotrou resultados) evita o NullPointerException. 
		return obj.orElseThrow(() -> new ObjectNotFoundException( // uso de uma função anonima, para retornar um objeto caso exista, senão retornar a exceção personalizada
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Twitter.class.getSimpleName())); // mesagem de retorno do erro que mostra o id não encontrado e tipo do objeto

	}

	public Twitter insertTwitter(Twitter obj) {// metodo para chamar o serviço de inserir um registro(objeto) no banco
		obj.setId(null); // usado para garantir que o id do objeto seja null , que o mesmo será gerado automatico no banco
		return repo.save(obj);
	}

	public Twitter updateTwitter(Twitter obj) {// metodo para chamar o serviço de atualizar um registro(objeto) no banco de dados

		Twitter newObj = findTwitterById(obj.getId());// metodo que busca um registro (opbjeto) no banco para ser autalizado
		return repo.save(newObj); // usado o metodo de salvar, pois é o mesmo para atualizar e salvar
	}

	public void deleteTwitter(Integer id) {// metodo para deletar um registro(objeto) do banco de daods usando um id como filtro
		findTwitterById(id);// verifica se o id passado corresponde a um que esteja registrado no banco

		repo.deleteById(id);

	}

	public List<Twitter> findTwitterByUser(User user) { // metodo para buscar todos os tweets de um usuario

		return repo.findByUser(user);

	}

	public List<Twitter> findTop10ByUserOrderByDesc(User user) {// metoddo para buscar os 10 ultimos tweets de um usuario
		return repo.findTop10ByUserOrderByIdDesc(user);
	}

	public List<Twitter> findByUserAndFollower() {// metodo para buscar os 10 ultimos tweets do usuario seguido e seguidor
		return repo.findByUserAndFollower();
	}

	public void insertUserFollower(Integer id_usuario, Integer id_seguidor) {// metodo para inserir um usuario seguido e seguidor
		Integer idUser = id_usuario;
		Integer idSegui = id_seguidor;
		repo.insertUserFollower(idUser, idSegui);
	}
}
