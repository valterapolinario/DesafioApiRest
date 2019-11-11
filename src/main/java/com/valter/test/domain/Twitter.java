package com.valter.test.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity// anotação para indicar que a classe vai ter uma tabela representando no ban de dados
public class Twitter implements Serializable {// interface que permite que a classe seja serializada em "zeros e ums" podendo trafegar via rede. é um requesito do JPA

	private static final long serialVersionUID = 1L;
	@Id // anotação que identifica que este atributo sera a chave primaria da tabela que será criada no banco
	@GeneratedValue(strategy = GenerationType.IDENTITY)// anotaçãoq que  idenficar que o campo será gerdo automaticamente pelo banco de dados
	private Integer id;
	private String tweet;
	@JsonIgnore // anotação que impede a redudancia ciclica no JSON, usada no atributo que se deseja deixar de fora da exibição
	@ManyToOne //anotação que idenfica qual tipo de relacionamento com a classe
	@JoinColumn(name = "user_id") // anotação que identifica a coluna de ligação entre as tabelas( chave estrangeira)
	private User user;

	public Twitter() {// metodo construtor padrão sem parãmetros

	}

	public Twitter(Integer id, String tweet) {// metodo construtor com paramentros
		super();
		this.id = id;
		this.tweet = tweet;
	}

	public Integer getId() { // metodo utilizado para "pegar" o valor de um atributo
		return id;
	}

	public void setId(Integer id) {// metodo utilizado para atribuir um valor de um atributo
		this.id = id;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {// metodo usado para comparações entre objetos
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {// metodo usado para comparações entre objetos
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Twitter other = (Twitter) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
