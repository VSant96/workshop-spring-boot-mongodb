package com.victorsantos.workshopmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.victorsantos.workshopmongodb.domain.Post;
import com.victorsantos.workshopmongodb.domain.User;
import com.victorsantos.workshopmongodb.dto.AuthorDTO;
import com.victorsantos.workshopmongodb.repositories.PostRepository;
import com.victorsantos.workshopmongodb.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");		
		
		userRepository.deleteAll();
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		Post post1 = new Post(null,sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para são paulo, abraços", new AuthorDTO(maria));
		Post post2 = new Post(null,sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!",new AuthorDTO(maria));
		
		postRepository.deleteAll();
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.saveAll(Arrays.asList(maria));
	}

}
