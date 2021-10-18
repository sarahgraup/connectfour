package homework4;
import java.util.Random;
import java.util.Scanner;
public class ConsoleCF extends CFGame {
    public CFPlayer redPlayer;
    public CFPlayer blackPlayer;
    private int winner;

    public ConsoleCF(CFPlayer ai)
    {
        //randomly chooses which player goes first between an ai and human player
        long seed = System.currentTimeMillis();
        Random rand = new Random(seed);
        int firstplayer = rand.nextInt(1);
        if(firstplayer==0) {
            redPlayer = ai;
            blackPlayer = new HumanPlayer();
        }
        else{
            blackPlayer = ai;
            redPlayer = new HumanPlayer();
        }


    }
    public ConsoleCF(CFPlayer ai1, CFPlayer ai2)
    {
        //randomly chooses which ai goes first
        long seed = System.currentTimeMillis();
        Random rand = new Random(seed);
        int firstplayer = rand.nextInt(1);
        if(firstplayer==0)
        {
            redPlayer = ai1;
            blackPlayer = ai2;
        }

        else
        {
            redPlayer = ai2;
            blackPlayer = ai1;
        }
    }

    public void playOut(){
        //creates new game
        CFGame g = new CFGame();
        while(!g.isGameOver()){
            //while the game is not over, the redplayer will play their next move
            boolean correctPlay;
            g.isRedTurn = true;
            do{
                int num = redPlayer.nextMove(g);
                correctPlay = g.play(num);
            }while(correctPlay == false);
            //checks if the game is over
            if(g.isGameOver())
            {
                winner = g.winner();
                break;
            }
            //switches to black player and the blackplayer will play their next move
            g.isRedTurn = false;
            do{
                int num = blackPlayer.nextMove(g);
                correctPlay = g.play(num);
            }while(correctPlay == false);
            //checks if the game is over
            if(g.isGameOver()){
                winner = g.winner();
                break;
            }
        }

    }

    //sets winner to redplayer's or blackplayer's name
    public String getWinner(){
        if(winner == 0)
            return "draw";
        else if(winner == 1)
            return redPlayer.getName();
        else
            return blackPlayer.getName();

    }

    private class HumanPlayer implements CFPlayer{
        public int nextMove(CFGame g)
        {
            //prints out state of game
            int[][]arr = g.getState();
            for(int i=0; i<7; i++)
            {
                for(int j=0; j<6; j++)
                {
                    System.out.print(arr[i][j] + "  ");
                }
                System.out.println("");
            }
            int i;
            boolean valid;
            Scanner user = new Scanner(System.in);
            //asks for valid move, if valid, plays move
           do {
               System.out.println("What is your next move?");
               i = user.nextInt();
               valid = play(i);
           }while(valid == false);
           return i;
        }
        //return value of getName
        public String getName()
        {
            return "Human player";
        }
    }
}
