package homework4;

import java.util.Scanner;
import homework4.CFPlayer;
import homework4.RandomAI;
import homework4.SarahAI;
import homework4.ConsoleCF;
//import homework4.GUICF;


public class Test {
    public static void main(String[] args) {
        Scanner reader = new Scanner (System.in);
        int gameMode = reader.nextInt();

        if (gameMode==1) {
            new GUICF(new SarahAI());
        } else if (gameMode==2) {
            CFPlayer ai1 = new SarahAI();
            CFPlayer ai2 = new RandomAI();
            int n = 10000;
            int winCount = 0;
            for (int i =0; i < n ; i++) {
                ConsoleCF game = new ConsoleCF(ai1, ai2);
                game.playOut();
                if(game.getWinner() == ai1.getName())
                    winCount++;
            }
            System.out.println(((double) winCount)/n);
        } else {
            ConsoleCF game = new ConsoleCF(new SarahAI());
            game.playOut();
            System.out.println(game.getWinner() + " has won.");
        }
    }
}
