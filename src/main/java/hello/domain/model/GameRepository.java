package hello.domain.model;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RepositoryRestResource(path = "/game")
public interface GameRepository extends CrudRepository<Game, Integer> {
    List<Game> findByPlayer1(String name);
    List<Game> findByPlayer2(String name);
}

