package homework4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
public class GUICF extends CFGame {
    private int winner;
    public CFPlayer redPlayer;
    public CFPlayer blackPlayer;
    public String humanPlayer;
    CFGame g = new CFGame();
    public static JFrame frame;
    private GameBoard this_board;

    //creates a game with two ais and randomly chooses who goes first, or if ai2 is null, ai1 goes first
    public GUICF(CFPlayer ai1, CFPlayer ai2) {
        if(ai2 == null){
            redPlayer = ai1;
            humanPlayer = "human";

        }
        else {
            long seed = System.currentTimeMillis();
            Random rand = new Random(seed);
            int firstplayer = rand.nextInt(1);
            if (firstplayer == 0) {
                redPlayer = ai1;
                blackPlayer = ai2;
            } else {
                redPlayer = ai2;
                blackPlayer = ai1;
            }
        }
        //sets frame
        frame = new JFrame("connect four");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //creates new gameboard
        this_board = new GameBoard();
        frame.getContentPane().add(this_board.getBoard(), BorderLayout.CENTER);
        //if there is a human player, put arrow buttons onto the frame, else, put play button
        if (ai2 == null) {
            setUpArrows();
        } else
            setUpPlay();
        //red player automatically goes first
        playGUIAI();
        frame.setVisible(true);
    }

    //calls other constructor with ai and null
    public GUICF(CFPlayer ai) {
        this(ai, null);
    }

    private void setUpArrows() {
        //creates a row of buttons with arrows and calls the arrowlistener for each button
        JButton[] arrows = new JButton[6];
        JPanel arrow_panel = new JPanel();
        for (int i = 0; i < 6; i++) {
            arrows[i] = new JButton("\u2193");
            arrows[i].addActionListener(new arrowListener(i));
            arrow_panel.add(arrows[i]);

        }
        //adds buttons to frame
        frame.getContentPane().add(arrow_panel, BorderLayout.NORTH);
    }

    private void setUpPlay() {
        //creates a play button and calls the playlistener
        JPanel playB = new JPanel();
        JButton play = new JButton("play");
        play.addActionListener(new playListener());
        playB.add(play);
        //adds button to frame
        frame.getContentPane().add(playB, BorderLayout.NORTH);
    }

    class arrowListener implements ActionListener {
        private int column;

        arrowListener(int i) {
            column = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //once the arrow is clicked, it is now the blackplayers turn
            g.isRedTurn = false;
            //calls playgui with the number of the arrow clicked
            playGUI(column);
            //goes back to the redplayers turn and calls the ai to play
            if(!g.isGameOver()) {
                g.isRedTurn = true;
                playGUIAI();
            }
        }
    }

    class playListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //once play is clicked, it is now the blackplayer's turn
            g.isRedTurn = false;
            //calls the black ai to play
            playGUIAI();
            //switches back to the red ai and calls red ai to play
            g.isRedTurn = true;
            playGUIAI();
        }
    }


    public void getWinner(){
        //creates a new jframe to show the winner
        JFrame f = new JFrame("the winner is:");
        JPanel w = new JPanel();
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        w.setBackground(Color.PINK);
        //checks who the winner is and will add label accordingly
        if (winner == 1) {
            JLabel win = new JLabel("The winner is: " + redPlayer.getName());
            w.add(win, BorderLayout.CENTER);
        } else if (winner == -1) {
            if(blackPlayer!=null) {
                JLabel win = new JLabel("The winner is: " + blackPlayer.getName());
                w.add(win, BorderLayout.CENTER);
            }
            else
            {
                JLabel win = new JLabel("The winner is: " + humanPlayer);
                w.add(win,BorderLayout.CENTER);
            }
        }
        else
        {
            JLabel win = new JLabel("it is a draw");
            w.add(win, BorderLayout.CENTER);
        }
        //adds jpanel to frame
        f.getContentPane().add(w);
        f.setVisible(true);

    }


    private boolean playGUI(int c) {
        //makes sure the column is correct
        int[][]arr = g.getState();
        if(c>5)
            return false;
        else if(arr[0][c]!=0)
            return false;
        else{
            //plays the number chosen  for cfgame and if it is the red turn, the square is red, if it is the black turn, the square is black
            g.play(c);
            for(int i=6; i>=0; i--){
                if(arr[i][c]==0)
                {
                    if(g.isRedTurn)
                    {
                        this_board.paint(i, c, 1);
                    }
                    else
                        this_board.paint(i, c, -1);
                    break;

                }
            }
            if(g.isGameOver())
            {
                winner = g.winner();
                getWinner();
            }
            return true;
        }

    }

    private void playGUIAI(){
        //plays the ais
        if(g.isRedTurn){
            //gets the red ai's next move
            int num = redPlayer.nextMove(g);
            playGUI(num);
        }
        else{
            //gets the black ai's next move
            int num = blackPlayer.nextMove(g);
            playGUI(num);
        }

    }

    private class GameBoard extends javax.swing.JPanel {
        private JPanel board;
        private JPanel[][]squares;
        private GameBoard() {
            //creates game board with a grid and borders
            board = new JPanel(new GridLayout(7, 6));
            board.setBackground(Color.WHITE);
            squares = new JPanel[7][6];
            for(int i=0; i<7; i++)
            {
                for(int j=0; j<6; j++)
                {
                    squares[i][j] = new JPanel();
                    squares[i][j].setBackground(Color.WHITE);
                    squares[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    board.add(squares[i][j]);
                }
            }
        }
        private JPanel getBoard(){
            return board;
        }

        //changes the color of the specific column and row to either red or black
        private void paint(int x, int y, int color) {
            if(color == 1)
                squares[x][y].setBackground(Color.RED);
            else
                squares[x][y].setBackground(Color.BLACK);

        }
    }
}