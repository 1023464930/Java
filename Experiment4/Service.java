package Experiment4;

public class Service
{
    private int dest;
    private int cost;
    private int distance;
    Service link;
    public Service(int dest, int cost, int distance, Service link)
    {
        this.dest = dest;
        this.cost = cost;
        this.distance = distance;
        this.link = link;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Service getLink() {
        return link;
    }

    public void setLink(Service link) {
        this.link = link;
    }
}
