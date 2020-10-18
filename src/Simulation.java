import java.text.Collator;
import java.util.*;

public class Simulation
{
    MarkovChain markovChain;
    Node actualNode;
    Integer[] count;
    Integer[][] links;

    public Simulation()
    {
        this.markovChain = new DiscreteMarkovChain();
        this.count = new Integer[11];
        this.links = new Integer[][]{{4, 7, 9}, {5, 9}, {10, 1}, {2, 6}, {10, 3}, {2, 10}, {8, 3}, {2, 5, 3}, {7, 5}};
        for (int i = 0; i < 11; i++)
            this.count[i] = 0;
        this.setupMarkovChain();
        this.markovChain.enableSelfTransition();
        this.markovChain.shuffleNodes();
        this.markovChain.setupRandomProbabilities();
        this.actualNode = this.markovChain.getRandomNode();
        System.out.println(this.markovChain);
        this.run();
    }

    public void run()
    {
        int i = 0;
        int n = 0;
        while (this.markovChain.totalTime <= 100000000) {
            for (int m = 0; m < 1000; m++) {
                this.markovChain.addWaitingTime(actualNode);
                actualNode = this.towerSampling(actualNode);
                i = actualNode.getNb();
            }
            n++;
            this.count[i - 1] += 1;
        }
        Double sum = 0.0;
        for (i = 0; i < this.count.length - 1; i++) {
            System.out.println("PI FOR " + (i + 1) + " [" + (double)this.count[i] / (double)n + "]");
            sum +=  (double)this.count[i] / (double)n;
        }
        System.out.println("SUM ? " + sum);
    }

    public Node towerSampling(Node node)
    {
        Random random = new Random();
        LinkedList<Pair> t = this.getOrderedProbabilities(node);
        Double r = random.nextDouble();

        for (int i = 0; i < t.size(); i++) {
            Double sumMinusOne = 0.0;
            Double sum = 0.0;

            if (i < 1)
                continue;
            for (int n = 0; n < i; n++) {
                sumMinusOne += t.get(n).probability;
            }
            sum = sumMinusOne + t.get(i).probability;
            if (sumMinusOne < r && sum >= r) {
                return (t.get(i).node);
            }
        }
        return (t.get(t.size() - 1).node);
    }


    public LinkedList<Pair> getOrderedProbabilities(Node node)
    {
        LinkedList<Pair> connections = node.getConnectionProbabilites();
        ArrayList<Double> probabilities = new ArrayList<>();

        connections.sort((o1, o2) -> {
            if (o1.probability.equals(o2.probability)) {
                return (0);
            } else if (o1.probability > o2.probability) {
                return (-1);
            }
            return (1);
        });
        connections.forEach((pair) -> {
            probabilities.add(pair.probability);
        });
        connections.add(0, new Pair(null));
        return (connections);
    }

    public void setupMarkovChain()
    {
        for (int i = 0; i < 10; i++) {
            markovChain.addNode(new Node(i + 1));
        }
        int i = 0;
        for (Integer[] subArr : this.links) {
            for (Integer integer : subArr) {
                markovChain.get(i).addNode(markovChain.get(integer - 1));
            }
            i++;
        }

       /* for (i = 0; i < 10; i++) {
            Node node = markovChain.get(i);

            for (Node connection : node.getConnections()) {
                System.out.print(connection.nb + " ");
            }
            System.out.println();
        }*/
    }

    public void setupRandomProbabilities()
    {
        this.markovChain.setupRandomProbabilities();
    }


}
