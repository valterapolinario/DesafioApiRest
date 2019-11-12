package com.valter.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valter.test.domain.User;
import com.valter.test.repository.UserRepository;
import com.valter.test.service.exceptions.ObjectNotFoundException;

@Service // anotação que indica que esta classe será uma classe que fornece "serviços" entre uma camada e outra da aplicação
public class UserService {
	@Autowired// anotação que identifica a injeção de dependencia
	private UserRepository repo;

	public List<User> findAll() {// metodo que retorna uma lista com todos os registros(objetos) de usuario
		return repo.findAll();
	}

	public User findUserById(Integer id) {// metodo que retorna um unico registro(objeto) usando o id como filtro de busca
		Optional<User> obj = repo.findById(id);// uso do optional, um tipo do java 8 que funciona como um container(*caixa) para guardar o objeto retornado, caso objeto esteja nulo(a busca não encotrou resultados e retorna nulo) evita o NullPointerException. 
		return obj.orElseThrow(() -> new ObjectNotFoundException(// uso de uma função anonima, para retornar um objeto caso exista, senão retornar a exceção personalizada
				"Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getSimpleName()));// mesagem de retorno do erro que mostra o id não encontrado e tipo do objeto

	}

	public User insertUser(User obj) {// metodo para chamar o serviço de inserir um registro(objeto) no banco
		obj.setId(null);// usado para garantir que o id do objeto seja null , que o mesmo será gerado automatico no banco
		return repo.save(obj);
	}

	public User updateUser(User obj) {// metodo para chamar o serviço de atualizar um registro(objeto) no banco de dados

		User newObj = findUserById(obj.getId());// metodo que busca um registro (opbjeto) no banco para ser autalizado servindo para verificar se existe o registro correspondente no banco
		return repo.save(newObj); // usado o metodo de salvar, pois é o mesmo para atualizar e salvar
	}

	public void deleteUser(Integer id) {// metodo para deletar um registro(objeto) do banco de daods usando um id como filtro
		findUserById(id);// verifica se o id passado corresponde a um que esteja registrado no banco

		repo.deleteById(id);

	}
}
