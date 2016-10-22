/**
 * Created by Eric on 10/20/16.
 */

import com.sun.org.apache.regexp.internal.RESyntaxException;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.io.*;
import java.util.*;
public class Program2 {

    public static void main (String[] args){
        try {
            parseFile(new Scanner (new File(args[0])));
        }
        catch (Exception e){
            System.out.println("Invalid input format");
            System.exit(0);
        }
    }

    static void parseFile(Scanner scan){
        String[] inputArgs = scan.nextLine().trim().split("\\s+");
        int numOfDevices = Integer.parseInt(inputArgs[0]);
        int numOfTraces = Integer.parseInt(inputArgs[1]);
        ArrayList<int[]> traces = new ArrayList<>();
        int[] query = new int[4];
        for (int i = 0; i < numOfTraces; i ++){
            String[] traceEntries = scan.nextLine().trim().split("\\s+");
            int[] s = new int[traceEntries.length];
            for (int j = 0; j < traceEntries.length; j++){
                s[j] = Integer.parseInt(traceEntries[j]);
            }
            traces.add(s);
        }

        ArrayList<Edge>[] graph = constructGraph(traces, numOfDevices);

        String[] queryString = scan.nextLine().trim().split("\\s+");
        for (int i = 0; i < 4; i++){
            query[i] = Integer.parseInt(queryString[i]);
        }

        ArrayList<Edge> backTrace = solveGraph(graph, query);
        printResult(backTrace);
    }

    static ArrayList<Edge>[] constructGraph (ArrayList<int[]> traces, int devices){
        ArrayList<Edge>[] graph = new ArrayList[devices];
        for (int[] trace: traces) {
            int device1 = trace[0];
            int device2 = trace[1];
            Edge e = new Edge(device1, device2, trace[2]);
            if (graph[device1] == null){
                graph[device1] = new ArrayList<Edge>();
            }
            graph[device1].add(e);
            if (graph[device2] == null){
                graph[device2] = new ArrayList<Edge>();
            }
            graph[device2].add(e);

        }

        return graph;
    }

    static ArrayList<Edge> solveGraph (ArrayList<Edge>[] graph, int[] query){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();
        HashMap<Integer, Edge> traverse = new HashMap<>();
        ArrayList<Node> resultGraph = new ArrayList<>();
        for(int i = 0; i < graph.length; i++){
            resultGraph.add(i, new Node(i, query[3] + 1));
        }

        resultGraph.get(query[0]).timestamp = query[2];
        queue.add(resultGraph.get(query[0]));

        boolean found = false;
        while(!queue.isEmpty()){
            Node current = queue.poll();
            if(current.timestamp > query[3]) {
                found = false;
                break;
            }
            if(current.device == query[1]){
                found = true;
                break;
            }

            visited.add(new Integer(current.device));
            for (int i = 0; i < graph[current.device].size(); i++){
                Edge e = graph[current.device].get(i);
                int otherDevice = e.device1 != current.device ? e.device1 : e.device2;
                if(!visited.contains(new Integer(otherDevice))){
                    if((current.timestamp <= e.timestamp) && resultGraph.get(otherDevice).timestamp > e.timestamp) {
                        if(e.timestamp <= query[3]){
                            resultGraph.get(otherDevice).timestamp = e.timestamp;
                            queue.add(resultGraph.get(otherDevice));
                            traverse.put(new Integer(otherDevice), e);
                        }
                    }
                }
            }
        }

        if(found){
            ArrayList<Edge> trace = new ArrayList<>();
            Integer key = new Integer(query[1]);
            while(traverse.containsKey(key)){
                Edge e = traverse.remove(key);
                trace.add(0,e);
                int otherDeviceKey = e.device1 != key.intValue() ? e.device1: e.device2;
                key = new Integer(otherDeviceKey);
            }
            return trace;
        } else {
            return null;
        }
    }

    static void printResult (ArrayList<Edge> result){
        if(result == null){
            System.out.println(0);
            return;
        }
        System.out.println(result.size());
        for(int i = 0; i < result.size(); i++){
            System.out.println(result.get(i));
        }

    }
}
