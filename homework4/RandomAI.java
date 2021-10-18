package homework4;
import java.util.Random;

public class RandomAI implements CFPlayer{
    @Override
    public int nextMove(CFGame g)
    {
        //creates and returns random integer
        long seed = System.currentTimeMillis();
        Random rand = new Random(seed);
        int columnRandom = rand.nextInt(6);
        return columnRandom;
    }

    //return value of getName
    @Override
    public String getName()
    {
        return "Random Player";
    }

}
