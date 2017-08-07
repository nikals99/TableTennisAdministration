package hello;



import hello.domain.model.Match;
import hello.domain.model.MatchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    MatchRepository repository;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {


            repository.save(new Match( "Niklas", "Stefan", "1:3"));
            repository.save(new Match( "Rene", "Bernhard", "2:3"));
            repository.save(new Match( "Sven", "Niklas", "3:1"));
            repository.save(new Match( "Sven", "Niklas", "2:3"));
            repository.save(new Match( "Sven", "Thomas", "3:2"));



        };
    }

}
