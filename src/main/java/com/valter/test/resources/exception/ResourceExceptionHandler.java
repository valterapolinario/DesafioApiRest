package com.valter.test.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.valter.test.service.exceptions.ObjectNotFoundException;

@ControllerAdvice // anotação que permite consolidar um unico componente global de tratamento de erros
public class ResourceExceptionHandler { //classe  para interceptar e manipular exceções
	@ExceptionHandler(ObjectNotFoundException.class)// anotação que indica que é um metodo tratador de exceções
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {// recebe uma exceção como parametro e um HttpServletRequest(informações de requisição)

		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());// passando os dados do erro(no caso objeto não encontrado)inclusive passando o status a ser retornado(no caso o 404)

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);//retornando o status e o objeto de erro

	}
}
