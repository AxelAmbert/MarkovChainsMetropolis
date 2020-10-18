public class Pair
{
    public Node node;
    public Double probability;
    public Integer rate;

    public Node getNode()
    {
        return node;
    }

    public void setNode(Node node)
    {
        this.node = node;
    }

    public Double getProbability()
    {
        return probability;
    }

    public void setProbability(Double probability)
    {
        this.probability = probability;
    }

    public Integer getRate()
    {
        return rate;
    }

    public void setRate(Integer rate)
    {
        this.rate = rate;
    }


    Pair(Node _x) {
        node = _x;
        probability = 0.0;
        rate = 0;
    }
}
