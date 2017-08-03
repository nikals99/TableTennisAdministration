package hello.domain.controller;

import hello.domain.model.PlayerForTable;
import hello.domain.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class helloController {

    @Autowired
    TableService tableService;


    @RequestMapping("/")
    public List<PlayerForTable> test(){
        return tableService.getTable();
    }

}
