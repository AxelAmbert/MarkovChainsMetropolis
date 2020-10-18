import java.util.ArrayList;
import java.util.Random;

public class DiscreteMarkovChain extends MarkovChain
{

    public ArrayList<Double> getRandomValues(int nb)
    {
        ArrayList<Double> array = new ArrayList<Double>();
        Double max = 1.0;
        Double remainder = 1.0;
        Random random = new Random();

        nb -= 1;
        for (int i = 0; i < nb; i++) {
            Double rand = random.nextDouble();

            if (rand > max) {
                rand %= max;
            }
            if (rand > (0.05 * (nb -  i))) {
                rand = rand - (0.05 * (nb - i));
            }
            if (rand < 0.05)
                rand = 0.05;
            rand = ((double)1 / ((double)nb + 1));
            max -= rand;
            remainder -= rand;
            array.add(rand);
        }
        if (remainder < 0.0)
            remainder = 0.0;
        array.add(remainder);
        return (array);
    }

    public void addWaitingTime(Node node)
    {
        //this.waitingTime.add(1.0);
        this.totalTime += 1.0;
    }
}
