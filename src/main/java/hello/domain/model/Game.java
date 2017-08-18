package hello.domain.model;


import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "game", schema = "db_tt")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="player1")
    private String player1;
    @Column(name="player2")
    private String player2;
    @Column(name="result")
    private String result;
   // private String[] resultDetail;

    protected Game() {

    }

    public Game(String player1, String player2, String result) {
        this.player1 = player1;
        this.player2 = player2;
        this.result = result;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public int getIdent(){
        return id;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public String getResult() {
        String[] tmp = result.split(",");
        int winsA = 0, winsB = 0;
        for(int i = 0; i<tmp.length;i++){
            if(Integer.valueOf(tmp[i])>0){
               winsA++;
            }else {
                winsB++;
            }
        }
        return winsA + ":" + winsB;

    }

    public String[] getResultDetail() {
        String[] resultDetail;
        resultDetail = result.split(",");
        for(int i = 0; i < resultDetail.length; i++){
            int r = Integer.valueOf(resultDetail[i]);
            if(r > 0 && r<= 9){
                resultDetail[i] = "11:"+r;
            }else if(r > 9){
                resultDetail[i] = r+2 +":"+r;
            }else if(r>=-9){
                resultDetail[i] = Math.abs(r)+":11";
            }else{
                resultDetail[i] = Math.abs(r)+":11"+Math.abs(r)+2;
            }


        }
        return resultDetail;

    }
}
