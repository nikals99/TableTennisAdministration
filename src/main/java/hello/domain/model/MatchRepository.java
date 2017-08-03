package hello.domain.model;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "game", path = "game")
public interface MatchRepository extends CrudRepository<Match, Integer> {
    List<Match> findByPlayer1(String name);
    List<Match> findByPlayer2(String name);
}

