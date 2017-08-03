package hello.domain.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String player1, player2, result;

    protected Match(){

    }

    public Match(int id, String player1, String player2, String result) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.result = result;
    }



    public int getId() {
        return id;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public String getResult() {
        return result;
    }
}
