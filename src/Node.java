import java.awt.List;
import java.util.*;

public class Node
{
    private LinkedList<Pair> transitions;

    private final int nb;

    public Node(int i)
    {
        nb = i;
        this.transitions = new LinkedList<>();
    }

    public void addTransition(Node toAdd)
    {
        for (Pair pair : this.transitions) {
            if (pair.node.nb == toAdd.nb)
                return;
        }
        this.transitions.add(new Pair(toAdd));
        if (toAdd.nb != this.nb)
            toAdd.transitions.add(new Pair(this));
    }

    public LinkedList<Node> getConnections()
    {
        LinkedList<Node> nodes = new LinkedList<>();

        for (Pair pair : this.transitions) {
            nodes.add(pair.node);
        }
        return nodes;
    }

    public LinkedList<Pair> getConnectionProbabilites()
    {
        return ((LinkedList<Pair>)this.transitions.clone());
    }

    public void shuffleTransitions()
    {
        LinkedList<Pair> neoTransitions = new LinkedList<>();
        Random random = new Random();

        while (transitions.size() > 0) {
            Pair pair = transitions.remove(Math.abs(random.nextInt()) % transitions.size());

            neoTransitions.add(pair);
        }
        this.transitions = neoTransitions;
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("Je suis node ").append(nb).append(" et je suis connecté à : \n\r");

        for (Pair pair : this.transitions) {
            builder.append("[NODE ").append(pair.node.nb).append("] - [PROBABITY ").append(pair.probability).append("]\n\r");
        }
        return builder.toString();
    }

    public void setProbabilities(ArrayList<Double> probabilities)
    {
        int i = 0;
        Double ok = 0.0;

        for (Pair pair : this.transitions) {
            ok += probabilities.get(i);
            pair.setProbability(probabilities.get(i));
            i++;
        }
    }



    public void setRates(ArrayList<Integer> rates)
    {
        int i = 0;
        Integer ok = 0;

        for (Pair pair : this.transitions) {
            ok += rates.get(i);
            pair.setRate(rates.get(i));
            i++;
        }
    }
    public int getNb()
    {
        return nb;
    }
}
