package homework4;

import java.util.Random;

public class SarahAI implements CFPlayer {
    @Override
    public int nextMove(CFGame g)
    {
        //checks if SarahAI is red or black
        int yourNum;
        int opponentNum;
        if(g.isRedTurn)
        {
            yourNum = 1;
            opponentNum=-1;
        }
        else
        {
            yourNum = -1;
            opponentNum = 1;
        }
        int[][]arr = g.getState();
        for(int i=6; i>0; i--)
        {
            //checks for horizontal three in a row of its number
            for(int j=0; j<3; j++)
            {
                int count = 0;
                int zero =0;
                int one = 0;
                int two = 0;
                int three = 0;
                if(arr[i][j] == yourNum)
                {
                    count++;
                    zero++;
                }
                if(arr[i][j+1] == yourNum)
                {
                    count++;
                    one++;
                }
                if(arr[i][j+2] == yourNum)
                {
                    count++;
                    two++;
                }
                if(arr[i][j+3] == yourNum)
                {
                    count++;
                    three++;
                }
                if(count == 3)
                {
                    if(zero ==0 && arr[i][j] == 0)
                    {
                        return j;
                    }
                    else if(one == 0 && arr[i][j+1] == 0) {
                        return j+1;
                    }
                    else if(two == 0 && arr[i][j+2] == 0) {
                       return j+2;
                    }
                    else if(three == 0 && arr[i][j+3] == 0){
                        return j+3;
                    }
                }
            }
        }
        //checks for vertical three in a row of its number
        for(int i=6; i>2; i--)
        {
            for(int j=0; j<6; j++)
            {
                if(arr[i][j] == arr[i-1][j] && arr[i-1][j] == arr[i-2][j]&& arr[i-2][j] == yourNum && arr[i-3][j] == 0){
                    return j;
                }
            }
        }
        //checks for diagonal three in a row for its number
        for(int i=0; i<4; i++)
        {
            for(int j=0; j<3; j++)
            {
                if (arr[i][j] == arr[i + 1][j + 1] && arr[i + 1][j + 1] == arr[i + 2][j + 2] && arr[i + 2][j + 2] == yourNum && arr[i + 3][j + 3] == 0){
                    return j+3;
                }
            }
        }
        //checks for diagonal2 three in a row for its number
        for (int i = 6; i > 2; i--) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == arr[i - 1][j + 1] && arr[i - 1][j + 1] == arr[i - 2][j + 2] && arr[i - 2][j + 2] == yourNum && arr[i - 3][j + 3]== 0){
                    return j+3;
                }
            }
        }
    //checks for horizontal for opponent three  in a row
        for(int i=6; i>0; i--)
        {
            for(int j=0; j<3; j++)
            {
                int count = 0;
                int zero =0;
                int one = 0;
                int two = 0;
                int three = 0;
                if(arr[i][j] == opponentNum)
                {
                    count++;
                    zero++;
                }
                if(arr[i][j+1] == opponentNum)
                {
                    count++;
                    one++;
                }
                if(arr[i][j+2] == opponentNum)
                {
                    count++;
                    two++;
                }
                if(arr[i][j+3] == opponentNum)
                {
                    count++;
                    three++;
                }
                if(count == 3)
                {
                    if(zero ==0 && arr[i][j] == 0)
                    {
                        return j;
                    }
                    else if(one == 0 && arr[i][j+1] == 0) {
                        return j+1;
                    }
                    else if(two == 0 && arr[i][j+2] == 0) {
                        return j+2;
                    }
                    else if(three == 0 && arr[i][j+3] == 0){
                        return j+3;
                    }
                }
            }
        }
        //vertical for your opponent three in a row
        for(int i=6; i>2; i--)
        {
            for(int j=0; j<6; j++)
            {
                if(arr[i][j] == arr[i-1][j] && arr[i-1][j] == arr[i-2][j]&& arr[i-2][j] == opponentNum && arr[i-3][j] == 0){
                    return j;
                }
            }
        }
        //horizontal for your number two in a row
        for(int i=6; i>0; i--) {
            for (int j = 0; j < 4; j++) {
                int count = 0;
                int zero = 0;
                int one = 0;
                int two = 0;
                if (arr[i][j] == yourNum) {
                    count++;
                    zero++;
                }
                if (arr[i][j + 1] == yourNum) {
                    count++;
                    one++;
                }
                if (arr[i][j + 2] == yourNum) {
                    count++;
                    two++;
                }
                if (count == 2) {
                    if (zero == 0 && arr[i][j] == 0) {
                        return j;
                    } else if (one == 0 && arr[i][j + 1] == 0) {
                        return j + 1;
                    } else if (two == 0 && arr[i][j + 2] == 0) {
                        return j + 2;
                    }
                }
            }
        }

        //vertical for your number two in a row
        for(int i=6; i>2; i--)
        {
            for(int j=0; j<6; j++)
            {
               if(arr[i][j] == arr[i-1][j] && arr[i-1][j] == yourNum && arr[i-2][j] == 0){
                    return j;
                }
            }
        }
        //horizontal for opponent number two in a row
        for(int i=6; i>0; i--) {
            for (int j = 0; j < 4; j++) {
                int count = 0;
                int zero = 0;
                int one = 0;
                int two = 0;
                if (arr[i][j] == opponentNum) {
                    count++;
                    zero++;
                }
                if (arr[i][j + 1] == opponentNum) {
                    count++;
                    one++;
                }
                if (arr[i][j + 2] == opponentNum) {
                    count++;
                    two++;
                }
                if (count == 2) {
                    if (zero == 0 && arr[i][j] == 0) {
                        return j;
                    } else if (one == 0 && arr[i][j + 1] == 0) {
                        return j + 1;
                    } else if (two == 0 && arr[i][j + 2] == 0) {
                        return j + 2;
                    }
                }
            }
        }

        //vertical for your opponent two in a row
        for(int i=6; i>2; i--)
        {
            for(int j=0; j<6; j++)
            {
                if(arr[i][j] == arr[i-1][j] && arr[i-1][j] == opponentNum && arr[i-2][j] == 0){
                    return j;
                }
            }
        }
        //if a number has not been returned(there weren't any significant spots) create a random number
        long seed = System.currentTimeMillis();
        Random rand = new Random(seed);
        int columnRandom = rand.nextInt(6);
        return columnRandom;
    }

    //returns SarahAI name
    @Override
    public String getName()
    {
        return "Sarah AI";
    }


}
