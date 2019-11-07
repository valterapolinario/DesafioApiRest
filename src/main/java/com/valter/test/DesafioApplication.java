package com.valter.test;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.valter.test.domain.Twitter;
import com.valter.test.domain.User;
import com.valter.test.repository.TwitterRepository;
import com.valter.test.repository.UserRepository;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {
	@Autowired
	UserRepository uRepository;
	@Autowired
	TwitterRepository tRepository;

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Twitter twitter1 = new Twitter(null, "esta funcionando o tweet");
		User user1 = new User(null, "alan", "alan@gmail.com");
		
		twitter1.setUser(user1);
		user1.getTweets().addAll(Arrays.asList(twitter1));
		
		

		uRepository.save(user1);
		tRepository.save(twitter1);
	}

}
