import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class MarkovChain
{
    protected final LinkedList<Node> nodes;
    protected final LinkedList<Double> waitingTime;
    public Double totalTime;

    public MarkovChain()
    {
        this.totalTime = 0.0;
        this.waitingTime = new LinkedList<>();
        this.nodes = new LinkedList<>();
    }

    public void addNode(Node toAdd)
    {
        this.nodes.add(toAdd);
    }

    public Node get(int nb)
    {
        return this.nodes.get(nb);
    }

    public void shuffleNodes()
    {
        for (Node node : nodes) {
            node.shuffleTransitions();
        }
    }

    public void enableSelfTransition()
    {
        for (Node node : nodes) {
            node.addTransition(node);
        }
    }

    public void setupRandomProbabilities()
    {
        for (Node node : this.nodes) {
            ArrayList<Double> probabilities = this.getRandomValues(node.getConnections().size());
            int i = 0;

            node.setProbabilities(probabilities);
        }
    }

    public ArrayList<Double> getRandomValues(int nb)
    {
        ArrayList<Double> array = new ArrayList<Double>();

        return array;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Node node : this.nodes) {
            builder.append(node);
        }
        return builder.toString();
    }

    public Node getRandomNode() {
        Random random = new Random();

        return (this.nodes.get(Math.abs(random.nextInt()) % this.nodes.size()));
    }

    public void addWaitingTime(Node node){
        waitingTime.add(1.0);totalTime += 1.0;}
}
