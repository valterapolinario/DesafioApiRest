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
		Twitter twitter2 = new Twitter(null, "esta funcionando o tweet e a coisa ta melhorando");
		Twitter twitter3 = new Twitter(null, "esta funcionando o tweet  e testanto o top 10");
		Twitter twitter4 = new Twitter(null, "esta funcionando o tweet este é o 4");
		Twitter twitter5 = new Twitter(null, "esta funcionando o tweet este é o 5");
		Twitter twitter6 = new Twitter(null, "esta funcionando o tweet este é o 6");
		Twitter twitter7 = new Twitter(null, "esta funcionando o tweet este é o 7");
		Twitter twitter8 = new Twitter(null, "esta funcionando o tweet este é o 8");
		Twitter twitter9 = new Twitter(null, "esta funcionando o tweet este é o 9");
		Twitter twitter10 = new Twitter(null, "esta funcionando o tweet este é o 10");
		Twitter twitter11 = new Twitter(null, "esta funcionando o tweet este é o 11");
		User user1 = new User(null, "alan", "alan@gmail.com");

		twitter1.setUser(user1);
		twitter2.setUser(user1);
		twitter3.setUser(user1);
		twitter4.setUser(user1);
		twitter5.setUser(user1);
		twitter6.setUser(user1);
		twitter7.setUser(user1);
		twitter8.setUser(user1);
		twitter9.setUser(user1);
		twitter10.setUser(user1);
		twitter11.setUser(user1);
		user1.getTweets().addAll(Arrays.asList(twitter1, twitter2, twitter3, twitter4, twitter5, twitter6, twitter7,
				twitter8, twitter9, twitter10, twitter11));

		uRepository.save(user1);
		tRepository.saveAll(Arrays.asList(twitter1, twitter2, twitter3, twitter4, twitter5, twitter6, twitter7,
				twitter8, twitter9, twitter10, twitter11));
	}

}
