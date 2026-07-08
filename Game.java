public class Game {
    public int[][] Map;
    private int LENTH;
    private int HIGHT;
    private int TREASURE;

    
    public Game(int lenth, int hight, int treasure) {
        LENTH = lenth;
        HIGHT = hight;
        TREASURE = treasure;
        Map = new int[lenth][hight]; 
    }

    
    public static Game createGame(int lenth, int hight, int treasure) {
        Game game = new Game(lenth, hight, treasure);
        int total = Integer.MAX_VALUE;
        double p = (double) treasure / lenth / hight;

        while (total > treasure) {
            total = 0;
            for (int i = 0; i < lenth; i++) {
                for (int j = 0; j < hight; j++) {
                    if (Math.random() < p) {
                        game.Map[i][j] = 0;
                        total++;
                    } else {
                        game.Map[i][j] = 1;
                    }
                }
            }

            if (total < treasure) {
                for (int z = 0; z < treasure - total; z++) {
                    int RL = (int) (Math.random() * lenth);
                    int RC = (int) (Math.random() * hight);
                    if (game.Map[RL][RC] == 0) {
                        z--;
                    } else {
                        game.Map[RL][RC] = 0;
                    }
                }
                total = treasure; 
            }
        }
        return game;
    }

 


    public String play(Player name, int row, int col) {
        if (Map[row][col] == 0) {
            return name.getName()+": Your wealth has increased!";
        } else {
            return name.getName()+": Your luck will be better next time.";
        }
    }
    
    public void P(Player name, int row, int col){
        if (Map[row][col] == 0) {
            name.addScore();
        } 
        else{
            Map[row][col]=-1;
        }
    }
    public String whoWin(Player one, Player two) {
        if (one.getScore() > two.getScore()) {
            return one.getName() + " wins";
        } else if (one.getScore() < two.getScore()) {
            return two.getName() + " wins";
        } else {
            return "Both win";
        }
    }

    public void getMap() {
        for (int q = 0; q < LENTH; q++) {
            for (int w = 0; w < HIGHT; w++) {
                if (Map[q][w]==-1){
                   System.out.print("🕳");
                }
                else if (Map[q][w]==0){
                    System.out.print("⛰️");
                }
                else{
                    System.out.print("💰");
                }
                
            }
            System.out.println();
        }
        System.out.println("💰represents treasure,⛰represents non-treasure and 🕳represents the places searched ");
    }
    public void NUM(){
        int n=0;
        for (int i = 0; i < LENTH; i++) {
            for (int j = 0; j < HIGHT; j++) {
                if (Map[i][j] == 0) {
            
                    n++;
                } 
            }
        }
        System.out.print(n);
    }
}
