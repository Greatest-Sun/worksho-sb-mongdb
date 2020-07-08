package com.gabrieldb.mongodb.config;

import com.gabrieldb.mongodb.domain.Post;
import com.gabrieldb.mongodb.domain.User;
import com.gabrieldb.mongodb.dtos.AuthorDTO;
import com.gabrieldb.mongodb.dtos.CommentDTO;
import com.gabrieldb.mongodb.repositories.PostRepository;
import com.gabrieldb.mongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private UserRepository ur;
    @Autowired
    private PostRepository pr;
    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        ur.deleteAll();
        pr.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        ur.saveAll(Arrays.asList(maria, alex, bob)); //Placed here to generate user ID before the instantiation of posts.

        Post p1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem!", "Vou viajar para SP, abraços!", new AuthorDTO(maria));
        Post p2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia!", "Acordei feliz hoje!", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));

        p1.getComments().addAll(Arrays.asList(c1, c2));
        p2.getComments().addAll(Arrays.asList(c3));

        pr.saveAll(Arrays.asList(p1, p2));

        maria.getPosts().addAll(Arrays.asList(p1, p2));

        ur.save(maria);



    }
}
