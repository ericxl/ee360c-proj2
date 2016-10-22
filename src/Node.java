/**
 * Created by Eric on 10/21/16.
 */
public class Node implements Comparable<Node>{
    public int device;
    public int timestamp;

    public Node(int dev, int time){
        device = dev;
        timestamp = time;
    }


    @Override
    public int compareTo(Node other){
        return Integer.compare(this.timestamp, other.timestamp);
    }
}
