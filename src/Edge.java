
/**
 * Created by Eric on 10/21/16.
 */
public class Edge {
    public int device1;
    public int device2;
    public int timestamp;

    public Edge (int d1, int d2, int time){
        device1 = d1;
        device2 = d2;
        timestamp = time;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;

        if(!Edge.class.isAssignableFrom(obj.getClass())){
            return false;
        }
        final Edge other = (Edge) obj;
        if(this.timestamp != other.timestamp){
            return false;
        }
        if(this.device1 == other.device1 && this.device2 == other.device2) {
            return true;
        }
        if(this.device2 == other.device1 && this.device1 == other.device2) {
            return true;
        }

        return false;
    }

    @Override
    public String toString(){
        return device1 <= device2 ? device1 + " " + device2 + " " + timestamp : device2 + " " + device1 + " " + timestamp;
    }
}
