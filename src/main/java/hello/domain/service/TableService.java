package hello.domain.service;

import hello.domain.model.MatchRepository;
import hello.domain.model.PlayerForTable;
import hello.domain.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {

    @Autowired
    private MatchRepository repository;

    public List<PlayerForTable> getTable(){

        Table table = new Table();



        return table.populateTable(repository);

    }
}
