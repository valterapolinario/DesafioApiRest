package com.valter.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.valter.test.domain.Twitter;
import com.valter.test.domain.User;

@Repository // anotação que identifica que a classe é um repositorio( reponsavel pela manipulação e persistencia de dados)
public interface TwitterRepository extends JpaRepository<Twitter, Integer> { 

	List<Twitter> findByUser(User user); // metodo que busca os tweets de um determinado usuario, usando o usuario como filtrode busca, implemntado usando palavars contempladas pelo JPARepository

	@Transactional(readOnly = true) // anotação que indica que este metodo funciona por meio de uma transação( apesar do metodo ser apenas uma leitura do banco de dados, por motivo que desconheço, só funcionou com a anotação)
	List<Twitter> findTop10ByUserOrderByIdDesc(User user);// metodo que busca os 10 ultimos tweets de um usuario, implementado usando palavras contempladas pelo JPARepository
	
	@Transactional(readOnly =  true)// anotação que indica que este metodo funciona por meio de uma transação( apesar do metodo ser apenas uma leitura do banco de dados, por motivo que desconheço, só funcionou com a anotação)
	@Query(value = "SELECT t.tweet FROM Twitter as t WHERE t.user_id"
			+ " IN(SELECT us.id FROM user as us inner join users_folowwers as uf on %us.id% = uf.id_usuario"
			+ "inner join Twitter as t on %uf.id_usuario% = t.user_id ORDER BY id DESC LIMIT 10",nativeQuery = true)// metodo implementado usando query nativa(linguagem de banco de dados) para buscar os 10 ultimos tweets de um usuario seguido e seguidor
	List<Twitter>findByUserAndFollower();// metodo que busca os tweets de um usuario seguido e seguidor
	
	@Transactional(readOnly = true)// anotação que indica que este metodo funciona por meio de uma transação
	@Query(value = "INSERT into  users_folowwers(%id_usuario%,%id_seguidor%)" + 
			"values (?,?)",nativeQuery = true)// metodo implementado usando query nativa(linguagem de banco de dados) para inserir um usuario seguido e seguidor
	void insertUserFollower(@Param("id_usuario")Integer idUsuario,@Param("id_seguidor")Integer idSeguidor);// metodo que insere um usuario seguido e seguidor 
}


