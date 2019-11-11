package com.valter.test.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity // anotação para indicar que a classe vai ter uma tabela representando no ban de dados
public class User implements Serializable {// interface que permite que a classe seja serializada em "zeros e ums" podendo trafegar via rede. é um requesito do JPA

	private static final long serialVersionUID = 1L;
	@Id // anotação que identifica que este atributo sera a chave primaria da tabela que será criada no banco
	@GeneratedValue(strategy = GenerationType.IDENTITY)// anotaçãoq que  idenficar que o campo será gerdo automaticamente pelo banco de dados
	private Integer id;
	private String nome;
	private String email;

	@OneToMany(mappedBy = "user") // anotação que idenfica qual tipo de relacionamento com a classe, e por qual atributo setá mapeado, mostrando tb que esta será a entdidade fraca do relacionamento
	private List<Twitter> tweets = new ArrayList<>();

	public User() {// metodo construtor padrão sem parãmetros

	}

	public User(Integer id, String nome, String email) { // metodo construtor com paramentros
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public Integer getId() {// metodo utilizado para "pegar" o valor de um atributo
		return id;
	}

	public void setId(Integer id) {// metodo utilizado para atribuir um valor de um atributo
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Twitter> getTweets() {
		return tweets;
	}

	public void setTweets(List<Twitter> tweets) {
		this.tweets = tweets;
	}

	@Override
	public int hashCode() { // metodo usado para comparações entre objetos
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
