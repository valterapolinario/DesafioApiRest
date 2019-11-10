package com.valter.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.valter.test.domain.Twitter;
import com.valter.test.domain.User;

@Repository
public interface TwitterRepository extends JpaRepository<Twitter, Integer> {

	List<Twitter> findByUser(User user);

	@Transactional(readOnly = true)
	List<Twitter> findTop10ByUserOrderByIdDesc(User user);
	
	@Transactional(readOnly =  true)
	@Query(value = "SELECT t.tweet FROM Twitter as t WHERE t.user_id"
			+ " IN(SELECT us.id FROM user as us inner join users_folowwers as uf on us.id = uf.id_usuario"
			+ "inner join Twitter as t on uf.id_usuario = t.user_id ORDER BY id DESC LIMIT 10",nativeQuery = true)
	List<Twitter>findByUserAndFollower();
	
	@Transactional(readOnly = true)
	@Query(value = "INSERT into  users_folowwers(%id_usuario%,%id_seguidor%)" + 
			"values (?,?)",nativeQuery = true)
	void insertUserFollower(@Param("id_usuario")Integer idUsuario,@Param("id_seguidor")Integer idSeguidor);
}
