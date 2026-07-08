import java.util.ArrayList;

public class Player {
    private String NAME;
    private int SCORE;

    public Player(String nameOfPlayer){
        NAME=nameOfPlayer;
        SCORE=0;
        
    }
    
    public void addScore(){
        SCORE++;
    }
    
    public int getScore(){
        return SCORE;
    }
    
    public String getName(){
        return NAME;
    }
    
    
    }
    
    

