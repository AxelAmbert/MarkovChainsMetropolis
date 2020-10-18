import java.util.ArrayList;
import java.util.Random;

public class ContinuousMarkovChain extends MarkovChain
{

    public void setupRandomProbabilities()
    {
        for (Node node : this.nodes) {
            ArrayList<Integer> rates = this.getRandomRates(node.getConnections().size());
            ArrayList<Double> probabilities = this.getRandomValues(rates);

            node.setRates(rates);
            node.setProbabilities(probabilities);
        }
    }

    public ArrayList<Integer> getRandomRates(int nb)
    {
        ArrayList<Integer> rates = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < nb; i++) {
            rates.add(Math.abs(random.nextInt() % 50));
        }
        return (rates);
    }

    public ArrayList<Double> getRandomValues(ArrayList<Integer> rates) {
        ArrayList<Double> probabilities = new ArrayList<>();
        Integer nb = rates.size();

        for (int i = 0; i < nb; i++) {
            Double probability = 0.0;
            Integer sum = 0;
            for (int n = 0; n < nb; n++) {
                sum += rates.get(n);
            }
            probability = (double)rates.get(i) / (double)sum;
            probabilities.add(probability);
        }
        return (probabilities);
    }

    public void addWaitingTime(Node node)
    {
        Integer sum = 0;
        Random random = new Random();
        Double time = 0.0;

        for (Pair pair : node.getConnectionProbabilites()) {
            sum += pair.rate;
        }
        Double rdm = random.nextDouble();
        Double log = Math.log(rdm);
        time = (((double)1/sum) * log) * -1;
        //this.waitingTime.add(time);
        this.totalTime += time;
    }

    public void enableSelfTransition(Node node)
    {

    }
}
