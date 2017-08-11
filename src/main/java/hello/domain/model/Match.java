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
    private String[] resultDetail;

    protected Match() {

    }

    public Match(String player1, String player2, String result) {
        this.player1 = player1;
        this.player2 = player2;
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
