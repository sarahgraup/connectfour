package homework4;

public class CFGame {
    //state[i][j]= 0 means the i,j slot is empty
    //state[i][j]= 1 means the i,j slot has red
    //state[i][j]=-1 means the i,j slot has black
    private final int[][] state;
    public boolean isRedTurn;
    public boolean isdraw = false;
    public int winner;
    private boolean over = false;

    {
        state = new int[7][6];
        for (int i=0; i<7; i++)
            for (int j=0; j<6; j++)
                state[i][j] = 0;
        isRedTurn = true; //red goes first
    }

    public int[][] getState() {
        int[][] ret_arr = new int[7][6];
        for (int i=0; i<7; i++)
            for (int j=0; j<6; j++)
                ret_arr[i][j] = state[i][j];
        return ret_arr;
    }

    public boolean isRedTurn() {
        return isRedTurn;
    }

    public boolean play(int column) {
        //checks if column chosen is valid
        if(column>5)
            return false;
        else if(state[0][column]!=0)
            return false;
        else
        {
            //if column chosen is valid, assigns either 1 or -1 to (row,column )
            for(int i=6; i>=0; i--)
            {
                if(state[i][column] == 0)
                {
                    if(isRedTurn)
                    {
                        state[i][column] = 1;
                    }
                    else
                        state[i][column] = -1;
                    break;
                }
            }
            return true;
        }
    }

    public boolean isGameOver() {
        //checks if there is four in a row and assigns winner
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == state[i][j + 1] && state[i][j + 1] == state[i][j + 2] && state[i][j + 2] == state[i][j + 3] && state[i][j]!=0) {
                    over = true;
                    if(isRedTurn)
                        winner = 1;
                    else winner = -1;
                    return over;
                }
            }
        }
        //checks if there is four in a column and assigns winner
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (state[i][j] == state[i + 1][j] && state[i + 1][j] == state[i + 2][j] && state[i + 2][j] == state[i + 3][j] && state[i][j]!=0) {
                    over = true;
                    if(isRedTurn)
                        winner = 1;
                    else winner = -1;
                    return over;
                }
            }
        }
        //checks if there is four diagonal and assigns winner
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == state[i + 1][j + 1] && state[i + 1][j + 1] == state[i + 2][j + 2] && state[i + 2][j + 2] == state[i + 3][j + 3] && state[i][j]!=0) {
                    over = true;
                    if(isRedTurn)
                        winner = 1;
                    else winner = -1;
                    return over;
                }
            }
        }

        //checks if there is four diagonal2 and assigns winner
        for (int i = 6; i > 2; i--) {
            for (int j = 0; j <3; j++) {
                if (state[i][j] == state[i - 1][j + 1] && state[i - 1][j + 1] == state[i - 2][j + 2] && state[i - 2][j + 2] == state[i - 3][j + 3] && state[i][j]!=0) {
                    over = true;
                    if(isRedTurn)
                        winner = 1;
                    else winner = -1;
                    return over;
                }
            }
        }
        //checks if there is a draw(if boards is full)
        if (!over) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 6; j++) {
                    if (state[i][j] == 0)
                    {
                        return over;
                    }
                    else
                    {
                        over = true;
                    }
                }
            }
            if(over == true)
                isdraw = true;
        }

        return over;

    }

    public int winner() {
        //assigns winner
        if(winner ==1)
            return 1;
        else if(winner == -1)
            return -1;
        else
            return 0;
    }
}