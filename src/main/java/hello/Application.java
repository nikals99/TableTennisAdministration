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


            repository.save(new Match( "Niklas", "Stefan", "4,4,4"));
            repository.save(new Match( "Rene", "Thomas", "3,-2,3,-5,-5"));
            repository.save(new Match( "Peter", "Jens", "3,-2,3,-5,-5"));
            repository.save(new Match( "Jens", "Stefan", "3,-2,3,-5,-5"));
            repository.save(new Match( "Peter", "Sven", "3,-2,3,-5,-5"));
            repository.save(new Match( "Rene", "Peter", "3,-2,3,-5,-5"));
            repository.save(new Match( "Sven", "Bernhard", "3,-2,3,-5,-5"));

        };
    }

}
